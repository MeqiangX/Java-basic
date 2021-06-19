package com.example.simple.designmodel.simple;

import com.example.simple.designmodel.Constans;
import com.example.simple.designmodel.product.IPad;
import com.example.simple.designmodel.product.Phone;
import com.example.simple.designmodel.product.Product;

/**
 * @Author: DruidQiang
 * 简单工厂模式
 * @Date: 2021/6/19 10:19 上午
 */
public class SimpleFactory {

    public static Product getProduct(String product){
        switch (product){
            case Constans.IPAD:{
                return new IPad();
            }
            case Constans.PHONE:{
                return new Phone();
            }
            default:
                return null;
        }
    }

}
