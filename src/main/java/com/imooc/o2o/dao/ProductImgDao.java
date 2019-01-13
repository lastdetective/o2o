package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {

    /**
     * 批量添加商品的图片
     *
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);


    /**
     * 删除图片
     *
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);


    /**
     * 查询图片列表
     *
     * @param productId
     * @return
     * @since 20180929
     */
    List<ProductImg> queryProductImgList(long productId);


}
