package com.fxg.study.test;

import com.fxg.study.polymorphic.Animal;
import com.fxg.study.polymorphic.Dog;
import com.fxg.study.polymorphic.Cat;

/**
 * @author barry
 * @date 2020-11-24 15:05
 */
public class Main {
    public static void main(String[] args) {
        // 多态，向上转型只能调用子类重写父类的方法，否则调用父类方法
        System.out.println("===============向上转型==================");
        Animal dog = new Dog();
        dog.call();
        Animal cat = new Cat();
        cat.eat();

        /*多态，向下转型 错误示范 父类永远是比子类功能小，不允许转父亲不可能装儿子
        System.out.println("===============向下转型==================");
        Dog dog1 = (Dog) new Animal();
        dog1.bite();
        dog1.call();
        Cat cat1 = (Cat) new Animal();
        cat1.eat("鱼");*/

        // 多态，向下转型 正确示范 儿子装父亲在做回儿子是可以的
        System.out.println("===============向下转型(只能儿子装父亲)==================");
        Dog dog2 = (Dog) dog;
        dog2.bite();
        dog2.call();
        Cat cat2 = (Cat) cat;
        cat2.eat("草鱼");



    }
}
