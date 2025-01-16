package me.lhy.user.mapper;

import me.lhy.user.domain.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

public interface UserMapper {

    @Select("SELECT * FROM user where id = #{id}")
    Optional<User> selectById(Long id);

    @Select("SELECT * FROM user")
    Optional<List<User>> selectAll();

    @Select("SELECT * FROM user where username = #{name}")
    Optional<User> selectByUsername(String name);

    @Insert("INSERT INTO user(id, username, password, phone, created_at, updated_at,deleted) " +
            "VALUES(#{id}, #{username}, #{password}, #{phone}, now(), now(), 0)")
    void insert(User user);

    // 逻辑删除，置 deleted 为 true
    @Update("UPDATE user SET deleted = 1 WHERE id = #{id}")
    void deleteById(Long id);

}
