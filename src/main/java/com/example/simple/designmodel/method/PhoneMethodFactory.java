package com.example.simple.designmodel.method;

import com.example.simple.designmodel.product.Phone;
import com.example.simple.designmodel.product.Product;

/**
 * @Author: DruidQiang
 * @Date: 2021/6/19 10:46 上午
 */
public class PhoneMethodFactory implements MethodFactory{
    @Override
    public Product getProduct() {
        return new Phone();
    }
}
