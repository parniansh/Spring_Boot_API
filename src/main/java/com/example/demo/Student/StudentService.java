package com.example.demo.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public void deleteStudent(Long id) {
        boolean studentExists = studentRepository.existsById(id);
        if(!studentExists){
            throw new IllegalStateException(
                    "Student with Id "+ id + " does not exist");
        }
        studentRepository.deleteById(id);

    }

    @Transactional
    public void updateStudent(long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(
                        "student with id " + id + "does not exist"));
        if(name != null && name.length() > 0 && !Objects.equals(student.getEmail(),email)){
            student.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
            boolean emailExist = studentRepository.findByEmail(email).isPresent();
            if(emailExist){
                throw new IllegalStateException("Email is already taken");
            }
            student.setEmail(email);
        }
    }

//    public void deleteStudent(Student student) {
//        studentRepository.delete(student);
//    }
}
