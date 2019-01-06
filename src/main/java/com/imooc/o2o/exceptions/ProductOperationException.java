package com.imooc.o2o.exceptions;

import com.imooc.o2o.dto.ProductExecution;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

public class ProductOperationException extends RuntimeException {

    public ProductOperationException(String msg) {
        super(msg);
    }
}
