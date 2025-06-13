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
import springJava20.balance_management.request.ExpenseCategoryAddRequest;
import springJava20.balance_management.request.ExpenseCategoryUpdateRequest;
import springJava20.balance_management.response.ExpenseCategoryAddResponse;
import springJava20.balance_management.response.ExpenseCategoryListResponse;
import springJava20.balance_management.service.ExpenseCategoryService;
import springJava20.balance_management.util.Message;

@RestController
@RequestMapping(path = "/expense-category")
public class ExpenseCategoryController {

	@Autowired
	private ExpenseCategoryService service;

	@PostMapping
	@PreAuthorize(value = "hasAuthority('ROLE_ADD_EXPENSE_CATEGORY')")
	public ResponseEntity<ExpenseCategoryAddResponse> add(@Valid @RequestBody ExpenseCategoryAddRequest req, BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		Integer id = service.add(req);
		ExpenseCategoryAddResponse resp = new ExpenseCategoryAddResponse();
		resp.setId(id);
		return new ResponseEntity<ExpenseCategoryAddResponse>(resp, HttpStatus.CREATED);
	}

	@GetMapping(path = "/begin/{begin}/length/{length}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_EXPENSE_CATEGORY')")
	public ResponseEntity<ExpenseCategoryListResponse> getAll(@PathVariable Integer begin,
			@PathVariable Integer length) {
		ExpenseCategoryListResponse resp = service.getAll(begin, length);
		return new ResponseEntity<ExpenseCategoryListResponse>(resp, HttpStatus.OK);
	}

	@PutMapping
	@PreAuthorize(value = "hasAuthority('ROLE_UPDATE_EXPENSE_CATEGORY')")
	public ResponseEntity<?> update(@Valid @RequestBody ExpenseCategoryUpdateRequest req, BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		service.update(req);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_EXPENSE_CATEGORY')")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
