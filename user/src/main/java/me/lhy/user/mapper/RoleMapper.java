package me.lhy.user.mapper;

import me.lhy.user.domain.po.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

public interface RoleMapper {

    @Select("select * from role where deleted = 0")
    Optional<List<Role>> selectAll();

    @Select("select * from role where id = #{id} and deleted = 0")
    Optional<Role> selectById(Long id);

    @Select("select * from role where name = #{name} and deleted = 0")
    Optional<Role> selectByName(String name);
}
