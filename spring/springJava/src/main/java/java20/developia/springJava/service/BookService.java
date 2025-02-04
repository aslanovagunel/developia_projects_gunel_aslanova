package java20.developia.springJava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java20.developia.springJava.model.Book;
import java20.developia.springJava.model.BookNotFoundException;
import java20.developia.springJava.model.BookUpdate;
import java20.developia.springJava.model.DuplicateResourceException;
import java20.developia.springJava.model.ResourceNotFoundException;
import java20.developia.springJava.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	public BookService() {
		System.out.println("def konstruktor");
	}
	public BookService(int i) {
		System.out.println("parametrli konstruktor");
	}

	public List<Book> findAllBooks() {
		return repository.findAll();

	}

	public List<Book> findWords(String s) {
		List<Book> filtered = repository.findAllByNameContaining(s);
		return filtered;
	}

	public Integer add(Book book) {
		repository.save(book);
		return book.getId();

	}

	public void deleteById(Integer id) {
		repository.deleteById(id);

	}

	public Book findById(Integer id) {
		Optional<Book> optional=repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new BookNotFoundException("bazada kitab yoxdur");
		}
	}

	public void update(BookUpdate bU) {
		Optional<Book> optional = repository.findById(bU.getId());
		BookUpdate bookUpdate = new BookUpdate(optional.get().getId(), optional.get().getName(),
				optional.get().getDescription());
		if (optional.isPresent() && bU.equals(bookUpdate)) {
			throw new DuplicateResourceException("eyni melumat gonderdiniz");
		}
		else {
			if (!repository.findById(bU.getId()).isPresent()) {
				throw new ResourceNotFoundException("redakte ucun kitab tapilmadi");
			}
			else {
				Integer id = bU.getId();
				String name = bU.getName();
				String description = bU.getDescription();
	
				Book book = repository.findById(bU.getId()).get();
	
				book.setId(id);
				book.setName(name);
				book.setDescription(description);
	
				repository.save(book);
			}
		}
	}

	

	}

