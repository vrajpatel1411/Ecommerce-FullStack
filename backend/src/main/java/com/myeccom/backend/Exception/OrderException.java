package com.myeccom.backend.Exception;

import org.aspectj.weaver.ast.Or;
import org.springframework.core.annotation.Order;

public class OrderException extends Exception {

    OrderException(String msg){
        super(msg);
    }
}
