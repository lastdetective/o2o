package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;

import java.util.List;

public interface ProductDao {
    int insertProduct(Product product);



    int deleteProductImgByProductId(long productId);
}
