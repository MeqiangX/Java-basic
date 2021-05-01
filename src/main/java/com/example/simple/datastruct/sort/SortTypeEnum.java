package com.example.simple.datastruct.sort;

import lombok.Data;

/**
 * @Author: DruidQiang
 * 排序类型Enum
 * @Date: 2021/4/28 11:15 下午
 */
public enum SortTypeEnum {

    BUBBLE(1,"bubble"),
    INSERT(2,"insert"),
    CHOOSE(3,"choose"),
    MERGE(4,"merge"),
    QUICK(5,"quick");



    private Integer key;

    private String name;

    private SortTypeEnum(Integer key,String name){
        this.key = key;
        this.name = name;
    }

    public static SortTypeEnum getByKey(Integer key){
        for (SortTypeEnum value : SortTypeEnum.values()) {
            if (value.key.intValue() == key.intValue()){
                return value;
            }
        }
        return null;
    }

    public Integer getKey(){
        return key;
    }

    public String getValue(){
        return name;
    }


}
