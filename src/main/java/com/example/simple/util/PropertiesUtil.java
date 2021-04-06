package com.example.simple.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 属性映射
 * 是值和键都是字符串的特殊 映射(properties map) 映射表可以存在文件，也能从文件中加载（配置文件）
 * 实现属性映射的平台称为Properties 通常用于程序的特殊配置项
 *
 * Properties本质上是map 只是比较特殊，存储加载是在文件中
 */
@Component
public class PropertiesUtil {

    /**
     * 静态单例 加载一次
     */
    private static Properties properties = new Properties();

    /**
     * 创建空配置项
     * @return
     */
    public Properties initProperties(){
        if (null == properties){
            properties = new Properties();
        }
        return properties;
    }

    /**
     * 从输入流中获取配置，读取文件，初始化加载配置一次
     * @param in
     */
    public void load(InputStream in){
        try {
            initProperties().load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 存储
     * @param out
     * @param keyAndMap
     */
    public void store(OutputStream out,String keyAndMap){
        try {
            properties.store(out,keyAndMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 从配置映射中获取值 by key
     *
     * @param key
     * @return
     */
    public String getProperty(String key){
        return properties.getProperty(key);
    }

    /**
     *
     * search value by key return default where key does not exists
     * @param key
     * @param defaultValue
     * @return
     */
    public String getProperties(String key,String defaultValue){
        return properties.getProperty(key,defaultValue);
    }


}
