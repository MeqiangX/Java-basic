package com.example.simple.thread;

import java.util.concurrent.Phaser;

/**
 * @Author: DruidQiang
 * @Date: 2021/4/12 10:34 下午
 */
public class PhaserSub extends Phaser {

    /**
     * 可以根据业务来定义在每个阶段完成时候 处理的业务
     * 当每一个阶段的所有线程达到时，执行此方法，之后phase+1
     * @param phase
     * @param registeredParties
     * @return
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase){
            case 0:
                System.out.println("预赛完成");
                return false;
            case 1:
                System.out.println("初赛完成");
                return false;
            case 2:
                System.out.println("半决赛完成");
                return false;
            case 3:
                System.out.println("决赛完成");
                return false;
            default:
                return true;
        }
    }
}
