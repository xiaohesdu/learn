package com.learn.mysql.controller;

import com.learn.mysql.domain.Student;
import com.learn.mysql.dto.StudentDto;
import com.learn.mysql.repository.StudentRepository;
import com.learn.mysql.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author gonghe.hogan
 */

@Controller
@RestController
@Slf4j
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    private CopyOnWriteArraySet<String> nameSet = new CopyOnWriteArraySet();

    @PostMapping("/student/update")
    public void updateStudent(@RequestBody StudentDto studentDto){
//        验证2个方面：1） 在创建时，num必须是递增的，且不能重复（而且不是依赖数据库的唯一约束）
//        2）必须通过name判断学生是否存在，如果name找不到才是创建，name找到了就是更新。不允许重复创建
        synchronized (StudentController.class){
            final Student student = studentRepository.findByName(studentDto.getName());
            if (student == null){
                studentService.createStudent(studentDto);
            }else {
                studentService.updateStudent(student, studentDto);
            }
        }
    }

    @PostMapping("/student/update/async")
    public void updateStudent2(@RequestBody StudentDto studentDto){
//        验证2个方面：1） 在创建时，num必须是递增的，且不能重复（而且不是依赖数据库的唯一约束）
//        2）必须通过name判断学生是否存在，如果name找不到才是创建，name找到了就是更新。不允许重复创建
//        synchronized (StudentController.class){
        final String name = studentDto.getName();
        synchronized (StudentController.class){
            if (nameSet.contains(name)){
                log.warn("the student name " +  name + " is already been operated, please operate later.");
                return;
            }else {
                nameSet.add(name);
            }
        }

        final Student student = studentRepository.findByName(studentDto.getName());
            if (student == null){
                studentService.createStudent(studentDto);
            }else {
                studentService.updateStudent(student, studentDto);
            }
            nameSet.remove(name);
//        }
    }


    @PostMapping("/students/find/delete")
    public void deleteAllStudentFindThenDelete(){
        final List<Student> students = studentRepository.findAllBySex("female");
        studentRepository.delete(students);
    }
    @PostMapping("/students/just/delete")
    @Transactional(rollbackFor = Exception.class)
    public void deleteAllStudentJustDelete(){
        studentRepository.deleteAllBySex("male");
    }
}
