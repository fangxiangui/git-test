package com.fxg.study.polymorphic;

/**
 * 猫
 * @author barry
 * @date 2020-11-24 15:01
 */
public class Cat extends Animal{

    public void call(){
        System.out.println("猫都会叫。。。。。。。。。。。。。。。。");
    }

    public void eat(String fish){
        System.out.println("猫吃。。。。。。。。。。。。。。。。"+fish);
    }
}
