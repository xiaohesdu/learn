package com.learn.clone;

import lombok.Data;

/**
 * @author gonghe.hogan
 */
@Data
public class Person implements Cloneable{
    private String name;

    private Email email;

    public Person(String name, Email email) {
        this.name = name;
        this.email = email;
    }

//    这个方式是浅克隆，容易导致出错
//    @Override
//    protected  Person clone(){
//        Person person = null;
//        try {
//            person = (Person) super.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//
//        return person;
//    }

    @Override
    protected Person clone(){
        Person person = null;
        try {
            person = (Person) super.clone();
            person.setName(person.name);
            person.setEmail(new Email(person.getEmail().getHead(), person.getEmail().getMessage()));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return person;
    }


    public void sendEmail(){
        System.out.println("姓名:" + name + " , 邮件内容:" + email.getMessage());
    }
}
