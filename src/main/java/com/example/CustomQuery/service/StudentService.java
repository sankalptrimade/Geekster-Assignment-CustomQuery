package com.example.CustomQuery.service;

import com.example.CustomQuery.model.Student;
import com.example.CustomQuery.repository.StudentRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public int saveStudent(Student student) {
        Student studentObj = studentRepository.save(student);
        return studentObj.getStudentId();
    }

    public JSONArray getStudent(String studentId) {
        JSONArray jsonArray = new JSONArray();
        if(null != studentId){
            Student student = studentRepository.findById(Integer.parseInt(studentId)).get();
            JSONObject studentObj = setStudent(student);
            jsonArray.put(studentObj);
        } else {
            List<Student> studentList = studentRepository.findAll();
            for(Student student : studentList){
                JSONObject studentObj = setStudent(student);
                jsonArray.put(studentObj);
            }
        }
        return jsonArray;
    }

    private JSONObject setStudent(Student student) {
        JSONObject studentObj = new JSONObject();
        studentObj.put("studentId", student.getStudentId());
        studentObj.put("firstName", student.getFirstName());
        studentObj.put("lastName", student.getLastName());
        studentObj.put("age", student.getAge());
        studentObj.put("admissionDate", student.getAdmissionDate());
        studentObj.put("status", student.isStatus());
        return studentObj;
    }

    public Student findByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    public Student findByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public void deleteStudentByStudentId(int studentId) {
        studentRepository.deleteStudentByStudentId(studentId);
    }

    public List<Student> findAllActiveStudent() {
        return studentRepository.findAllActiveStudent();
    }

    public List<Student> findAllInactiveStudent() {
        return studentRepository.findAllInactiveStudent();
    }

    public void updateStatus(int studentId) {
        studentRepository.updateStatus(studentId);
    }

    public List<Student> findByAgeGreaterThan(int age) {
        return studentRepository.findByAgeGreaterThan(age);
    }

    public List<Student> findByAgeGreaterThanEqual(int age) {
        return studentRepository.findByAgeGreaterThanEqual(age);
    }
    public List<Student> findByAgeLessThanEqual(int age) {
        return studentRepository.findByAgeLessThanEqual(age);
    }
    public List<Student> findByAgeLessThan(int age) {
        return studentRepository.findByAgeLessThan(age);
    }
    public List<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> findByFirstNameLike(String firstName) {
        return studentRepository.findByFirstNameLike(firstName);
    }
}
