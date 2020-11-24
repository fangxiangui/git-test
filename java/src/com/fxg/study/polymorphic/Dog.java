package com.fxg.study.polymorphic;

/**
 *  狗--》继承动物
 * @author barry
 * @date 2020-11-24 14:56
 */
public class Dog extends Animal {

    public void call(){
        System.out.println("狗叫汪汪汪。。。。。。。。");
    }

    public void bite(){
        System.out.println("狗会咬人。。。。。。。。。。");
    }

}
