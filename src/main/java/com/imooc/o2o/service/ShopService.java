package com.imooc.o2o.service;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exceptions.ShopOperationException;
import jdk.internal.util.xml.impl.Input;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream inputStream, String fileName) throws ShopOperationException;

    Shop getByShopId(Long shopId);


    ShopExecution modifyShop(Shop shop, InputStream shopInputStream, String fileName) throws ShopOperationException;

}
