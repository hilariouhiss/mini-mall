package me.lhy.user.mapper;

import me.lhy.user.domain.po.UserInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

public interface UserInfoMapper {

    /**
     * TODO: 分页查询所有未被删除的用户详情
     * @return List<UserInfo>
     */
    @Select("select * from user_info where deleted = 0")
    Optional<List<UserInfo>> selecrAll();

    /**
     * 根据id查询未被删除的用户详情
     * @param id
     * @return UserInfo
     */
    @Select("select * from user_info where id = #{id} and deleted = 0")
    Optional<UserInfo> selectById(Long id);

    /**
     * 根据userId查询未被删除的用户详情
     * @param userId
     * @return UserInfo
     */
    @Select("select * from user_info where user_id = #{userId} and deleted = 0")
    Optional<UserInfo> selectByUserId(Long userId);

    /**
     * 查询所有被删除的用户详情
     * @return List<UserInfo>
     */
    @Select("select * from user_info where deleted = 1")
    Optional<List<UserInfo>> selectAllDeleted();
}
