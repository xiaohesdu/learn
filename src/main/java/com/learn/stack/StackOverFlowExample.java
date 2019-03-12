package com.learn.stack;

/**
 * @author gonghe.hogan
 */

public class StackOverFlowExample {

    public static class Car{
        int price;
        String name;

        public Car(int price, String name) {
            this.price = price;
            this.name = name;
        }
    }

    public void init(int i, int c){
        try{
            final Car car = new Car(i, "name" + i);
            init(++i, ++c);
        }catch (Throwable e){
//            System.out.println(e.getMessage());
            System.out.println("stack length : " + i);
        }

    }

    public static void main(String[] args) {
        new StackOverFlowExample().init(0,10000);
    }
}
