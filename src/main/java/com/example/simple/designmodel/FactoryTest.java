package com.example.simple.designmodel;

import com.example.simple.designmodel.abstractfactory.AbstractFactory;
import com.example.simple.designmodel.abstractfactory.IpadMainFactory;
import com.example.simple.designmodel.abstractfactory.PhoneMainFactory;
import com.example.simple.designmodel.method.IPadMethodFactory;
import com.example.simple.designmodel.method.MethodFactory;
import com.example.simple.designmodel.method.PhoneMethodFactory;
import com.example.simple.designmodel.product.Product;
import com.example.simple.designmodel.simple.SimpleFactory;

/**
 * @Author: DruidQiang
 * @Date: 2021/6/19 10:27 上午
 */
public class FactoryTest {
    public static void main(String[] args) {

        // 简单工厂  不满足开闭
        Product product = SimpleFactory.getProduct(Constans.PHONE);
        product.productInfo();

        // 工厂方法 满足开闭 无法生产多层级产品
        MethodFactory ipadFactory = new IPadMethodFactory();
        MethodFactory phoneFactory = new PhoneMethodFactory();

        ipadFactory.getProduct().productInfo();
        phoneFactory.getProduct().productInfo();


        // 抽象工厂，满足开闭，生产复杂产品
        AbstractFactory ipadMainFactory = new IpadMainFactory();
        AbstractFactory phoneMainFactory = new PhoneMainFactory();

        ipadMainFactory.getDigitalProduct().productInfo();
        ipadMainFactory.getArtificialProduct().artificialInfo();

        phoneMainFactory.getDigitalProduct().productInfo();
        phoneMainFactory.getArtificialProduct().artificialInfo();
    }
}
