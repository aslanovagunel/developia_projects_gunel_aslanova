package java20.developia.springJava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java20.developia.springJava.exception.MyException;
import java20.developia.springJava.model.BookAdd;
import java20.developia.springJava.model.BookEntity;
import java20.developia.springJava.model.BookUpdate;
import java20.developia.springJava.repository.BookRepository;
import java20.developia.springJava.response.BookListResponse;
import java20.developia.springJava.response.BookSingleResponse;
import java20.developia.springJava.util.MyFileReader;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private MyFileReader fileReader;

	@Autowired
	private ModelMapper mapper;

	public BookListResponse findAllBooks() {
		List<BookEntity> books = repository.findAll();
		BookListResponse responce = new BookListResponse();
		List<BookSingleResponse> singleResponces = new ArrayList<BookSingleResponse>();

		for (BookEntity b : books) {
			BookSingleResponse responce2 = new BookSingleResponse();
			mapper.map(b, responce2);
			singleResponces.add(responce2);
		}

		responce.setBooks(singleResponces);
		return responce;

	}

	public BookListResponse findBooks(String s) {
		List<BookEntity> filtered = repository.findAllByNameContaining(s);

		if (filtered.isEmpty()) {
			throw new MyException("axtarisinizin neticesi yoxdur", null, "search-not-found");
		}
		BookListResponse listResponce = new BookListResponse();
		List<BookSingleResponse> singleResponces = new ArrayList<BookSingleResponse>();

		for (BookEntity book : filtered) {
			BookSingleResponse singleResponce = new BookSingleResponse();
			mapper.map(book, singleResponce);
			singleResponces.add(singleResponce);
		}
		listResponce.setBooks(singleResponces);
		return listResponce;
	}

	public void add(BookAdd book) {
		BookEntity entity = new BookEntity();
		mapper.map(book, entity);
		repository.save(entity);


	}

	public void deleteById(Integer id) {
		Optional<BookEntity> byId = repository.findById(id);
		if (!byId.isPresent()) {
			throw new MyException("id tapilmadi", null, "id-not-found");
		}
		repository.deleteById(id);

	}

	public BookSingleResponse findById(Integer id) throws Exception {
		Optional<BookEntity> optional=repository.findById(id);
		String message = fileReader.readFromFile("id-not-found.txt");
		if (!optional.isPresent()) {
			throw new MyException(message, null, "id-not-found");
		}
		BookSingleResponse singleResponce = new BookSingleResponse();
		mapper.map(optional.get(), singleResponce);
		return singleResponce;
	}

	public void update(BookUpdate bU) throws Exception {
		Optional<BookEntity> optional = repository.findById(bU.getId());
	
		if (!optional.isPresent()) {
			String message = fileReader.readFromFile("id-not-found.txt");
			throw new MyException(message, null, "id-not-found");
		}
		
		BookEntity book = optional.get();

		mapper.map(bU, book);
		repository.save(book);
			
		}
	}

	


