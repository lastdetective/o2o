package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.HeadLine;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static junit.framework.TestCase.assertEquals;

import java.util.List;

public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testQueryHeadLine() {
        HeadLine headLineCondition = new HeadLine();
        List<HeadLine> headLineList = headLineDao.queryHeadLine(headLineCondition);
        assertEquals(4,headLineList.size());

    }
}
