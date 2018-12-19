package com.imooc.o2o.service.impl;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.service.ShopService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ShopServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮ���� 16, 2018</pre>
 */
public class ShopServiceImplTest extends BaseTest {

    @Autowired
    ShopService shopService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addShop(Shop shop, InputStream shopImgInputStream, String fileName)
     */
    @Test
    public void testAddShop() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getByShopId(Long shopId)
     */
    @Test
    public void testGetByShopId() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: modifyShop(Shop shop, InputStream shopInputStream, String fileName)
     */
    @Test
    public void testModifyShop() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getShopList(Shop shop, int pageIndex, int pageSize)
     */
    @Test
    public void testGetShopList() throws Exception {
        Shop shop = new Shop();
          shop.setShopId(57L);
        //  shop.setShopName("梗");
        // Area area  = new Area();
        // area.setAreaId(4);
        //   shop.setArea(area);
        //   shop.setEnableStatus(1);
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        shop.setOwner(personInfo);
        ShopExecution shopExecution = shopService.getShopList(shop, 0, 10);
        System.out.println(shopExecution);
    }

    /**
     * Method: addShopImg(Shop shop, InputStream shopImgInputStream, String fileName)
     */
    @Test
    public void testAddShopImg() throws Exception {
//TODO: Test goes here... 
    }


} 
