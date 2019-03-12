package com.learn.reflection;

/**
 * @author gonghe.hogan
 */

public class Apple {

    private int size;
    private String name;

    public Apple() {
    }

    private Apple(int size) {
        this.size = size;
    }

    public Apple(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
