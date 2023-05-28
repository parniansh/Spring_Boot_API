package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private  StudentRepository studentRepository;




    public List<Student> getStudents(){

       return  studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        boolean studentExist = studentRepository.findByEmail(student.getEmail()).isPresent();
        if(studentExist){
            throw new IllegalStateException("Email Already Exist");
        }
        studentRepository.save(student);
//        System.out.println(student);
    }

//    public void deleteStudent(Student student) {
//        studentRepository.delete(student);
//    }
}
