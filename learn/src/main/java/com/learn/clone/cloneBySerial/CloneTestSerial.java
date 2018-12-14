package com.learn.clone.cloneBySerial;

import com.learn.clone.Email;
import com.learn.clone.Person;

/**
 * @author gonghe.hogan
 */

public class CloneTestSerial {

    public static void main(String[] args) {
        Email email = new Email();
        email.setHead("开会通知");
        email.setMessage("请与今天12:30到二会议室参加会议");

        PersonSerial person1 = new PersonSerial("zhangsan", email);

        final PersonSerial person2 = CloneUtils.cloneBySerial(person1);
        person2.getEmail().setMessage("请与今天15:30到二会议室参加会议");


        final PersonSerial person3 = CloneUtils.cloneBySerial(person1);
        person3.setName("wangwu");

        person1.sendEmail();
        person2.sendEmail();
        person3.sendEmail();

    }
}
