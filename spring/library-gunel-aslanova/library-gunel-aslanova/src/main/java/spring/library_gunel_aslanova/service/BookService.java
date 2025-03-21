package spring.library_gunel_aslanova.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.library_gunel_aslanova.entity.BookEntity;
import spring.library_gunel_aslanova.entity.UserEntity;
import spring.library_gunel_aslanova.exception.MyException;
import spring.library_gunel_aslanova.repository.BookRepository;
import spring.library_gunel_aslanova.request.BookAddRequest;
import spring.library_gunel_aslanova.request.BookFilterRequest;
import spring.library_gunel_aslanova.request.BookUpdateRequest;
import spring.library_gunel_aslanova.response.BookListResponse;
import spring.library_gunel_aslanova.response.BookSingleResponse;
import spring.library_gunel_aslanova.util.Message;

@Service
@Transactional
public class BookService {

	@Autowired
	private BookRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper mapper;

	public Integer add(BookAddRequest req) {
		BookEntity en = new BookEntity();
		mapper.map(req, en);

		String username = userService.findUsername();

		UserEntity entity = userService.findByUsername(username);
		en.setLibrarianCode(entity.getUserId());
		repository.save(en);
		return en.getId();
	}


	public Integer update(BookUpdateRequest req) {
		Optional<BookEntity> op = repository.findById(req.getId());
		if (!op.isPresent()) {
			throw new MyException(Message.BOOK_NOT_FOUND_BY_ID, null, Message.ID_NOT_FOUND);
		}
		BookEntity en = op.get();
		mapper.map(req, en);

		String username = userService.findUsername();
		UserEntity entity = userService.findByUsername(username);
		if (!en.getLibrarianCode().equals(entity.getUserId())) {
			throw new MyException(Message.OTHER_USER_BOOK_UPDATE_NOT_ALLOWED, null, Message.FORBIDDEN);
		}
		repository.save(en);

		return en.getId();
	}

	public BookListResponse search(String query, Integer minPrice, Integer maxPrice) {
		query = query.toLowerCase();
		String username = userService.findUsername();
		UserEntity entity = userService.findByUsername(username);

		List<BookEntity> en = repository.findMyBookSearch(query, entity.getUserId(), minPrice, maxPrice);

		if (en.isEmpty()) {
			throw new MyException(Message.BOOK_NOT_FOUND_BY_NAME, null, Message.ID_NOT_FOUND);
		}
		BookListResponse resp = new BookListResponse();
		List<BookSingleResponse> responses = new ArrayList<BookSingleResponse>();
		for (BookEntity b : en) {
			if (!b.getLibrarianCode().equals(entity.getUserId())) {
				throw new MyException(Message.OTHER_USER_BOOK_UPDATE_NOT_ALLOWED, null, Message.FORBIDDEN);
			}
			BookSingleResponse r = new BookSingleResponse();
			mapper.map(b, r);
			responses.add(r);
		}

		resp.setBooks(responses);
		return resp;
	}

	public void deleteById(Integer id) {
		Optional<BookEntity> op = repository.findById(id);
		if (!op.isPresent()) {
			throw new MyException(Message.BOOK_NOT_FOUND_BY_NAME, null, Message.ID_NOT_FOUND);
		}
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);
		if (!op.get().getLibrarianCode().equals(en.getUserId())) {
			throw new MyException(Message.OTHER_USER_BOOK_DELETE_NOT_ALLOWED, null, Message.FORBIDDEN);
		}
		repository.deleteById(id);

	}

	public BookListResponse myBookSearch(BookFilterRequest r) {
		String username = userService.findUsername();
		UserEntity entity = userService.findByUsername(username);

		Long count = repository.myBookSearchCheck(entity.getUserId(), r.getId(), r.getName(), r.getStartPrice(),r.getEndPrice(),
				r.getDescription(), r.getStartDate(), r.getEndDate());
		
		if(count>2) {
			throw new MyException("daha cox melumat elave edin", null, "data-too-long");
		}
		List<BookEntity> en = repository.myBookSearch(entity.getUserId(), r.getId(), r.getName(), r.getStartPrice(),r.getEndPrice(),
				r.getDescription(), r.getStartDate(), r.getEndDate());

		BookListResponse resp = new BookListResponse();
		List<BookSingleResponse> responses = new ArrayList<BookSingleResponse>();
		for (BookEntity b : en) {
			BookSingleResponse re = new BookSingleResponse();
			mapper.map(b, re);
			responses.add(re);
		}

		resp.setBooks(responses);
		return resp;
	}

}
