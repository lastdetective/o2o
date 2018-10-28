package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ShopDaoTest extends BaseTest {


    @Autowired
    private ShopDao shopDao;

    @Test
    public void testInsertShop() {
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

        shop.setShopName("川菜馆");
        shop.setShopDesc("这里是最正宗的川菜");
        shop.setShopAddr("陕西省西安市金花南路5号");
        shop.setPhone("18717341807");
        shop.setShopImg("aaa.jpg");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("继续审核中");

        System.out.println(shopDao.insertShop(shop));


    }

    @Test
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(31L);
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(2L);

        shop.setOwner(owner);
        shop.setShopCategory(shopCategory);
        shop.setArea(area);

        shop.setShopName("川菜馆");
        shop.setShopDesc("这里是最正宗的川菜");
        shop.setShopAddr("陕西省西安市金花南路5号");
        shop.setPhone("18717341807");
        shop.setShopImg("aaa.jpg");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("继续审核中");

        System.out.println(shopDao.updateShop(shop));
    }
}
