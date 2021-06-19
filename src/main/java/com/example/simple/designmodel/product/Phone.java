package com.example.simple.designmodel.product;

/**
 * @Author: DruidQiang
 * @Date: 2021/6/19 10:22 上午
 */
public class Phone implements Product{
    @Override
    public void productInfo() {
        System.out.println("我是手机产品");
    }
}
