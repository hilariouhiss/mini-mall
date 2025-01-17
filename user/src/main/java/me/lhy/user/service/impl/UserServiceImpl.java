package me.lhy.user.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import me.lhy.commons.exception.DataNotFoundException;
import me.lhy.user.converter.Converter;
import me.lhy.user.domain.dto.UserDto;
import me.lhy.user.domain.po.User;
import me.lhy.user.mapper.UserMapper;
import me.lhy.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 查询所有deleted = 0 的用户
     * @return List<UserDto>
     */
    @Override
    public List<UserDto> selectAll() {
        log.info("开始查询所有用户");
        List<User> userList = userMapper.selectAll().orElseGet(Collections::emptyList);
        return userList.stream()
                .map(Converter.INSTANCE::toUserDTO)
                .collect(Collectors.toList());
    }


    /**
     * 根据id查询用户
     * @param id 用户主键
     * @return UserDto
     */
    @Override
    public UserDto selectById(Long id) {
        User user = userMapper.selectById(id)
                .orElseThrow(() -> new DataNotFoundException("user not found"));
        return Converter.INSTANCE.toUserDTO(user);
    }

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return UserDto
     */
    @Override
    public UserDto selectByUsername(String username) {
        User user = userMapper.selectByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("user not found"));
        return Converter.INSTANCE.toUserDTO(user);
    }

    /**
     * 用户注册
     * @param userDTO 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserDto userDTO) {
        // 参数校验
        // 1. userDTO 不能为 null
        if (userDTO == null) {
            throw new IllegalArgumentException("user is null");
        }
        // 2. username 不能已存在
        userMapper.selectByUsername(userDTO.getUsername())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("username already exists");
                });
        // 3. password 只能包含大小写字母、数字和特殊字符，并且长度必须在[8,56]
        if (!userDTO.getPassword().matches("^[a-zA-Z0-9!@#$%^&*()\\-_=+]{8,56}$")) {
            throw new IllegalArgumentException("password must be 8-56 characters long and contain only uppercase and lowercase letters, numbers, and special characters");
        }

        // 加密密码
        userDTO.setPassword(BCrypt.hashpw(userDTO.getPassword()));
        // 转为User对象
        User user = Converter.INSTANCE.toUser(userDTO);
        // 设置deleted、createdAt、updatedAt
        // user.setCreatedAt(LocalDateTime.now());
        // user.setUpdatedAt(LocalDateTime.now());
        // user.setDeleted(false);

        log.info("开始注册用户，user: {}", user);
        userMapper.insert(user);
    }

    /**
     * 逻辑删除，将deleted字段设置为true
     * @param id 用户主键
     */
    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    /**
     * 使用手机号登录
     * @param phoneNumber 手机号
     * @param password 密码
     * @return SaTokenInfo
     */
    @Override
    public SaTokenInfo loginByPhone(String phoneNumber, String password) {
        return processLogin(phoneNumber, password,
                () -> userMapper.selectByPhoneNumber(phoneNumber));
    }

    /**
     * 使用用户名登录
     * @param username 用户名
     * @param password 密码
     * @return SaTokenInfo
     */
    @Override
    public SaTokenInfo loginByUsername(String username, String password) {
        return processLogin(username, password,
                () -> userMapper.selectByUsername(username));
    }

    /**
     * 登录逻辑
     * @param key 用户名或手机号
     * @param password 密码
     * @param supplier 查询用户的方法
     * @return SaTokenInfo
     */
    private SaTokenInfo processLogin(String key, String password, Supplier<Optional<User>> supplier) {
        // 参数校验
        // 1. 不为 null
        if (key == null || password == null) {
            throw new IllegalArgumentException("key or password is null");
        }

        log.info("开始登录，{}", key);

        // 2. 比对key和password
        supplier.get().ifPresentOrElse(
                        // 如果存在
                        u -> {
                            // 比对密码
                            if (BCrypt.checkpw(password, u.getPassword())) {
                                // 登录成功
                                StpUtil.login(u.getId());
                                log.info("登录成功，user_id: {}", u.getId());
                            }
                        },
                        // 如果不存在则抛出错误
                        () -> {
                            throw new IllegalArgumentException("key or password error");
                        }
                );
        log.info("登录结束，key: {}", key);
        return StpUtil.getTokenInfo();

    }
}
