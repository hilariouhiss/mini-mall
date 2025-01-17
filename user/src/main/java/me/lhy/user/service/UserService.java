package me.lhy.user.service;


import cn.dev33.satoken.stp.SaTokenInfo;
import me.lhy.user.domain.dto.UserDto;

import java.util.List;

public interface UserService {


    /**
     * 查询所有未被标记删除的用户
     * @return 用户列表
     */
    List<UserDto> selectAll();

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return UserDto
     */
    UserDto selectById(Long id);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return UserDto
     */
    UserDto selectByUsername(String username);

    /**
     * 使用用户名登录
     * @param username 用户名
     * @param password 密码
     * @return token信息
     */
    SaTokenInfo loginByUsername(String username, String password);

    /**
     * 用户注册
     * @param user 用户信息
     */
    void register(UserDto user);

    /**
     * 根据id删除用户
     * @param id 用户id
     */
    void deleteById(Long id);

    SaTokenInfo loginByPhone(String phoneNumber, String password);
}
