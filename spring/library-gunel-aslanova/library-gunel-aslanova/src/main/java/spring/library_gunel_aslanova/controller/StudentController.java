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
import spring.library_gunel_aslanova.request.SendBookRequest;
import spring.library_gunel_aslanova.request.StudentAddRequest;
import spring.library_gunel_aslanova.request.StudentFilterRequest;
import spring.library_gunel_aslanova.request.StudentUpdateRequest;
import spring.library_gunel_aslanova.response.SendAddBookResponse;
import spring.library_gunel_aslanova.response.StudentAddResponse;
import spring.library_gunel_aslanova.response.StudentListResponse;
import spring.library_gunel_aslanova.response.StudentUpdateResponse;
import spring.library_gunel_aslanova.service.StudentService;
import spring.library_gunel_aslanova.util.Message;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

	@Autowired
	private StudentService service;

	@PostMapping
	@PreAuthorize(value = "hasAuthority('ROLE_ADD_STUDENT')")
	public ResponseEntity<StudentAddResponse> add(@Valid @RequestBody StudentAddRequest req,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		Integer id = service.add(req);
		StudentAddResponse resp = new StudentAddResponse();
		resp.setId(id);
		return new ResponseEntity<StudentAddResponse>(resp, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_STUDENT')")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	@PreAuthorize(value = "hasAuthority('ROLE_UPDATE_STUDENT')")
	public ResponseEntity<StudentUpdateResponse> update(@Valid @RequestBody StudentUpdateRequest req,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		Integer id = service.update(req);
		StudentUpdateResponse resp = new StudentUpdateResponse();
		resp.setId(id);
		return new ResponseEntity<StudentUpdateResponse>(resp, HttpStatus.OK);
	}

	@PostMapping(path = "/find-student")
	@PreAuthorize(value = "hasAuthority('ROLE_FIND_STUDENT')")
	public ResponseEntity<StudentListResponse> search(@RequestBody StudentFilterRequest req) {

		StudentListResponse resp = service.search(req);
		return new ResponseEntity<StudentListResponse>(resp, HttpStatus.CREATED);
	}

	@PostMapping(path = "/request-book")
	@PreAuthorize(value = "hasAuthority('ROLE_REQUEST_BOOK')")
	public ResponseEntity<SendAddBookResponse> getRequestBook(@RequestBody SendBookRequest req) {

		SendAddBookResponse resp = service.getRequestBook(req);
		return new ResponseEntity<SendAddBookResponse>(resp, HttpStatus.CREATED);
	}




}
