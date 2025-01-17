package me.lhy.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.lhy.user.domain.po.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @Select("select * from user")
    Optional<List<User>> selectAll();

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户
     */
    @Select("select * from user where id = #{id}")
    Optional<User> selectById(Long id);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    @Select("select * from user where username = #{username}")
    Optional<User> selectByUsername(String username);

    /**
     * 根据手机号查询用户
     * @param phoneNumber 用户手机号
     * @return 用户
     */
    @Select("select * from user where phone_number = #{phoneNumber}")
    Optional<User> selectByPhoneNumber(String phoneNumber);

    @Select("select * from user where deleted = 1")
    Optional<List<User>> selectAllDeleted();


}
