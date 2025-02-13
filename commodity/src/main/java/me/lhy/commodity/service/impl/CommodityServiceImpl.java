package me.lhy.commodity.service.impl;

import me.lhy.commodity.mapper.CommodityMapper;
import me.lhy.commodity.service.CommodityService;
import org.springframework.stereotype.Service;

@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {

    private final CommodityMapper commodityMapper;

    public CommodityServiceImpl(CommodityMapper commodityMapper) {
        this.commodityMapper = commodityMapper;
    }

    /**
     * 获取商品库存
     * @param commodityId 商品 ID
     * @return 商品库存
     */
    @Override
    public Integer getStock(Long commodityId) {
        return commodityMapper.selectById(commodityId).getStock();
    }
}
