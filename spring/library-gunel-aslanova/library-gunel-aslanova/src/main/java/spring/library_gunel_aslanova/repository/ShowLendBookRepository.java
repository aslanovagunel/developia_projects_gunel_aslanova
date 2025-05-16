package spring.library_gunel_aslanova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.library_gunel_aslanova.entity.ShowLendBookEntity;

public interface ShowLendBookRepository extends JpaRepository<ShowLendBookEntity, Integer> {

	@Query(value = "select * from show_lend_books where librarian_code=?1 and return_date is null ", nativeQuery = true)
	List<ShowLendBookEntity> showLendBook(Integer id);

	@Query(value = "select * from show_lend_books where student_code=?1 and book_code=?2 and return_date is null ", nativeQuery = true)
	List<ShowLendBookEntity> changeReturnDate(Integer studentCode, Integer bCode);

	@Query(value = "select * from show_lend_books where librarian_code=?1 and return_date is not null ", nativeQuery = true)
	List<ShowLendBookEntity> showReturnBook(Integer id);

	@Query(value = "select * from show_lend_books where librarian_code=?1 and return_date is not null and return_date > must_return_date and must_return_date < CURRENT_DATE", nativeQuery = true)
	List<ShowLendBookEntity> getLateReturnedBooks(Integer userId);

	@Query(value = "select * from show_lend_books where student_code=?1", nativeQuery = true)
	List<ShowLendBookEntity> getBorrowedBooks(Integer id);

	@Query(value = "select * from show_lend_books where return_date is not null and return_date > must_return_date and must_return_date < CURRENT_DATE", nativeQuery = true)
	List<ShowLendBookEntity> getAllLateReturnedBooks();
}

