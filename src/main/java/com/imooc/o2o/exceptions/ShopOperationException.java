package com.imooc.o2o.exceptions;

import com.imooc.o2o.enums.ShopStateEnum;

public class ShopOperationException extends RuntimeException {
    public ShopOperationException(String msg) {
        super(msg);
    }
}
