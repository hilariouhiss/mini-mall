package me.lhy.user.service;


import cn.dev33.satoken.stp.SaTokenInfo;
import me.lhy.user.domain.dto.LoginDTO;
import me.lhy.user.domain.dto.UserDTO;

import java.util.List;

public interface UserService {


    public List<UserDTO> selectAll();

    /**
     * 根据id查询用户
     * @param id
     * @return 用户
     */
    public UserDTO selectById(Long id);

    /**
     * 根据用户名查询用户
     * @param username
     * @return 用户
     */
    public UserDTO selectByUsername(String username);

    // TODO: 加密密码

    /**
     * 用户登录
     * @param login
     * @return 用户
     */
    public SaTokenInfo login(LoginDTO login);

    /**
     * 用户注册
     * @param user
     */
    public void register(UserDTO user);

    /**
     * 根据id删除用户
     * @param id
     */
    public void deleteById(Long id);
}
