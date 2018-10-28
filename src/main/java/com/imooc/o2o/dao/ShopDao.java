package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    /**
     * 分页查询店铺: 可输入的条件有：
     * 店铺名（模糊），店铺状态，店铺类别，区域ID，owner
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
                             @Param("pageSize") int pageSize,
                             @Param("pageNum") int pageNum);

    /**
     * 新增店铺
     *
     * @param shop
     * @return
     */
    int insertShop(Shop shop);


    /**
     * 更新店铺
     */
    int updateShop(Shop shop);


    Shop queryByShopId(long shopId);
}
