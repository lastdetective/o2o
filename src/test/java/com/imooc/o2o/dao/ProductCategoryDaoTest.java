package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ProductCategoryDaoTest extends BaseTest {


    @Autowired
    private ProductCategoryDao productCategoryDao;


    @Test
    public void testQueryProductCategoryList() {
        long shopId = 32L;
        List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList(shopId);
        System.out.println(productCategories);

    }

    @Test
    public void testBatchInsertProductCategory() {
        ProductCategory productCategory1 = ProductCategory.builder().productCategoryName("测试batch1").priority(3).shopId(31L).createTime(new Date()).build();
        ProductCategory productCategory2 = ProductCategory.builder().productCategoryName("测试batch2").priority(2).shopId(31L).createTime(new Date()).build();
        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(productCategory1);
        productCategoryList.add(productCategory2);
        int effectNum = productCategoryDao.batInsertPoductCategory(productCategoryList);
        assertEquals(effectNum, 2);
    }


    @Test
    public void testDeleteProductCategory() {
        long shopId = 31;
        long productCategory = 5L;
        System.out.println(productCategoryDao.deleteProductCategory(productCategory, shopId));
    }
}
