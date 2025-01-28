package java20.developia.springJava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import java20.developia.springJava.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findAllByPhoneContaining(String phone);
}
