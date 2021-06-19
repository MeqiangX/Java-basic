package com.example.simple.designmodel.abstractfactory;

import com.example.simple.designmodel.product.ArtificialProduct;
import com.example.simple.designmodel.product.Product;

/**
 * @Author: DruidQiang
 * 抽象工厂，工厂方法的升级，解决工厂方法的产品单一问题，现实情况下的 工厂的产品 涉及多个类别，需要满足实际场景，推出抽象工厂模式
 * @Date: 2021/6/19 11:00 上午
 */
public interface AbstractFactory {

    /****
     * client
     *   ｜
     *   AbstractFactory
     *   ｜
     *   Product/ArtificialProduct
     *
     *
     *
     * @return
     */

    Product getDigitalProduct();

    ArtificialProduct getArtificialProduct();

}
