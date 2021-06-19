package com.example.simple.designmodel.abstractfactory;

import com.example.simple.designmodel.product.ArtificialProduct;
import com.example.simple.designmodel.product.Phone;
import com.example.simple.designmodel.product.PhoneBracket;
import com.example.simple.designmodel.product.Product;

/**
 * @Author: DruidQiang
 * phone总工厂 生产phone+周边
 * @Date: 2021/6/19 11:14 上午
 */
public class PhoneMainFactory implements AbstractFactory{
    @Override
    public Product getDigitalProduct() {
        return new Phone();
    }

    @Override
    public ArtificialProduct getArtificialProduct() {
        return new PhoneBracket();
    }
}
