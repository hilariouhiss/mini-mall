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
public class Permission implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description="主键")
    private Long id;
    @Schema(description="权限名")
    private String name;
    @Schema(description="权限url")
    private String url;
    @Schema(description="权限描述")
    private String description;
    @Schema(description="创建时间")
    private Date createdAt;
    @Schema(description="更新时间")
    private Date updatedAt;
    @Schema(description="是否删除")
    private Boolean deleted;
}
