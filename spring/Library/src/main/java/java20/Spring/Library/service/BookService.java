package java20.Spring.Library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java20.Spring.Library.model.Book;
import java20.Spring.Library.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	public List<Book> allBooks() {
		return repository.allBooks();
	}

	public List<Book> findBooks(String s) {
		List<Book> book = repository.findBooks();
		List<Book> filtred = new ArrayList<Book>();
		for (Book b : book) {
			if (b.getAuthor().contains(s) || b.getTitle().contains(s)) {
				filtred.add(b);
			}
		}
		return filtred;
	}

	public void add(Book book) {
		repository.add(book);

	}

}
