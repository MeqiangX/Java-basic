package com.example.simple.designmodel.method;

import com.example.simple.designmodel.product.Product;

/**
 * @Author: DruidQiang
 * 工厂方法，简单工厂如果要适用多种产品，则需要修改工厂，违背了开闭原则，
 * 对工厂进行抽象，面向工厂接口，不修改原代码的情况下，实现扩展，满足开闭原则
 * @Date: 2021/6/19 10:38 上午
 */
public interface MethodFactory {
    Product getProduct();
}
