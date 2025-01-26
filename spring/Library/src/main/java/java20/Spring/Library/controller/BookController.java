package java20.Spring.Library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java20.Spring.Library.model.Book;
import java20.Spring.Library.service.BookService;

@RestController
@RequestMapping(path = "/books")
public class BookController {

	@Autowired
	private BookService service;

	@GetMapping
	public List<Book> allBooks() {
		return service.allBooks();
	}

	@GetMapping(path = "find-book")
	public List<Book> findBooks(@RequestParam(name = "search") String s) {
		return service.findBooks(s);
	}

	@PostMapping
	public void add(@RequestBody Book book) {
		service.add(book);
	}

}
