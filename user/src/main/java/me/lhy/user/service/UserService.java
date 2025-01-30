package me.lhy.user.service;


import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.lhy.user.domain.dto.UserDto;

public interface UserService {


    /**
     * 分页查询所有用户
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return 用户列表
     */
    Page<UserDto> selectAll(Integer currentPage, Integer pageSize);

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户列表
     */
    UserDto selectById(Long id);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户列表
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
    void add(UserDto user);

    /**
     * 根据id删除用户
     * @param id 用户id
     */
    void deleteById(Long id);

    /**
     * 通过手机号登录
     * @param phoneNumber 手机号
     * @param password 密码
     * @return token信息
     */
    SaTokenInfo loginByPhone(String phoneNumber, String password);


    /**
     * 通过id更新用户信息
     * @param user 新的用户信息
     */
    void updateById(UserDto user);
}
