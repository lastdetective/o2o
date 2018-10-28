package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.web.shopadmin.ShopManagementController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;





    @Test
    public void testQueryArea() {
        List<Area> areaList = areaDao.queryArea();
        System.out.println(areaList.size());
    }

    /*@Test
    public void testQueryAreaService() {
        List<Area> areaList = areaService.getAreaList();
        System.out.println(areaList.size());
    }*/
}
