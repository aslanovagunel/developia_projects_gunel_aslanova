package java20.developia.springJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java20.developia.springJava.config.MyException;
import java20.developia.springJava.model.BookAdd;
import java20.developia.springJava.model.BookListResponce;
import java20.developia.springJava.model.BookSingleResponce;
import java20.developia.springJava.model.BookUpdate;
import java20.developia.springJava.service.BookService;

@RestController
@RequestMapping(path = "/books")
public class BookController {
//dependency injection
	@Autowired
	private BookService service;
	private BindingResult result;

	@GetMapping
	public BookListResponce findAllBooks() {
		return service.findAllBooks();
	}

	@GetMapping(path = "find-word")
	public BookListResponce findWords(@RequestParam(name = "sorgu") String s) {
		return service.findWords(s);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Integer add(@Valid @RequestBody BookAdd book, BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException("melumat yanlis daxil edilib", result, "validation");
		}
		return service.add(book);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable Integer id) {
		service.deleteById(id);
	}

	@GetMapping(path = "/{id}")
	public BookSingleResponce findById(@PathVariable Integer id) throws Exception {
		return service.findById(id);
	}
	
	@PutMapping
	public void update(@Valid @RequestBody BookUpdate bU, BindingResult result) throws Exception {
		if(result.hasErrors()) {
			throw new MyException("validasiya xetasi", result, "validation");
		}

		service.update(bU);
	}

	@GetMapping(path = "/name/{name}/index/{index}")
	public BookListResponce demo(@PathVariable Integer index, @PathVariable String name) {

		if (name.contains("g")) {
			if (index >= name.length()) {
				throw new MyException("o indexli element yoxdur", null, "index-not-found");
			} else {
				System.out.println(name.charAt(index));
			}

		} else {
			System.out.println("yoxdu");
		}

		return service.findAllBooks();
	}


}