package com.imooc.o2o.service.impl;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * ProductServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>һ�� 6, 2019</pre>
 */
import static junit.framework.TestCase.assertEquals;
public class ProductServiceImplTest extends BaseTest {
    @Autowired
    private ProductService productService;


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
     */
    @Test
    public void testAddProduct() throws Exception {

        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(20L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(13L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("测试商品20190116");
        product.setProductDesc("测试 desc");
        product.setPriority(22);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());

        // 创建文缩略图的输入流
        File thumbnailFile = new File("D:\\testpic\\thumbnail.jpg");
        InputStream thumbnailInputStream = new FileInputStream(thumbnailFile);
        ImageHolder thumbnailHolder = new ImageHolder();
        thumbnailHolder.setImage(thumbnailInputStream);
        thumbnailHolder.setImageName(thumbnailFile.getName());


        // 创建商品详情的缩略图
        File producImg1 = new File("D:\\testpic\\1.jpg");
        InputStream producImg1InputStream = new FileInputStream(producImg1);
        ImageHolder producImg1Holder = new ImageHolder();
        producImg1Holder.setImage(producImg1InputStream);
        producImg1Holder.setImageName(producImg1.getName());



        File producImg2 = new File("D:\\testpic\\1.jpg");
        InputStream producImg2InputStream = new FileInputStream(producImg1);
        ImageHolder producImg2Holder = new ImageHolder();
        producImg2Holder.setImage(producImg2InputStream);
        producImg2Holder.setImageName(producImg2.getName());

        List<ImageHolder> imageHolderList = new ArrayList<>();
        imageHolderList.add(producImg1Holder);
        imageHolderList.add(producImg2Holder);
        ProductExecution pe = productService.addProduct(product,thumbnailHolder,imageHolderList);
        assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());

    }


    /**
     * Method: addProductImgList(Product product, List<ImageHolder> productImgHolderList)
     */
    @Test
    public void testAddProductImgList() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = ProductServiceImpl.getClass().getMethod("addProductImgList", Product.class, List<ImageHolder>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: addThumbnail(Product product, ImageHolder thumbnail)
     */
    @Test
    public void testAddThumbnail() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = ProductServiceImpl.getClass().getMethod("addThumbnail", Product.class, ImageHolder.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
