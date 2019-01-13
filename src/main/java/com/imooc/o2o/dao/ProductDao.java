package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ProductDao {
    int insertProduct(Product product);


    int deleteProduct(@Param("productId") long productId,
                      @Param("shopId") long shopId);

    Product queryProductById(long productId);

    int updateProduct(Product product);

    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    int queryProductCount(@Param("productCondition") Product productCondition);
}
