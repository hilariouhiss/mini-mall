package me.lhy.user.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lhy.user.converter.Converter;
import me.lhy.user.domain.dto.UserDto;
import me.lhy.user.domain.po.User;
import me.lhy.user.mapper.UserMapper;
import me.lhy.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    /**
     * 查询所有deleted = 0 的用户
     *
     * @return 用户列表
     */
    @Override
    public Page<UserDto> selectAll(Integer currentPage, Integer pageSize) {
        log.info("查询所有用户，currentPage: {}, pageSize: {}", currentPage, pageSize);
        // 创建分页对象
        Page<User> page = new Page<>(currentPage, pageSize);
        // 执行分页查询
        Page<User> userPage = userMapper.selectPage(page, null);
        // 取得查询结果并转换为UserDto
        List<UserDto> userDtoList = userPage.getRecords()
                .stream().map(Converter.INSTANCE::toUserDTO).toList();
        return new Page<UserDto>()
                .setRecords(userDtoList)
                .setTotal(userPage.getTotal())
                .setCurrent(userPage.getCurrent())
                .setSize(userPage.getSize());

    }


    /**
     * 根据id查询用户
     * @param id 用户主键
     * @return UserDto
     */
    @Override
    public UserDto selectById(Long id) {
        User user = userMapper.selectById(id)
                .orElseThrow(() -> new NoSuchElementException("user not found"));
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
                .orElseThrow(() -> new NoSuchElementException("user not found"));
        return Converter.INSTANCE.toUserDTO(user);
    }

    /**
     * 用户注册
     * @param userDTO 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserDto userDTO) {
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
            throw new IllegalArgumentException("password format error");
        }

        // 加密密码
        userDTO.setPassword(BCrypt.hashpw(userDTO.getPassword()));
        // 转为User对象，deleted、createdAt、updatedAt字段由mybatis-plus自动填充
        User user = Converter.INSTANCE.toUser(userDTO);

        log.info("插入用户，user: {}", user.getUsername());
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
     * @return token 信息
     */
    @Override
    public SaTokenInfo loginByPhone(String phoneNumber, String password) {
        return processLogin(phoneNumber, password,
                () -> userMapper.selectByPhoneNumber(phoneNumber));
    }

    @Override
    public void updateById(UserDto user) {
        userMapper.updateById(Converter.INSTANCE.toUser(user));
    }

    /**
     * 使用用户名登录
     * @param username 用户名
     * @param password 密码
     * @return token 信息
     */
    @Override
    public SaTokenInfo loginByUsername(String username, String password) {
        return processLogin(username, password,
                () -> userMapper.selectByUsername(username));
    }

    /**
     * 具体登录逻辑
     * @param key 用户名或手机号
     * @param password 密码
     * @param supplier 查询用户的方法
     * @return token 信息
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
