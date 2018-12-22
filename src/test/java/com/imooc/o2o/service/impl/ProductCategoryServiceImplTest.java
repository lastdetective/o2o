package com.imooc.o2o.service.impl;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.service.ProductCategoryService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ProductCategoryServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
public class ProductCategoryServiceImplTest extends BaseTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getProductCategoryList(long shopId)
     */
    @Test
    public void testGetProductCategoryList() throws Exception {

        List<ProductCategory> productCategories = productCategoryService.getProductCategoryList(32L);
        System.out.println(productCategories);
    }


} 
