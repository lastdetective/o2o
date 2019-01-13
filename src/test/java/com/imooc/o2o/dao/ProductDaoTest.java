package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.util.PageCalculator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import static junit.framework.TestCase.assertEquals;

@Slf4j
public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;


    @Test
    public void testUpdateProduct() {

        Product product = new Product();

        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(15L);

        Shop shop = new Shop();
        shop.setShopId(20L);

        product.setProductId(7L);
        product.setShop(shop);
        product.setProductCategory(productCategory);

        product.setProductName("缤纷拿铁");
        int effectedNum = productDao.updateProduct(product);
        assertEquals(effectedNum, 1);
    }

    @Test
    public void testQueryProductById() {
        Product product = productDao.queryProductById(4L);
        log.info(product.toString());
    }

    @Test
    public void testDeleteProduct() {
        int deletedNum = productDao.deleteProduct(6L, 20L);
        assertEquals(deletedNum, 1);
    }

    @Test
    public void testQueryProductList() {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(16L);
        product.setShop(shop);
        int pageIndex = 1;
        int pageSize = 5;
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(product, pageIndex, pageSize);
        assertEquals(5, productList.size());
    }

    @Test
    public void testQueryProductListCount() {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(16L);
        product.setShop(shop);

        int count = productDao.queryProductCount(product);
        assertEquals(7, count);
    }

    @Test
    public void testUpdateProductCategoryToNull() {
        productDao.updateProductCategoryToNull(6L);
    }
}

