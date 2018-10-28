package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(2L);

        shop.setOwner(owner);
        shop.setShopCategory(shopCategory);
        shop.setArea(area);

        shop.setShopName("岐山臊子面馆");
        shop.setShopDesc("追正宗的岐山面");
        shop.setShopAddr("岐山县蔡家坡火车站对面");
        shop.setPhone("12131356466");
        shop.setShopImg("aaa.jpg");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("还在审核");

        File shopImg  = new File("D:\\feidele.jpg");
        System.out.println(shopService.addShop(shop,shopImg));

    }

}
