package java20.developia.springJava.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import java20.developia.springJava.model.BookNotFoundException;
import java20.developia.springJava.model.BookUpdate;
import java20.developia.springJava.model.DuplicateResourceException;
import java20.developia.springJava.model.ErrorResponce;
import java20.developia.springJava.model.MyException;
import java20.developia.springJava.model.MyFieldError;
import java20.developia.springJava.model.MyValidationError;
import java20.developia.springJava.model.ResourceNotFoundException;
import java20.developia.springJava.model.ValidationException;
import java20.developia.springJava.service.BookService;

@RestController
@RequestMapping(path = "/books")
public class BookController {
//dependency injection
	@Autowired
	private BookService service;
	private BindingResult result;

	@GetMapping
	public List<Book> findAllBooks() {
		return service.findAllBooks();
	}

	@GetMapping(path = "find-word")
	public List<Book> findWords(@RequestParam(name = "sorgu") String s) {
		return service.findWords(s);
	}

	@PostMapping
	public Integer add(@Valid @RequestBody Book book, BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException("melumat yanlis daxil edilib", result);
		}
		return service.add(book);
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
	public void update(@Valid @RequestBody BookUpdate bU, BindingResult result) {
		if(result.hasErrors()) {
			throw new ValidationException("validasiya xetasi", result);
		}
		service.update(bU);
	}

	@GetMapping(path = "/name/{name}/index/{index}")
	public List<Book> demo(@PathVariable Integer index, @PathVariable String name) {

		if (name.contains("g")) {
			if (index >= name.length()) {
				throw new ArrayIndexOutOfBoundsException("o indexli element yoxdur");
			} else {
				System.out.println(name.charAt(index));
			}

		} else {
			System.out.println("yoxdu");
		}

		return service.findAllBooks();
	}

	@ExceptionHandler
	public String myHandleArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e) {
		return e.getMessage();
	}

	@ExceptionHandler
	public ErrorResponce myHandleException(MyException e) {
		BindingResult result = e.getResult();
		List<MyFieldError> errors = new ArrayList<MyFieldError>();
		List<FieldError> errors2 = result.getFieldErrors();

		for (FieldError error : errors2) {
			MyFieldError er = new MyFieldError();
			er.setField(error.getField());
			er.setMessage(error.getDefaultMessage());
			errors.add(er);
		}
		ErrorResponce responce = new ErrorResponce();
		responce.setMessage(e.getMessage());
		responce.setErrors(errors);
		responce.setDate(LocalDate.now());
		return responce;
	}

	@ExceptionHandler
	public String myHandleBookNotFoundException(BookNotFoundException e) {
		return e.getMessage();
	}

	@ExceptionHandler
	public String myHandleResourceNotFoundException(ResourceNotFoundException e) {
		return e.getMessage();
	}

	@ExceptionHandler
	public List<MyValidationError> myHandleValidationException(ValidationException e) {
		BindingResult result = e.getResult();
		List<MyValidationError> errors = new ArrayList<MyValidationError>();
		List<FieldError> fieldErrors = result.getFieldErrors();
		MyValidationError error = new MyValidationError();
		for (FieldError fieldError : fieldErrors) {
			error.setField(fieldError.getField());
			error.setMessage(fieldError.getDefaultMessage());
			errors.add(error);
		}
		return errors;
	}

	@ExceptionHandler
	public String myDuplicateResourceException(DuplicateResourceException e) {
		return e.getMessage();
	}
}