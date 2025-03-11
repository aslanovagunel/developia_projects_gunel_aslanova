
package spring.library_gunel_aslanova.controller;

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
import spring.library_gunel_aslanova.exception.MyException;
import spring.library_gunel_aslanova.request.BookAddRequest;
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

	@GetMapping(path = "/search")
	public ResponseEntity<BookListResponse> search(@RequestParam(value = "query") String query) {
		BookListResponse resp = service.search(query);
		return new ResponseEntity<BookListResponse>(resp, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_BOOK')")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return  ResponseEntity.noContent().build();
	}



}
