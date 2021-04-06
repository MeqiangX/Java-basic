package com.example.simple.model;

import lombok.Builder;

import java.util.Arrays;

public class Account {

    private double[] account;

    public Account(int size,double initialValue){
        account = new double[size];
        Arrays.fill(account,initialValue);
    }

    public double getTotal(){
        double t = 0;
        for (double v : account) {
            t += v;
        }
        return t;
    }

    /**
     * 转账业务
     * @param from
     * @param to
     * @param amount
     */
    public void transfer(int from,int to,double amount){

    }

}
