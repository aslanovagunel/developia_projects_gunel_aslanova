package spring.library_gunel_aslanova.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.library_gunel_aslanova.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

	@Query(value = "select * from books where librarian_code=?2 and (lower(name) like %?1% or lower(author) like %?1% or price between ?3 and ?4) ", nativeQuery = true)
	List<BookEntity> findMyBookSearch(String query, Integer userId, Integer minPrice, Integer maxPrice);

	@Query(value = "select * from books where librarian_code=?1 and id like %?2% and lower(name) like %?3% and  (price between ?4 and ?5) and lower(description) like %?6%  and (publish_date between ?7 and ?8)", nativeQuery = true)
	List<BookEntity> myBookSearch(Integer userId, String id, String name, Integer startPrice, Integer endPrice,
			String description, LocalDate startDate, LocalDate endDate);

	@Query(value = "select count(*) from books where librarian_code=?1 and id like %?2%  and lower(name) like %?3% and  (price between ?4 and ?5) and lower(description) like %?6% and (publish_date between ?7 and ?8) ", nativeQuery = true)
	Long myBookSearchCheck(Integer userId, String id, String name, Integer startPrice, Integer endPrice,
			String description, LocalDate startDate, LocalDate endDate);

	@Query(value = "select count(*) from books where category_id like %?1%  and lower(name) like %?2% ", nativeQuery = true)
	Integer myBookSearchCountForStudent(String category, String name);

	@Query(value = "select * from books where category_id like %?1%  and lower(name) like %?2% limit ?3,?4 ", nativeQuery = true)
	List<BookEntity> myBookSearchForStudent(String category, String name, Integer begin,
			Integer length);

	@Query(value = "select * from books limit ?1,?2 ", nativeQuery = true)
	List<BookEntity> getShowBooksForStudent(Integer begin, Integer length);

	@Query(value = "select * from books where name like %?1% ", nativeQuery = true)
	List<BookEntity> searchBookWithFallback(String name);



}
