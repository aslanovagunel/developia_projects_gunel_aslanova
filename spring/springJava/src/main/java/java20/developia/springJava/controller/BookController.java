package java20.developia.springJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java20.developia.springJava.exception.MyException;
import java20.developia.springJava.request.BookAddRequest;
import java20.developia.springJava.request.BookUpdateRequest;
import java20.developia.springJava.response.BookAddResponse;
import java20.developia.springJava.response.BookListResponse;
import java20.developia.springJava.response.BookSingleResponse;
import java20.developia.springJava.service.BookService;
import java20.developia.springJava.util.Constans;

@RestController
@RequestMapping(path = "/books")
public class BookController {

	@Autowired
	private BookService service;

	@GetMapping(path = "/begin/{begin}/length/{length}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_BOOK')")
	public ResponseEntity<BookListResponse> findPagination(@PathVariable Integer begin, @PathVariable Integer length) {
		BookListResponse resp = service.findPagination(begin, length);
		return new ResponseEntity<BookListResponse>(resp, HttpStatus.OK);
	}

	@GetMapping(path = "search")
	public ResponseEntity<BookListResponse> search(@RequestParam(name = "query") String query) {
		BookListResponse resp = service.search(query);
		return new ResponseEntity<BookListResponse>(resp, HttpStatus.CREATED);
	}

	@PostMapping
	@PreAuthorize(value = "hasAuthority('ROLE_ADD_BOOK')")
	public ResponseEntity<BookAddResponse> add(@Valid @RequestBody BookAddRequest req, BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Constans.STRING_VALIDATION_MESSAGE, result, Constans.STRING_VALIDATION_TYPE);
		}
		service.add(req);
		BookAddResponse resp = new BookAddResponse();
		return new ResponseEntity<BookAddResponse>(resp, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_BOOK')")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<BookSingleResponse> findById(@PathVariable Integer id) throws Exception {
		BookSingleResponse resp = service.findById(id);
		return new ResponseEntity<BookSingleResponse>(resp, HttpStatus.OK);
	}
	
	@PutMapping
	@PreAuthorize(value = "hasAuthority('ROLE_UPDATE_BOOK')")
	public void update(@Valid @RequestBody BookUpdateRequest bU, BindingResult result) throws Exception {
		if(result.hasErrors()) {
			throw new MyException("validasiya xetasi", result, "validation");
		}

		service.update(bU);
	}



}