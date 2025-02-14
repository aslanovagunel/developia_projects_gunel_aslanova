package java20.developia.springJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java20.developia.springJava.exception.MyException;
import java20.developia.springJava.model.BookAdd;
import java20.developia.springJava.model.BookUpdate;
import java20.developia.springJava.response.BookListResponse;
import java20.developia.springJava.response.BookSingleResponse;
import java20.developia.springJava.service.BookService;

@RestController
@RequestMapping(path = "/books")
public class BookController {

	@Autowired
	private BookService service;


	@GetMapping
	@PreAuthorize(value = "hasAuthority('ROLE_GET_BOOK')")
	public BookListResponse findAllBooks() {
		return service.findAllBooks();
	}

	@GetMapping(path = "search")
	public BookListResponse findBooks(@RequestParam(name = "query") String s) {
		return service.findBooks(s);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize(value = "hasAuthority('ROLE_ADD_BOOK')")
	public void add(@Valid @RequestBody BookAdd book, BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException("melumat yanlis daxil edilib", result, "validation");
		}
		service.add(book);
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_BOOK')")
	public void deleteById(@PathVariable Integer id) {
		service.deleteById(id);
	}

	@GetMapping(path = "/{id}")
	public BookSingleResponse findById(@PathVariable Integer id) throws Exception {
		return service.findById(id);
	}
	
	@PutMapping
	@PreAuthorize(value = "hasAuthority('ROLE_UPDATE_BOOK')")
	public void update(@Valid @RequestBody BookUpdate bU, BindingResult result) throws Exception {
		if(result.hasErrors()) {
			throw new MyException("validasiya xetasi", result, "validation");
		}

		service.update(bU);
	}

	@GetMapping(path = "/name/{name}/index/{index}")
	public BookListResponse demo(@PathVariable Integer index, @PathVariable String name) {

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