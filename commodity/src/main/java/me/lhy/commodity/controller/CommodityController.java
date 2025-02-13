package me.lhy.commodity.controller;

import me.lhy.commodity.service.CommodityService;
import me.lhy.commons.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    private final CommodityService commodityService;

    public CommodityController(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    @GetMapping("/stock/{id}")
    public Result<Integer> getStock(Long id) {
        return Result.success(commodityService.getStock(id));
    }
}
