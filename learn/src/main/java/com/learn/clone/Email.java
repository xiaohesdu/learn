package com.learn.clone;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghe.hogan
 */
@Data
public class Email implements Serializable {
    String head;
    String message;

    public Email() {
    }

    public Email(String head, String message) {
        this.head = head;
        this.message = message;
    }

    public static void main(String[] args) {
        Object o = new Object();
    }
}
