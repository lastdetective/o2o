package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exceptions.ProductOperationException;

import java.util.List;

public interface ProductService {
    /**
     * 添加商品信息以及图片处理
     */
    ProductExecution addProduct(
            Product product,
            // 缩略图 将图片名称和图片流合并称为一个类
            // InputStream thumbnail,
            // String thumbnailName,
            ImageHolder thumbnail,
            // List<InputStream> productImgList,
            // List<String> productImgNameList
            List<ImageHolder> productImgList
    ) throws ProductOperationException;

    /**
     * 查询商品列表并分页
     *
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);


    Product getProductById(long ProductId);

    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
            throws ProductOperationException;

}
