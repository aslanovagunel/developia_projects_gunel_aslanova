
package spring.library_gunel_aslanova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.library_gunel_aslanova.exception.MyException;
import spring.library_gunel_aslanova.request.BookAddRequest;
import spring.library_gunel_aslanova.request.BookFilterRequest;
import spring.library_gunel_aslanova.request.BookFilterRequestForStudent;
import spring.library_gunel_aslanova.request.BookUpdateRequest;
import spring.library_gunel_aslanova.response.BookAddResponse;
import spring.library_gunel_aslanova.response.BookListResponse;
import spring.library_gunel_aslanova.service.BookService;
import spring.library_gunel_aslanova.util.Message;

@RestController
@RequestMapping(path = "/books")
public class BookController {

	@Autowired
	private BookService service;

	@PostMapping
	@PreAuthorize(value = "hasAuthority('ROLE_ADD_BOOK')")
	public ResponseEntity<BookAddResponse>  add(@Valid @RequestBody BookAddRequest req,BindingResult result) {
		if(result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		Integer id = service.add(req);
		BookAddResponse resp = new BookAddResponse();
		resp.setId(id);
		return new ResponseEntity<BookAddResponse>(resp, HttpStatus.CREATED);
	}

	@PutMapping
	@PreAuthorize(value = "hasAuthority('ROLE_UPDATE_BOOK')")
	public ResponseEntity<BookAddResponse> update(@Valid @RequestBody BookUpdateRequest req, BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		Integer id = service.update(req);
		BookAddResponse resp = new BookAddResponse();
		resp.setId(id);
		return new ResponseEntity<BookAddResponse>(resp, HttpStatus.OK);
	}

	@PostMapping(path = "/search")
	@PreAuthorize(value = "hasAuthority('ROLE_SEARCH_BOOK')")
	public ResponseEntity<BookListResponse> myBookSearch(@Valid @RequestBody BookFilterRequest req,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		BookListResponse resp = service.myBookSearch(req);
		return new ResponseEntity<BookListResponse>(resp, HttpStatus.OK);
	}
	
	@PostMapping(path = "/search-for-student")
	@PreAuthorize(value = "hasAuthority('ROLE_SEARCH_FOR_STUDENT_BOOK')")
	public ResponseEntity<BookListResponse> myBookSearchForStudent(@RequestBody BookFilterRequestForStudent req) {
		BookListResponse resp = service.myBookSearchForStudent(req);
		return new ResponseEntity<BookListResponse>(resp, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_BOOK')")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return  ResponseEntity.noContent().build();
	}

}
