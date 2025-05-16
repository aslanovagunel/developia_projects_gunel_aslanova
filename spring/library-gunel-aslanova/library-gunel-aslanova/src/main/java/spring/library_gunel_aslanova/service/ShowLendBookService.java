package spring.library_gunel_aslanova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.library_gunel_aslanova.entity.ShowLendBookEntity;
import spring.library_gunel_aslanova.repository.ShowLendBookRepository;
import spring.library_gunel_aslanova.request.ReturnBookRequest;

@Service
@Transactional
public class ShowLendBookService {

	@Autowired
	private ShowLendBookRepository repository;

	@Autowired
	private LendBookService lendBookService;

	public List<ShowLendBookEntity> showLendBook(Integer userId) {
		List<ShowLendBookEntity> showLendBook = repository.showLendBook(userId);
		return showLendBook;

	}

	public List<ShowLendBookEntity> changeReturnDate(ReturnBookRequest req) {
		List<ShowLendBookEntity> list = repository.changeReturnDate(req.getStudentCode(), req.getBookCode());
		lendBookService.changeReturnDate(req);
		return list;
	}

	public List<ShowLendBookEntity> showReturnBook(Integer userId) {
		List<ShowLendBookEntity> list = repository.showReturnBook(userId);
		return list;

	}

	public List<ShowLendBookEntity> getLateReturnedBooks(Integer userId) {
		List<ShowLendBookEntity> list = repository.getLateReturnedBooks(userId);
		return list;

	}

	public List<ShowLendBookEntity> getBorrowedBooks(Integer id) {
		List<ShowLendBookEntity> list = repository.getBorrowedBooks(id);
		return list;
	}

	public List<ShowLendBookEntity> getAllLateReturnedBooks() {
		List<ShowLendBookEntity> all = repository.getAllLateReturnedBooks();
		return all;
	}

}
