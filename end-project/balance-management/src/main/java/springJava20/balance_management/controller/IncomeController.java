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
import springJava20.balance_management.request.IncomeAddRequest;
import springJava20.balance_management.request.IncomeCategoryFilterRequest;
import springJava20.balance_management.request.IncomeFilterRequest;
import springJava20.balance_management.request.IncomeUpdateRequest;
import springJava20.balance_management.response.IncomeAddResponse;
import springJava20.balance_management.response.IncomeListResponse;
import springJava20.balance_management.service.IncomeService;
import springJava20.balance_management.util.Message;

@RestController
@RequestMapping(path = "/income")
public class IncomeController {

	@Autowired
	private IncomeService service;

	@PostMapping
	@PreAuthorize(value = "hasAuthority('ROLE_ADD_INCOME')")
	public ResponseEntity<IncomeAddResponse> add(@Valid @RequestBody IncomeAddRequest req,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		Integer id = service.add(req);
		IncomeAddResponse resp = new IncomeAddResponse();
		resp.setId(id);
		return new ResponseEntity<IncomeAddResponse>(resp, HttpStatus.CREATED);
	}

	@GetMapping(path = "/begin/{begin}/length/{length}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_INCOME')")
	public ResponseEntity<IncomeListResponse> getAll(@PathVariable Integer begin,
			@PathVariable Integer length) {
		IncomeListResponse resp = service.getAll(begin, length);
		return new ResponseEntity<IncomeListResponse>(resp, HttpStatus.OK);
	}

	@PutMapping
	@PreAuthorize(value = "hasAuthority('ROLE_UPDATE_INCOME')")
	public ResponseEntity<?> update(@Valid @RequestBody IncomeUpdateRequest req, BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		service.update(req);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_DELETE_INCOME')")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/category-date-range")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_CATEGORY_DATE_RANGE')")
	public ResponseEntity<IncomeListResponse> getByCategoryAndDateRange(@RequestBody IncomeCategoryFilterRequest req) {
		IncomeListResponse resp = service.getByCategoryAndDateRange(req);
		return new ResponseEntity<IncomeListResponse>(resp, HttpStatus.OK);
	}

	@GetMapping(path = "/date-range")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_DATE_RANGE')")
	public ResponseEntity<IncomeListResponse> getByDateRange(@RequestBody IncomeFilterRequest req) {
		IncomeListResponse resp = service.getByDateRange(req);
		return new ResponseEntity<IncomeListResponse>(resp, HttpStatus.OK);
	}

}
