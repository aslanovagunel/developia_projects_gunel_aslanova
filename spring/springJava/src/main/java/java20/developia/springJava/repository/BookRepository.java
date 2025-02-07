package java20.developia.springJava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import java20.developia.springJava.model.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
	List<BookEntity> findAllByNameContaining(String description);

}
