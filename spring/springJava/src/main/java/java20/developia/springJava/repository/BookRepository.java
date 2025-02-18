package java20.developia.springJava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java20.developia.springJava.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
	List<BookEntity> findAllByNameContaining(String description);

	@Query(value = "select * from books limit ?1,?2", nativeQuery = true)
	List<BookEntity> findPagination(Integer begin, Integer length);

}
