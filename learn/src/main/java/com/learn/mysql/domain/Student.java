package com.learn.mysql.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author gonghe.hogan
 */

@Entity(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    //name is unique(if name repeat, then it is update)
    String name;
    String sex;
    Integer age;

    //num must be increase, like asset num.(can not be update)
    Long num;

    Date createTime;

    Date updateTime;

}
