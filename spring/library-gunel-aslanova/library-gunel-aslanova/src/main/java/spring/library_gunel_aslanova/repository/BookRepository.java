package spring.library_gunel_aslanova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.library_gunel_aslanova.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

	@Query(value = "select * from books where librarian_code=?2 and (lower(name) like %?1% or lower(author) like %?1% or price between ?3 and ?4) ", nativeQuery = true)
	List<BookEntity> findMyBookSearch(String query, Integer userId, Integer minPrice, Integer maxPrice);

}
