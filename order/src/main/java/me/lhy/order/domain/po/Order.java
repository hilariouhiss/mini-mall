package me.lhy.order.domain.po;


import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import me.lhy.order.Status;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description="主键")
    private Long id;

    @Schema(description="订单号")
    private String orderNo;

    @Schema(description="用户id")
    private Long userId;

    @Schema(description="总金额")
    private BigDecimal totalAmount;

    @Schema(description="订单状态")
    private Status status;

    @Schema(description="创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description="更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description="删除标识")
    @TableLogic
    private Boolean deleted;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }
}
