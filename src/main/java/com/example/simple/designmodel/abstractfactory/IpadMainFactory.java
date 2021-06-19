package com.example.simple.designmodel.abstractfactory;

import com.example.simple.designmodel.product.ArtificialProduct;
import com.example.simple.designmodel.product.IPad;
import com.example.simple.designmodel.product.IPadBracket;
import com.example.simple.designmodel.product.Product;

/**
 * @Author: DruidQiang
 * ipad 总厂 生产ipad+周边
 * @Date: 2021/6/19 11:12 上午
 */
public class IpadMainFactory implements AbstractFactory{
    @Override
    public Product getDigitalProduct() {
        return new IPad();
    }

    @Override
    public ArtificialProduct getArtificialProduct() {
        return new IPadBracket();
    }
}
