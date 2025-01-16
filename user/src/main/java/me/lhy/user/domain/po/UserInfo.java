package me.lhy.user.domain.po;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
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
}
