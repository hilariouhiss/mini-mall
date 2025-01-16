package me.lhy.user.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import me.lhy.commons.exception.ItemNotFoundException;
import me.lhy.user.converter.Converter;
import me.lhy.user.domain.dto.LoginDTO;
import me.lhy.user.domain.dto.UserDTO;
import me.lhy.user.domain.po.User;
import me.lhy.user.mapper.UserMapper;
import me.lhy.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> selectAll() {
        log.info("开始查询所有用户");
        List<User> userList = userMapper.selectAll().orElseGet(Collections::emptyList);
        return userList.stream()
                .map(Converter.INSTANCE::toUserDTO)
                .collect(Collectors.toList());
    }


    @Override
    public UserDTO selectById(Long id) {
        User user = userMapper.selectById(id)
                .orElseThrow(() -> new ItemNotFoundException("user not found"));
        return Converter.INSTANCE.toUserDTO(user);
    }

    @Override
    public UserDTO selectByUsername(String username) {
        User user = userMapper.selectByUsername(username)
                .orElseThrow(() -> new ItemNotFoundException("user not found"));
        return Converter.INSTANCE.toUserDTO(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserDTO userDTO) {
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

        // TODO: 使用雪花算法设置 ID
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setDeleted(false);

        log.info("开始注册用户，user: {}", user);
        // userMapper.insert(user);
    }

    /**
     * 逻辑删除，将deleted字段设置为true
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public SaTokenInfo login(LoginDTO login) {
        // 参数校验
        // 1. 不为 null
        if (login == null) {
            throw new IllegalArgumentException("login is null");
        }

        log.info("开始登录，{}", login);

        // 2. 比对username和password
        userMapper.selectByUsername(login.getUsername())
                .ifPresentOrElse(
                        // 如果存在
                        u->{
                            // 比对密码
                            if (BCrypt.checkpw(login.getPassword(), u.getPassword())) {
                                // 登录成功
                                StpUtil.login(u.getId());
                                log.info("登录成功，user_id: {}", u.getId());
                            }
                        },
                        // 如果不存在则抛出错误
                        ()->{
                            throw new IllegalArgumentException("username or password error");
                        }
                );
        log.info("登录结束，username: {}", login.getUsername());
        return StpUtil.getTokenInfo();
    }
}
