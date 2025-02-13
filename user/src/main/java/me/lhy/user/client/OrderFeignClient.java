package me.lhy.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("order")
public interface OrderFeignClient {
    // 调用 order 模块提供的接口，路径支持参数化（这里传入商品 id）
    @GetMapping("/order/detail/{id}")
    Integer getStock(@PathVariable("id") Long orderId);
}
