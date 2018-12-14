package com.learn.mysql.service;

import com.learn.mysql.domain.Student;
import com.learn.mysql.dto.StudentDto;
import com.learn.mysql.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gonghe.hogan
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class StudentService {


    private volatile boolean gettingStudentNum = false;
    private AtomicInteger atomicInteger = new AtomicInteger(-1);

    @Autowired
    private StudentRepository studentRepository;

    @PostConstruct
    private void initNum(){
        Integer maxNum = studentRepository.findMaxNum();
        if (maxNum == null){
            atomicInteger.getAndSet(0);
        }else {
            atomicInteger.getAndSet(maxNum);
        }
    }

    private Integer getNumByAtomic(){
            return atomicInteger.incrementAndGet();
    }

    private synchronized Long getNum(){

        while (!gettingStudentNum){
            gettingStudentNum = true;
            Integer maxNum = studentRepository.findMaxNum();
            if (maxNum == null){
                 maxNum = 0;
            }
            gettingStudentNum = false;
            return maxNum+1L;
        }
        return -1L;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createStudent(StudentDto studentDto){

        Long num = (long)getNumByAtomic();
        studentRepository.save(convertToStudent(studentDto, num));
        try {
//            模拟其他创建工作
            TimeUnit.MILLISECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateStudent(Student student, StudentDto studentDto){
        student.setAge(studentDto.getAge());
        student.setSex(student.getSex());
        student.setUpdateTime(new Date());
        studentRepository.save(student);
        try {
//            模拟其他更新工作
            TimeUnit.MILLISECONDS.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Student convertToStudent(StudentDto studentDto, Long num){
        return Student.builder()
                .name(studentDto.getName())
                .sex(studentDto.getSex())
                .age(studentDto.getAge())
                .num(num)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }
}
