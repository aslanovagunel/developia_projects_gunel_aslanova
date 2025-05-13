package spring.library_gunel_aslanova.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.library_gunel_aslanova.config.MyConfig;
import spring.library_gunel_aslanova.entity.BookEntity;
import spring.library_gunel_aslanova.entity.ShowLendBookEntity;
import spring.library_gunel_aslanova.entity.UserEntity;
import spring.library_gunel_aslanova.exception.MyException;
import spring.library_gunel_aslanova.request.LendBookRequest;
import spring.library_gunel_aslanova.request.ReturnBookRequest;
import spring.library_gunel_aslanova.response.LendBookListResponse;
import spring.library_gunel_aslanova.response.LendBookSingleResponse;
import spring.library_gunel_aslanova.response.ShowLendBookListResponse;
import spring.library_gunel_aslanova.response.ShowLendBookSingleResponse;
import spring.library_gunel_aslanova.util.Message;

@Service
@Transactional
public class BookBasketService {

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@Autowired
	private ShowLendBookService showLendBookService;

	@Autowired
	private LendBookService lendBookService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private SuggestionService suggestionService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private MyConfig myConfig;

	public LendBookListResponse lendBook(LendBookRequest req) {

		studentService.checkStudentExists(req.getStudentCode());

		String username = userService.findUsername();
		UserEntity userEntity = userService.findByUsername(username);

		Optional<BookEntity> en = bookService.findById(req.getBookCode());
		if (en.isEmpty()) {
			throw new MyException("bu id'li kitab tapilmadi", null, Message.ID_NOT_FOUND);
		}

		BookEntity entity = en.get();

		if (entity.getQuantity() < req.getCount()) {
			throw new MyException("kifayet qeder kitab yoxdur", null, "kitab yoxdur");
		}
		entity.setQuantity(entity.getQuantity() - req.getCount());
		bookService.save(entity);

		LendBookSingleResponse re = new LendBookSingleResponse();
		re.setBookCode(entity.getId());
		re.setStudentCode(req.getStudentCode());
		re.setCount(req.getCount());
		re.setLibrarianCode(userEntity.getUserId());
		re.setMustReturnDate(req.getMustReturnDate());
		re.setRegDate(LocalDate.now());
		re.setReturnDate(null);
		Integer id = lendBookService.lendBook(re);
		re.setId(id);

		LendBookListResponse resp = new LendBookListResponse();
		resp.setResponses(List.of(re));

		return resp;
	}

	public ShowLendBookListResponse showLendBook() {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		List<ShowLendBookEntity> showLendBook = showLendBookService.showLendBook(en.getUserId());
		List<ShowLendBookSingleResponse> res = new ArrayList<ShowLendBookSingleResponse>();
		for (ShowLendBookEntity s : showLendBook) {
			ShowLendBookSingleResponse r = new ShowLendBookSingleResponse();
			mapper.map(s, r);
			r.setId(s.getId());
			res.add(r);

		}
		ShowLendBookListResponse resp = new ShowLendBookListResponse();
		resp.setResponses(res);
		return resp;
	}

	public ShowLendBookListResponse returnBook(ReturnBookRequest req) {

		ShowLendBookListResponse showLendBook = showLendBook();
		List<ShowLendBookEntity> changeReturnDate = showLendBookService.changeReturnDate(req);

		List<ShowLendBookSingleResponse> res = new ArrayList<>(showLendBook.getResponses());
		List<ShowLendBookSingleResponse> resp = new ArrayList<ShowLendBookSingleResponse>();

		for (ShowLendBookEntity c : changeReturnDate) {
			ShowLendBookSingleResponse s = new ShowLendBookSingleResponse();
			mapper.map(c, s);
			s.setReturnDate(req.getReturnDate());
			resp.add(s);
		}
		showLendBook.setResponses(resp);
		return showLendBook;
	}

	public ShowLendBookListResponse showReturnBook() {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		List<ShowLendBookEntity> showReturnBook = showLendBookService.showReturnBook(en.getUserId());

		List<ShowLendBookSingleResponse> res = new ArrayList<ShowLendBookSingleResponse>();
		for (ShowLendBookEntity s : showReturnBook) {
			ShowLendBookSingleResponse r = new ShowLendBookSingleResponse();
			mapper.map(s, r);
			r.setId(s.getId());
			res.add(r);
		}
		ShowLendBookListResponse resp = new ShowLendBookListResponse();
		resp.setResponses(res);
		return resp;
	}

	public ShowLendBookListResponse getLateReturnedBooks() {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		List<ShowLendBookEntity> lateReturnedBooks = showLendBookService.getLateReturnedBooks(en.getUserId());

		List<ShowLendBookSingleResponse> res = new ArrayList<ShowLendBookSingleResponse>();
		for (ShowLendBookEntity s : lateReturnedBooks) {
			ShowLendBookSingleResponse r = new ShowLendBookSingleResponse();
			mapper.map(s, r);
			r.setId(s.getId());
			res.add(r);
		}
		ShowLendBookListResponse resp = new ShowLendBookListResponse();
		resp.setResponses(res);
		return resp;
	}
}
