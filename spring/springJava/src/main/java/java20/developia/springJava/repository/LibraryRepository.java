package java20.developia.springJava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import java20.developia.springJava.entity.LibraryEntity;

public interface LibraryRepository extends JpaRepository<LibraryEntity, Integer> {
	List<LibraryEntity> findAllByNameContaining(String name);
}
