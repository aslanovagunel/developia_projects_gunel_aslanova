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
import java20.developia.springJava.request.StudentAddRequest;
import java20.developia.springJava.request.StudentUpdateRequest;
import java20.developia.springJava.response.StudentAddResponse;
import java20.developia.springJava.response.StudentListResponse;
import java20.developia.springJava.response.StudentSingleResponse;
import java20.developia.springJava.service.StudentService;
import java20.developia.springJava.util.Constans;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

	@Autowired
	private StudentService service;

	@GetMapping(path = "/search")
	@PreAuthorize(value = "hasAuthority('ROLE_SEARCH_STUDENT')")
	public ResponseEntity<StudentListResponse> find(@RequestParam(name = "query") String query) {
		StudentListResponse resp = service.find(query);
		return new ResponseEntity<StudentListResponse>(resp, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize(value = "hasAuthority('ROLE_ADD_STUDENT')")
	public ResponseEntity<StudentAddResponse> add(@Valid @RequestBody StudentAddRequest req,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Constans.STRING_VALIDATION_MESSAGE, result, Constans.STRING_VALIDATION_TYPE);
		}
		service.add(req);
		StudentAddResponse resp = new StudentAddResponse();

		return new ResponseEntity<StudentAddResponse>(resp, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_UPDATE_STUDENT')")
	public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody StudentUpdateRequest update,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Constans.STRING_VALIDATION_MESSAGE, result, Constans.STRING_VALIDATION_TYPE);
		}
		service.update(id, update);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_BY_ID')")
	public ResponseEntity<StudentSingleResponse> findById(@PathVariable Integer id) {
		StudentSingleResponse resp = service.findById(id);
		return new ResponseEntity<StudentSingleResponse>(resp, HttpStatus.OK);
	}



}
