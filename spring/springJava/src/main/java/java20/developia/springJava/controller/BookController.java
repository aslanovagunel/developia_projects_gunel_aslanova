package java20.developia.springJava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java20.developia.springJava.model.Book;
import java20.developia.springJava.model.BookUpdate;
import java20.developia.springJava.service.BookService;

@RestController
@RequestMapping(path = "/books")
public class BookController {
//dependency injection
	@Autowired
	private BookService service;

	@GetMapping
	public List<Book> findAllBooks() {
		return service.findAllBooks();
	}

	@GetMapping(path = "find-word")
	public List<Book> findWords(@RequestParam(name = "sorgu") String s) {
		return service.findWords(s);
	}

	@PostMapping
	public void add(@Valid @RequestBody Book book) {
		service.add(book);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable Integer id) {
		service.deleteById(id);
	}

	@GetMapping(path = "/{id}")
	public Book findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@PutMapping
	public void update(@RequestBody BookUpdate bU) {
		service.update(bU);
	}



}