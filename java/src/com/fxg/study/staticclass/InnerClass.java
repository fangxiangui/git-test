package com.fxg.study.staticclass;

import java.util.List;

/**
 * @author barry
 * @date 2020-11-25 16:30
 */
public class InnerClass {

    private String test;

    private List<Inner> inners;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public List<Inner> getInners() {
        return inners;
    }

    public void setInners(List<Inner> inners) {
        this.inners = inners;
    }

    public static class Inner{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
