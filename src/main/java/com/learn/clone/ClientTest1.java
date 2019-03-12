package com.learn.clone;

/**
 * @author gonghe.hogan
 */

public class ClientTest1 {


    public static void main(String[] args) {
        Email email = new Email();
        email.setHead("开会通知");
        email.setMessage("请与今天12:30到二会议室参加会议");

        Person person1 = new Person("zhangsan", email);

        final Person person2 = person1.clone();
        person2.setName("lisi");
        person2.getEmail().setMessage("请与今天15:30到二会议室参加会议");

        final Person person3 = person1.clone();
        person3.setName("wangwu");

        person1.sendEmail();
        person2.sendEmail();
        person3.sendEmail();
    }
}
