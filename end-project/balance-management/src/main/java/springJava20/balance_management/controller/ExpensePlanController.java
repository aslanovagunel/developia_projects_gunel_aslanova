package springJava20.balance_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springJava20.balance_management.request.ExpensePlanRequest;
import springJava20.balance_management.response.ExpensePlanAddResponse;
import springJava20.balance_management.response.ExpensePlanCheckResponse;
import springJava20.balance_management.service.ExpensePlanService;

@RestController
@RequestMapping(path = "/expense-plan")
public class ExpensePlanController {

	@Autowired
	private ExpensePlanService service;

	@PostMapping
	@PreAuthorize(value = "hasAuthority('ROLE_POST_EXPENSE_PLAN')")
	public ResponseEntity<ExpensePlanAddResponse> addExpensePlan(@RequestBody ExpensePlanRequest req) {
		Integer id = service.addExpensePlan(req);
		ExpensePlanAddResponse resp = new ExpensePlanAddResponse();
		resp.setId(id);
		return new ResponseEntity<ExpensePlanAddResponse>(resp, HttpStatus.CREATED);
	}

	@GetMapping(path = "id/{id}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_CHECK_EXPENSE_PLAN')")
	public ResponseEntity<ExpensePlanCheckResponse> checkExpensePlan(@PathVariable Integer id) {
		ExpensePlanCheckResponse resp = service.checkExpensePlan(id);
		return new ResponseEntity<ExpensePlanCheckResponse>(resp, HttpStatus.OK);
	}
}
