package com.example.CustomQuery.controller;

import com.example.CustomQuery.model.Student;
import com.example.CustomQuery.service.StudentService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping(value = "create-student")
    public ResponseEntity<String> saveStudent(@RequestBody String studentData){
        Student student = setStudent(studentData);
        int studentId = studentService.saveStudent(student);
        return new ResponseEntity<>("Student saved with id: "+studentId, HttpStatus.CREATED);
    }
    @GetMapping(value = "get-student")
    public ResponseEntity<String> getStudents(@Nullable @RequestParam String studentId){
        JSONArray studentArr = studentService.getStudent(studentId);
        return new ResponseEntity<>(studentArr.toString(), HttpStatus.OK);
    }
    @GetMapping(value = "get-student-by-firstName")
    public Student findByFirstName(@RequestParam(value = "firstName", required = true) String firstName){
        return studentService.findByFirstName(firstName);
    }
    @GetMapping(value = "get-student-by-firstNameAndLastName")
    public Student findByFirstNameAndLastName(@RequestParam(value = "firstName", required = true) String firstName, @RequestParam(value = "lastName", required = true) String lastName){
        return studentService.findByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping(value = "get-allActiveStudents")
    public List<Student> findAllActiveStudent(){
        return studentService.findAllActiveStudent();
    }
    @GetMapping(value = "get-allInactiveStudents")
    public List<Student> findAllInactiveStudent(){
        return studentService.findAllInactiveStudent();
    }
    @GetMapping(value = "get-studentByAgeGreaterThan")
    public List<Student> findByAgeGreaterThan(@RequestParam int age){
        return studentService.findByAgeGreaterThan(age);
    }

    @GetMapping(value = "get-studentByAgeGreaterThanEqual")
    public List<Student> findByAgeGreaterThanEqual(@RequestParam int age){
        return studentService.findByAgeGreaterThanEqual(age);
    }

    @GetMapping(value = "get-studentByAgeLessThanEqual")
    public List<Student> findByAgeLessThanEqual(@RequestParam int age){
        return studentService.findByAgeLessThanEqual(age);
    }

    @GetMapping(value = "get-studentByAgeLessThan")
    public List<Student> findByAgeLessThan(@RequestParam int age){
        return studentService.findByAgeLessThan(age);
    }

    @GetMapping(value = "get-studentByAge")
    public List<Student> findByAge(@RequestParam int age){
        return studentService.findByAge(age);
    }
    @GetMapping(value = "get-studentByFirstName")
    public List<Student> findByFirstNameLike(@RequestParam String firstName){
        return studentService.findByFirstNameLike(firstName);
    }

    @PutMapping(value = "updateStatus/{studentId}")
    public void updateStatus(@PathVariable int studentId){
        studentService.updateStatus(studentId);
    }

    @DeleteMapping(value = "delete-student/{studentId}")
    public ResponseEntity<String> deleteStudentByStudentId(@PathVariable int studentId){
        studentService.deleteStudentByStudentId(studentId);
        return new ResponseEntity<>("Deleted Student", HttpStatus.NO_CONTENT);
    }

    private Student setStudent(String studentData) {
        JSONObject studentObj = new JSONObject(studentData);
        Student student = new Student();
        student.setFirstName(studentObj.getString("firstName"));
        student.setLastName(studentObj.getString("lastName"));
        student.setAge(studentObj.getInt("age"));
        Timestamp currTime = new Timestamp(System.currentTimeMillis());
        student.setAdmissionDate(currTime);
        student.setStatus(studentObj.getBoolean("status"));
        return student;
    }

}
