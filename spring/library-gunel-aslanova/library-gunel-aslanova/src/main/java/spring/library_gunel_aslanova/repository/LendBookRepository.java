package spring.library_gunel_aslanova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.library_gunel_aslanova.entity.LendBookEntity;

public interface LendBookRepository extends JpaRepository<LendBookEntity, Integer> {

	@Query(value = "select * from lend_books where librarian_code=?1 and return_date is null ", nativeQuery = true)
	List<LendBookEntity> showLendBook(Integer id);

	@Query(value = "select * from lend_books where student_code=?1 and book_code=?2 and return_date is null ", nativeQuery = true)
	List<LendBookEntity> changeReturnDate(Integer studentCode, Integer bCode);

	@Query(value = "select * from lend_books where librarian_code=?1 and return_date is not null ", nativeQuery = true)
	List<LendBookEntity> showReturnBook(Integer id);

	@Query(value = "select * from lend_books where librarian_code=?1 and return_date is not null and return_date > must_return_date and must_return_date < CURRENT_DATE", nativeQuery = true)
	List<LendBookEntity> getLateReturnedBooks(Integer userId);

}
