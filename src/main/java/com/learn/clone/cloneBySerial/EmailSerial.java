package com.learn.clone.cloneBySerial;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gonghe.hogan
 */
@Data
public class EmailSerial implements Serializable {
    String head;
    String message;

    public EmailSerial() {
    }

    public EmailSerial(String head, String message) {
        this.head = head;
        this.message = message;
    }

}
