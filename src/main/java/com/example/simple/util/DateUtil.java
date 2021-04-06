package com.example.simple.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DateUtil {


    public DateUtil(){

    }

    private DateUtil(String m){

    }
    /**
     *
     * @param obj1
     * @return
     */
    public static String dateTest(Integer obj1){
        LocalDate.now();
        LocalDateTime.now();
        try{
            Class<?> aClass = Class.forName("com.example.simple.util.DateUtil");
            Constructor<?>[] constructors = aClass.getConstructors();

            Method[] declaredMethods = aClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
            }


            for (Constructor<?> constructor : constructors) {
                String s = Modifier.toString(constructor.getModifiers());
                Class<?>[] parameterTypes = constructor.getParameterTypes();

                for (Class<?> parameterType : parameterTypes) {
                    System.out.println("param type:"+parameterType.getTypeName());
                }
                System.out.println(s);
            }
        }catch (Exception e){

        }

        return "success";
    }


    public static void main(String[] args) {
        dateTest(1);
    }

}
