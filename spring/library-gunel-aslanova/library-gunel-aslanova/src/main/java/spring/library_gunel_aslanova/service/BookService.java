package spring.library_gunel_aslanova.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.library_gunel_aslanova.entity.BookEntity;
import spring.library_gunel_aslanova.repository.BookRepository;
import spring.library_gunel_aslanova.request.BookAddRequest;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	@Autowired
	private ModelMapper mapper;

	public Integer add(BookAddRequest req) {
		BookEntity en = new BookEntity();
		mapper.map(req, en);
		repository.save(en);
		return en.getId();
	}

}
