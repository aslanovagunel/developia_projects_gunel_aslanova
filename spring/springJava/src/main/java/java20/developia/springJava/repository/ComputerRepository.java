package java20.developia.springJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java20.developia.springJava.model.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Integer> {

}
