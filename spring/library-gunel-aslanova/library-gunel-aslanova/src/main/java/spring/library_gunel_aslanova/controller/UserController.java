package spring.library_gunel_aslanova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.library_gunel_aslanova.exception.MyException;
import spring.library_gunel_aslanova.request.LibrarianAddRequest;
import spring.library_gunel_aslanova.response.LibrarianAddResponse;
import spring.library_gunel_aslanova.service.UserService;
import spring.library_gunel_aslanova.util.Message;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping(path = "/librarians")
	public ResponseEntity<LibrarianAddResponse> addLibrarian(@Valid @RequestBody LibrarianAddRequest req,
			BindingResult result) {
		if(result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		Integer id = service.addLibrarian(req);
		LibrarianAddResponse resp = new LibrarianAddResponse();
		resp.setId(id);
		return new ResponseEntity<LibrarianAddResponse>(resp, HttpStatus.CREATED);
	}

}
