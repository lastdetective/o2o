package com.imooc.o2o.service;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;

import java.util.List;

/**
 * Created by Administrator on 2018/11/4.
 */
public interface ProductCategoryService {
    /**
     * 查询制定某个店铺下的所有类别信息
     *
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    ProductCategoryExecution batchAddProductCategoryList(List<ProductCategory> productCategoryList);

    ProductCategoryExecution deleteProductCategory(long shopId, long productCategoryId);

    List<ProductCategory> getByShopId(long shopId);
}
