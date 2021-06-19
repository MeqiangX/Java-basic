package com.example.simple.designmodel.method;

import com.example.simple.designmodel.product.IPad;
import com.example.simple.designmodel.product.Product;

/**
 * @Author: DruidQiang
 * @Date: 2021/6/19 10:45 上午
 */
public class IPadMethodFactory implements MethodFactory{
    @Override
    public Product getProduct() {
        return new IPad();
    }
}
