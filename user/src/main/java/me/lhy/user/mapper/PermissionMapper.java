package me.lhy.user.mapper;

import me.lhy.user.domain.po.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

public interface PermissionMapper {

    /**
     * TODO: 分页查询所有权限
     * @return List<Permission>
     */
    @Select("select * from permission where deleted = 0")
    Optional<List<Permission>> selectAll();

    /**
     * 根据id查询权限
     * @param id
     * @return Permission
     */
    @Select("select * from permission where id = #{id} and deleted = 0")
    Optional<Permission> selectById(Long id);

    /**
     * 根据name查询权限
     * @param name
     * @return Permission
     */
    @Select("select * from permission where name = #{name} and deleted = 0")
    Optional<Permission> selectByName(String name);

    /**
     * 查询所有被删除的权限
     * @return List<Permission>
     */
    @Select("select * from permission where deleted = 1")
    Optional<List<Permission>> selectAllDeleted();

    /**
     * 新增权限
     * @param permission
     */
    @Insert("insert into permission(name, created_at, updated_at, deleted, description)" +
            "values (#{name}, now(), now(), 0, #{description})")
    void insert(Permission permission);

    /**
     * 根据id更新权限
     * @param permission
     */
    @Update("update permission set name = #{name},url = #{url},description= #{description},updated_at = now() where id = #{id}")
    void updateById(Permission permission);

    /**
     * 据id逻辑删除权限
     * @param id
     */
    @Update("update permission set deleted = 1 where id = #{id}")
    void deleteById(Long id);

}
