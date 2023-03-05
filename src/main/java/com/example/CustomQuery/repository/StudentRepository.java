package com.example.CustomQuery.repository;

import com.example.CustomQuery.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByFirstName(String name);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "select * from tbl_student where first_name like '%:firstName%' and status = 1", nativeQuery = true)
    List<Student> findByFirstNameLike(@Param("firstName") String firstName);

    @Query(value = "select * from tbl_student where age > :age and status = 1", nativeQuery = true)
    List<Student> findByAgeGreaterThan(int age);

    @Query(value = "select * from tbl_student where age >= :age and status = 1", nativeQuery = true)
    List<Student> findByAgeGreaterThanEqual(int age);

    @Query(value = "select * from tbl_student where age <= :age and status = 1", nativeQuery = true)
    List<Student> findByAgeLessThanEqual(int age);

    @Query(value = "select * from tbl_student where age < :age and status = 1", nativeQuery = true)
    List<Student> findByAgeLessThan(int age);

    @Query(value = "select * from tbl_student where age = :age and status = 1", nativeQuery = true)
    List<Student> findByAge(int age);

    @Query(value = "select * from tbl_student where status = 1", nativeQuery = true)
    List<Student> findAllActiveStudent();

    @Query(value = "select * from tbl_student where status = 0", nativeQuery = true)
    List<Student> findAllInactiveStudent();

    @Modifying
    @Transactional
    @Query(value = "update tbl_student set status = 0 where student_id = :studentId",
            countQuery = "SELECT count(*) FROM tbl_student", nativeQuery = true)
    public void deleteStudentByStudentId(int studentId);

    @Modifying
    @Transactional
    @Query(value = "update tbl_student set status = 1 where student_id = :studentId",
            countQuery = "SELECT count(*) FROM tbl_student", nativeQuery = true)
    public void updateStatus(int studentId);
}
