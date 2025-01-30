package me.lhy.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import me.lhy.user.domain.po.Permission;
import me.lhy.user.mapper.PermissionMapper;
import me.lhy.user.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service("permissionService")
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionMapper permissionMapper;

    @Override
    public Page<Permission> selectAll(Integer currentPage, Integer pageSize) {
        Page<Permission> page = new Page<>(currentPage, pageSize);
        return permissionMapper.selectPage(page, null);
    }

    @Override
    public Permission selectById(Long id) {
        return permissionMapper.selectById(id)
                .orElseThrow(
                () -> new NoSuchElementException("permission not found")
        );
    }

    @Override
    public Permission selectByName(String name) {
        return permissionMapper.selectOne(new LambdaQueryWrapper<>(

        ));
    }

    @Override
    public void add(Permission permission) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void updateById(Permission permission) {

    }
}
