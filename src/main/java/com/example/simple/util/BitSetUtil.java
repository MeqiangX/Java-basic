package com.example.simple.util;

import org.springframework.stereotype.Component;
import sun.jvm.hotspot.utilities.Bits;

import java.util.BitSet;

/**
 * 位集 存放位是以字节的形式存储的，每个位表示1/0，如果要存储boolean型的集合，位集（其实叫做位数组或者位队列更为恰当）
 * 比数组或者列表更加合适，可以存储这种如标志类型的集合列表
 * 位集提供便于读取（get），设置(set)和清除(clear)各个位的接口方法，可以直接取值，如果存储在int或者long类型中，就必须进行另外的繁琐操作
 */
@Component
public class BitSetUtil {

    /**
     * init capacity bitset default=0
     * @param capacity
     * @return
     */
    public BitSet init(int capacity){
        return new BitSet(capacity);
    }

    /**
     * words[wordIndex] |= (1L << bitIndex); // Restores invariants
     * index下标位 设为1
     * @param bitSet
     * @param index
     */
    public void set(BitSet bitSet,int index){
        bitSet.set(index);
    }

    /**
     * return true if bitset[index] = 1 else return false
     * @param bitSet
     * @param index
     * @return
     */
    public boolean get(BitSet bitSet,int index){
        return bitSet.get(index);
    }

}
