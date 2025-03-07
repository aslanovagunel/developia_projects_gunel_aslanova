package spring.library_gunel_aslanova.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import spring.library_gunel_aslanova.entity.BookEntity;
import spring.library_gunel_aslanova.entity.UserEntity;
import spring.library_gunel_aslanova.exception.MyException;
import spring.library_gunel_aslanova.repository.BookRepository;
import spring.library_gunel_aslanova.request.BookAddRequest;
import spring.library_gunel_aslanova.request.BookUpdateRequest;
import spring.library_gunel_aslanova.response.BookListResponse;
import spring.library_gunel_aslanova.response.BookSingleResponse;
import spring.library_gunel_aslanova.util.Message;

@Service
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

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		UserEntity entity = userService.findByUsername(username);
		en.setLibrarianCode(entity.getUserId());
		repository.save(en);
		return en.getId();
	}


	public Integer update(BookUpdateRequest req) {
		Optional<BookEntity> op = repository.findById(req.getId());
		BookEntity en = null;
		if (!op.isPresent()) {
			throw new MyException(Message.BOOK_NOT_FOUND_BY_ID, null, Message.ID_NOT_FOUND);
		}
		en = op.get();
		mapper.map(req, en);
		repository.save(en);

		return en.getId();
	}

	public BookListResponse search(String query) {
		List<BookEntity> en = repository.findAllByNameContaining(query);

		if (en.isEmpty()) {
			throw new MyException(Message.BOOK_NOT_FOUND_BY_NAME, null, Message.ID_NOT_FOUND);
		}
		BookListResponse resp = new BookListResponse();
		List<BookSingleResponse> responses = new ArrayList<BookSingleResponse>();
		for (BookEntity b : en) {
			BookSingleResponse r = new BookSingleResponse();
			mapper.map(b, r);
			responses.add(r);
		}
		resp.setBooks(responses);
		return resp;
	}

}
