package java20.developia.springJava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import java20.developia.springJava.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	List<Book> findAllByNameContaining(String description);

}
