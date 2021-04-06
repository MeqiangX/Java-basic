package com.example.simple;

import com.example.simple.util.CollectionFunctions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CollectionDemo {

    public static void main(String[] args) throws Exception{
        Class<?> aClass = Class.forName("com.example.simple.util.CollectionFunctions");
        CollectionFunctions collectionFunctions = (CollectionFunctions) aClass.newInstance();

        collectionFunctions.collectionsFunctions();

    }

}
