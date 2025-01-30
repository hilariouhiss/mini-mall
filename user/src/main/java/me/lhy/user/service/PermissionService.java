package me.lhy.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.lhy.user.domain.po.Permission;

public interface PermissionService {
    /**
     * 分页查询所有权限
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return 权限列表
     */
    Page<Permission> selectAll(Integer currentPage, Integer pageSize);

    /**
     * 根据id查询权限
     * @param id 权限id
     * @return 权限
     */
    Permission selectById(Long id);

    /**
     * 根据权限名称查询权限
     * @param name 权限名称
     * @return 权限
     */
    Permission selectByName(String name);

    /**
     * 添加权限
     * @param permission 权限
     */
    void add(Permission permission);

    /**
     * 根据id删除权限
     * @param id 权限id
     */
    void deleteById(Integer id);

    /**
     * 根据id更新权限
     * @param permission 权限
     */
    void updateById(Permission permission);
}
