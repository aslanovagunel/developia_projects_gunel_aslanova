package java20.developia.springJava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java20.developia.springJava.model.Book;
import java20.developia.springJava.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	public List<Book> findAllBooks() {

		return repository.findAllBooks();

	}

}
