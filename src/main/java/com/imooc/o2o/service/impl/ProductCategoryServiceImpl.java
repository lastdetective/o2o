package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.exceptions.ProductCategoryOperationException;
import com.imooc.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/4.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    /**
     * 查询制定某个店铺下的所有类别信息
     *
     * @param shopId
     * @return
     */
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategoryList(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                int effectedNum = productCategoryDao.batInsertPoductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("创建店铺类别失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }

            } catch (Exception e) {
                throw new ProductCategoryOperationException("bat insert error" + e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    /**
     * 删除某个商品类别
     *
     * @param shopId
     * @param productCategoryId
     * @return
     */
    @Override
    public ProductCategoryExecution deleteProductCategory(long shopId, long productCategoryId)
            throws ProductCategoryOperationException {
        // 解除 tb_product 里的商品与该 productCategoryId 的关联
        try {
            int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
            if (effectedNum < 0) {
                throw new RuntimeException("商品类别更新失败");
            }

        } catch (Exception e) {
            throw new RuntimeException("商品类别更新失败");
        }
        // 删除该 productCategory
        try {


            int deleteproduct = productCategoryDao.deleteProduct(productCategoryId, shopId);
            int effectNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);

            if (effectNum > 0) {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            } else {
                throw new ProductCategoryOperationException("商品类别删除失败");
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("delete productCategory error" + e.getMessage());
        }

    }

    @Override
    public List<ProductCategory> getByShopId(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }
}
