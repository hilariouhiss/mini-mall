package me.lhy.commodity.domain.po;


import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description="商品实体类")
public class Commodity implements Serializable {

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Schema(description="商品id")
    private Integer id;

    @Schema(description="商品名称")
    private String name;

    @Schema(description="商品价格")
    private BigDecimal price;

    @Schema(description="商品库存")
    private Integer stock;

    @Schema(description="商品品牌")
    private String brand;

    @Schema(description="商品类别")
    private String category;

    @Schema(description="商品详情")
    private String description;

    @Override
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
