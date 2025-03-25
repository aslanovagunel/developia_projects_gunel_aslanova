package spring.library_gunel_aslanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.library_gunel_aslanova.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

}
