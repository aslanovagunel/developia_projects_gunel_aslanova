package java20.developia.springJava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java20.developia.springJava.model.Book;
import java20.developia.springJava.model.BookUpdate;
import java20.developia.springJava.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	public List<Book> findAllBooks() {
		return repository.findAll();

	}

	public List<Book> findWords(String s) {
		List<Book> filtered = repository.findAllByNameContaining(s);
		return filtered;
	}

	public void add(Book book) {
		repository.save(book);

	}

	public void deleteById(Integer id) {
		repository.deleteById(id);

	}

	public Book findById(Integer id) {
		Optional<Book> optional=repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}

	}

	public void update(BookUpdate bU) {
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
