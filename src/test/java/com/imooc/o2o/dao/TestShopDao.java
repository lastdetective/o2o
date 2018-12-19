package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestShopDao extends BaseTest {


    @Autowired
    private ShopDao shopDao;

    @Test
    public void testgetShopList() {

        Shop shop = new Shop();
     //   shop.setShopId(57L);
      //  shop.setShopName("梗");
       // Area area  = new Area();
       // area.setAreaId(4);
     //   shop.setArea(area);
     //   shop.setEnableStatus(1);
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(3L);
        shop.setOwner(personInfo);
        List shopList = shopDao.queryShopList(shop,0,10);
        System.out.println(shopList);

    }
}
