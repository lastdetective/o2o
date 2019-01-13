package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductImg;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * 这样写 可以让这个方法称为一个静态的方法，从而简化书写
 */
import static junit.framework.TestCase.assertEquals;

public class ProductImgDaoTest extends BaseTest {
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testBatchInsertProductImg() throws Exception {
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1的地址");
        productImg1.setImgDesc("图片1desc");
        productImg1.setPriority(4);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(4L);

        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("图片2的地址");
        productImg2.setImgDesc("图片2desc");
        productImg2.setPriority(2);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(4L);
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(effectedNum, 2);
    }

    @Test
    public void testQueryProductImglist() {
        List<ProductImg> productImgList = productImgDao.queryProductImgList(7L);
        assertEquals(productImgList.size(), 3);
    }

    @Test
    public void testDeleteProductImgByProductId() throws Exception {
        int deletedNum = productImgDao.deleteProductImgByProductId(7L);
        assertEquals(deletedNum, 3);
    }


}
