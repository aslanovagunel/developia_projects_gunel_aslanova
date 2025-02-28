package spring.library_gunel_aslanova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.library_gunel_aslanova.exception.MyException;
import spring.library_gunel_aslanova.request.BookAddRequest;
import spring.library_gunel_aslanova.response.BookAddResponse;
import spring.library_gunel_aslanova.service.BookService;

@RestController
@RequestMapping(path = "/books")
public class BookController {

	@Autowired
	private BookService service;

	@PostMapping
	@PreAuthorize(value = "hasAuthority('ROLE_ADD_BOOK')")
	public ResponseEntity<BookAddResponse>  add(@Valid @RequestBody BookAddRequest req,BindingResult result) {
		if(result.hasErrors()) {
			throw new MyException("melumat pozulub", result, "validatoin");
		}
		Integer id = service.add(req);
		BookAddResponse resp = new BookAddResponse();
		resp.setId(id);
		return new ResponseEntity<BookAddResponse>(resp, HttpStatus.CREATED);
	}

}
