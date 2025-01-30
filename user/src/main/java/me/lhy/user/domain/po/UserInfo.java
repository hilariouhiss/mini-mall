package me.lhy.user.domain.po;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description="主键")
    private Long id;
    @Schema(description="用户id")
    private Long userId;
    @Schema(description="昵称")
    private String nickname;
    @Schema(description="头像URL")
    private String avatar;
    @Schema(description="地址")
    private String address;
    @Schema(description="爱好")
    private String hobby;
    @Schema(description="创建时间")
    private Date createdAt;
    @Schema(description="更新时间")
    private Date updatedAt;
    @Schema(description="是否删除")
    private Boolean deleted;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", address='" + address + '\'' +
                ", hobby='" + hobby + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deleted=" + deleted +
                '}';
    }
}
