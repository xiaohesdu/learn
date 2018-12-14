package com.learn.clone.cloneBySerial;

import com.learn.clone.Email;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghe.hogan
 */
@Data
public class PersonSerial implements Serializable {
    private String name;

    private Email email;

    public PersonSerial(String name, Email email) {
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

//    这样算是深克隆（如果某个对象字段，它本身的字段又有对象的，还要再new一个出来）
//    序列化方式的话不需要这个方法了
    @Override
    protected PersonSerial clone(){
        PersonSerial person = null;
        try {
            person = (PersonSerial) super.clone();
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
