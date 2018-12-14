package com.learn.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @author gonghe.hogan
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    //name is unique(if name repeat, then it is update)
    String name;
    String sex;
    Integer age;


}
