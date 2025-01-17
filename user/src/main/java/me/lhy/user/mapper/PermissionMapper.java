package me.lhy.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.lhy.user.domain.po.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("select * from permission where id = #{id}")
    Optional<Permission> selectById(Long id);


}
