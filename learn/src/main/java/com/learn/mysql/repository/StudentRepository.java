package com.learn.mysql.repository;

import com.learn.mysql.domain.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author gonghe.hogan
 */

public interface StudentRepository extends CrudRepository<Student, Long> {

    Student findByName(String name);

    List<Student> findAllBySex(String sex);
    void deleteAllBySex(String sex);

    @Query("select max(o.num) from student o")
    Integer findMaxNum();
}
