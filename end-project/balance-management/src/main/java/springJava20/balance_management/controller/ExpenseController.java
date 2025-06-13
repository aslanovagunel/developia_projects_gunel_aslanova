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
import springJava20.balance_management.request.ExpenseAddRequest;
import springJava20.balance_management.request.ExpenseCategoryFilterRequest;
import springJava20.balance_management.request.ExpenseFilterRequest;
import springJava20.balance_management.request.ExpenseUpdateRequest;
import springJava20.balance_management.response.ExpenseAddResponse;
import springJava20.balance_management.response.ExpenseListResponse;
import springJava20.balance_management.service.ExpenseService;
import springJava20.balance_management.util.Message;

@RestController
@RequestMapping(path = "/expense")
public class ExpenseController {

	@Autowired
	private ExpenseService service;

	@PostMapping
	@PreAuthorize(value = "hasAuthority('ROLE_ADD_EXPENSE')")
	public ResponseEntity<ExpenseAddResponse> add(@Valid @RequestBody ExpenseAddRequest req,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		Integer id = service.add(req);
		ExpenseAddResponse resp = new ExpenseAddResponse();
		resp.setId(id);
		return new ResponseEntity<ExpenseAddResponse>(resp, HttpStatus.CREATED);
	}

	@GetMapping(path = "/begin/{begin}/length/{length}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_EXPENSE')")
	public ResponseEntity<ExpenseListResponse> getAll(@PathVariable Integer begin,
			@PathVariable Integer length) {
		ExpenseListResponse resp = service.getAll(begin, length);
		return new ResponseEntity<ExpenseListResponse>(resp, HttpStatus.OK);
	}

	@PutMapping
	@PreAuthorize(value = "hasAuthority('ROLE_UPDATE_EXPENSE')")
	public ResponseEntity<?> update(@Valid @RequestBody ExpenseUpdateRequest req, BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		service.update(req);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_EXPENSE')")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/category-date-range")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_CATEGORY_DATE_RANGE')")
	public ResponseEntity<ExpenseListResponse> getByCategoryAndDateRange(
			@RequestBody ExpenseCategoryFilterRequest req) {
		ExpenseListResponse resp = service.getByCategoryAndDateRange(req);
		return new ResponseEntity<ExpenseListResponse>(resp, HttpStatus.OK);
	}

	@GetMapping(path = "/date-range")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_DATE_RANGE')")
	public ResponseEntity<ExpenseListResponse> getByDateRange(@RequestBody ExpenseFilterRequest req) {
		ExpenseListResponse resp = service.getByDateRange(req);
		return new ResponseEntity<ExpenseListResponse>(resp, HttpStatus.OK);
	}

}
