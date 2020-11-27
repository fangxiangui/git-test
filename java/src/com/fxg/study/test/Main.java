package com.fxg.study.test;

import com.fxg.study.entity.Student;
import com.fxg.study.polymorphic.Animal;
import com.fxg.study.polymorphic.Cat;
import com.fxg.study.polymorphic.Dog;
import com.fxg.study.reflection.JdbcTools;
import com.fxg.study.reflection.MyQuery;
import com.fxg.study.staticclass.InnerClass;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author barry
 * @date 2020-11-24 15:05
 */
public class Main implements Serializable{
    class Test implements Serializable{
        public int i ;
    }

    public static void main(String[] args) {

        //多态测试 polymorphicTest();

        //序列化测试 serializableTest();

        //反射测试
        reflectionTest();
    }

    private  static void reflectionTest(){
        Connection connection = JdbcTools.getConnection();
        MyQuery query = new MyQuery();
        String sql = "select * from student where id = ?";
        Student student = (Student) query.query(connection,sql,Student.class,1);
        System.out.printf(student.toString());
    }

    private static void polymorphicTest() {
        // 多态，向上转型只能调用子类重写父类的方法，否则调用父类方法
        System.out.println("===============向上转型==================");
        Animal dog = new Dog();
        dog.call();
        Animal cat = new Cat();
        cat.eat();

        // 多态，向下转型 错误示范 父类永远是比子类功能小，不允许转父亲不可能装儿子
        System.out.println("===============向下转型==================");
        Dog dog1 = (Dog) new Animal();
        dog1.bite();
        dog1.call();
        Cat cat1 = (Cat) new Animal();
        cat1.eat("鱼");

        // 多态，向下转型 正确示范 儿子装父亲在做回儿子是可以的
        System.out.println("===============向下转型(只能儿子装父亲)==================");
        Dog dog2 = (Dog) dog;
        dog2.bite();
        dog2.call();
        Cat cat2 = (Cat) cat;
        cat2.eat("草鱼");

        InnerClass ic = new InnerClass();
        List<InnerClass.Inner> inners = new ArrayList<>();
        InnerClass.Inner inner = new InnerClass.Inner();
        inner.setName("测试");
        inners.add(inner);
        ic.setInners(inners);
        for (InnerClass.Inner i : inners){
            System.out.printf("name:"+i.getName());
        }
    }

    private static void serializableTest() {
        try {
            ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("test.obj"));
            Main main = new Main();
            Test t = main.new Test();
            t.i=1;
            obj.writeObject(t);
            obj.flush();
            t.i=2;
            obj.writeObject(t);
            obj.flush();
            ObjectInputStream iObj = new ObjectInputStream(new FileInputStream("test.obj"));
            Test test1 = (Test) iObj.readObject();
            Test test2 = (Test) iObj.readObject();
            System.out.println("t1==: "+test1.i);
            System.out.println("t1==: "+test2.i);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
