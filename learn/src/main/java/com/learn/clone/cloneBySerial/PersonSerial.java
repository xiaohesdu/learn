package com.learn.clone.cloneBySerial;

import com.learn.clone.Email;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghe.hogan
 */
@Data
public class PersonSerial implements Serializable {
    /**
     * 序列化ID是用来控制反序列化能否成功的。
     *
     * 进行反序列化时，JVM会把传来的字节流中的serialVersionUID与本地实体类中的serialVersionUID进行比较，
     * 如果相同则认为是一致的，便可以进行反序列化，否则就会报序列化版本不一致的异常。
     *
     * 如果没有指定的话，会根据对象内容自动的生成一个。这样潜在的风险是需要后续类的结构发生修改，那么反序列时可能会导致serialVersionUID不一致，从而反序列失败。
     */
    private static final long serialVersionUID = 3747630134243407984L;

    private static String type = "PERSON";

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
