package java20.developia.springJava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java20.developia.springJava.MyFileReader;
import java20.developia.springJava.config.MyException;
import java20.developia.springJava.model.BookAdd;
import java20.developia.springJava.model.BookEntity;
import java20.developia.springJava.model.BookListResponce;
import java20.developia.springJava.model.BookSingleResponce;
import java20.developia.springJava.model.BookUpdate;
import java20.developia.springJava.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	@Autowired
	private MyFileReader fileReader;

	@Autowired
	private ModelMapper mapper;

	public BookListResponce findAllBooks() {
		List<BookEntity> books = repository.findAll();
		BookListResponce responce = new BookListResponce();
		List<BookSingleResponce> singleResponces = new ArrayList<BookSingleResponce>();

		for (BookEntity b : books) {
			BookSingleResponce responce2 = new BookSingleResponce();
			mapper.map(b, responce2);
			singleResponces.add(responce2);
		}

		responce.setBooks(singleResponces);
		return responce;

	}

	public BookListResponce findWords(String s) {
		List<BookEntity> filtered = repository.findAllByNameContaining(s);
		BookListResponce listResponce = new BookListResponce();
		List<BookSingleResponce> singleResponces = new ArrayList<BookSingleResponce>();

		for (BookEntity book : filtered) {
			BookSingleResponce singleResponce = new BookSingleResponce();
			mapper.map(book, singleResponce);
			singleResponces.add(singleResponce);
		}
		listResponce.setBooks(singleResponces);
		return listResponce;
	}

	public Integer add(BookAdd book) {
		BookEntity entity = new BookEntity();
		mapper.map(book, entity);
		repository.save(entity);
		return entity.getId();

	}

	public void deleteById(Integer id) {
		repository.deleteById(id);

	}

	public BookSingleResponce findById(Integer id) throws Exception {
		Optional<BookEntity> optional=repository.findById(id);
		String message = fileReader.readFromFile("id-not-found.txt");
		if (!optional.isPresent()) {
			throw new MyException(message, null, "id-not-found");
		}
		BookSingleResponce singleResponce = new BookSingleResponce();
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

	


