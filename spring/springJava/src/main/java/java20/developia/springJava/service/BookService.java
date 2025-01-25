package java20.developia.springJava.service;

import java.util.ArrayList;
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

	public List<Book> findWords(String s) {
		List<Book> books = repository.findWords();
		List<Book> books2 = new ArrayList<Book>();
		for (Book book : books) {
			if (book.getName().contains(s)) {
				books2.add(book);
			}
		}
		return books2;
	}

	public void add(Book book) {
		repository.add(book);
	}

}
