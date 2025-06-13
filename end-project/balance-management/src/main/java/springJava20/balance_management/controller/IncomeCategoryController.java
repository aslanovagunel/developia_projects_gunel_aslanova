package springJava20.balance_management.controller;

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
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import springJava20.balance_management.exception.MyException;
import springJava20.balance_management.request.IncomeCategoryAddRequest;
import springJava20.balance_management.request.IncomeCategoryUpdateRequest;
import springJava20.balance_management.response.IncomeCategoryAddResponse;
import springJava20.balance_management.response.IncomeCategoryListResponse;
import springJava20.balance_management.service.IncomeCategoryService;
import springJava20.balance_management.util.Message;

@RestController
@RequestMapping(path = "/income-category")
public class IncomeCategoryController {

	@Autowired
	private IncomeCategoryService service;

	@PostMapping
	@PreAuthorize(value = "hasAuthority('ROLE_ADD_INCOME_CATEGORY')")
	public ResponseEntity<IncomeCategoryAddResponse> add(@Valid @RequestBody IncomeCategoryAddRequest req,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		Integer id = service.add(req);
		IncomeCategoryAddResponse resp = new IncomeCategoryAddResponse();
		resp.setId(id);
		return new ResponseEntity<IncomeCategoryAddResponse>(resp, HttpStatus.CREATED);
	}

	@GetMapping(path = "/begin/{begin}/length/{length}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_INCOME_CATEGORY')")
	public ResponseEntity<IncomeCategoryListResponse> getAll(@PathVariable Integer begin,
			@PathVariable Integer length) {
		IncomeCategoryListResponse resp = service.getAll(begin, length);
		return new ResponseEntity<IncomeCategoryListResponse>(resp, HttpStatus.OK);
	}

	@PutMapping
	@PreAuthorize(value = "hasAuthority('ROLE_UPDATE_INCOME_CATEGORY')")
	public ResponseEntity<?> update(@Valid @RequestBody IncomeCategoryUpdateRequest req, BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		service.update(req);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_INCOME_CATEGORY')")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
