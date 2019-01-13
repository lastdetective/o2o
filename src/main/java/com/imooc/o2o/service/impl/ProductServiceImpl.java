package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
            throws ProductOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {

            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            // 默认为上架的状态
            product.setEnableStatus(1);
            // 若商品缩略图不为空则天剑
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            try {
                //
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum < 1) {
                    throw new ProductOperationException("创建商品失败");
                }

            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败" + e.toString());
            }
            if (productImgList != null && productImgList.size() > 0) {
                addProductImgList(product, productImgList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {

        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        int count = productDao.queryProductCount(productCondition);
        ProductExecution productExecution = new ProductExecution();
        productExecution.setProductList(productList);
        productExecution.setCount(count);
        return productExecution;
    }

    @Override
    public Product getProductById(long productId) {

        return productDao.queryProductById(productId);
    }

    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException {
        if (product == null || product.getProductId() == null) {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
        product.setLastEditTime(new Date());
        // 如果有缩略图存在
        if (thumbnail != null) {
            // 先删除原来的照片
            Product tempProduct = productDao.queryProductById(product.getProductId());
            if (tempProduct.getImgAddr() != null) {
                ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
            }
            //
            addThumbnail(product, thumbnail);
        }
        // 如果有新的详情图片存在，则先删除后，后添加
        if (productImgHolderList != null && productImgHolderList.size() > 0) {
            List<ProductImg> oldProductImgList = productImgDao.queryProductImgList(product.getProductId());
            // 如果之前有照片，则将之前的照片删除
            if (oldProductImgList != null && oldProductImgList.size() > 0) {
                oldProductImgList.stream().forEach(oldProductImg -> {
                    ImageUtil.deleteFileOrPath(oldProductImg.getImgAddr());
                });
                productImgDao.deleteProductImgByProductId(product.getProductId());
                addProductImgList(product, productImgHolderList);
            }
        }
        // 更新product的其他信息

        try {
            int effectedNum = productDao.updateProduct(product);
            if (effectedNum < 1) {
                throw new ProductOperationException("更新商品信息失败");
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);

        } catch (Exception e) {
            throw new ProductOperationException("更新产品信息失败" + e.toString());
        }
    }

    private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
        String destination = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();

        // 遍历图片并存入 ProductImg 实体类
        productImgHolderList.stream().forEach(productImgHolder -> {
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder, destination);
            ProductImg productImg = ProductImg.builder()
                    .imgAddr(imgAddr)
                    .productId(product.getProductId())
                    .createTime(new Date()).build();
            productImgList.add(productImg);
        });

      /*  for (ImageHolder productImgHolder : productImgHolderList) {
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder, destination);
            ProductImg productImg = ProductImg.builder()
                    .imgAddr(imgAddr)
                    .productId(product.getProductId())
                    .createTime(new Date()).build();
            productImgList.add(productImg);

        }*/
        // 如果有图片 就批量添加图片
        if (productImgList != null && productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum < 1) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品图片出错" + e.toString());
            }
        }
    }


    /**
     * 添加缩略图
     *
     * @param product
     * @param thumbnail
     */

    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String destination = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, destination);
        product.setImgAddr(thumbnailAddr);
    }

}
