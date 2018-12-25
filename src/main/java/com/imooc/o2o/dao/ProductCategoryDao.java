package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2018/11/4.
 */
public interface ProductCategoryDao {
    /**
     * 通过shopId返回店铺下的 所有商品类别
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    int batInsertPoductCategory(List<ProductCategory> productCategoryList);

    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,
                             @Param("shopId") long shopId);
}
