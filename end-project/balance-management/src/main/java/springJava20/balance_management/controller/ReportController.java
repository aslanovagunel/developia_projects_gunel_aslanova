package springJava20.balance_management.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springJava20.balance_management.request.ReportDateRequest;
import springJava20.balance_management.response.ReportListResponse;
import springJava20.balance_management.service.ReportService;

@RestController
@RequestMapping(path = "/report")
public class ReportController {

	@Autowired
	private ReportService service;

	@GetMapping(path = "/expense-income-date")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_DATE')")
	public ResponseEntity<ReportListResponse> getIncomeAndExpensesBetweenDates(@RequestBody ReportDateRequest req) {
		ReportListResponse resp = service.getIncomeAndExpensesBetweenDates(req);
		return new ResponseEntity<ReportListResponse>(resp, HttpStatus.OK);
	}

	@GetMapping(path = "/current-balance")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_CURRENT_BALANCE')")
	public ResponseEntity<?> getCurrentBalance() {
		BigDecimal currentBalance = service.getCurrentBalance();
		return ResponseEntity.ok(currentBalance);
	}

	@GetMapping(path = "/date/{date}")
	@PreAuthorize(value = "hasAuthority('ROLE_GET_CURRENT_BALANCE_AND_DATE')")
	public ResponseEntity<?> getCurrentBalanceAndDate(@PathVariable LocalDate date) {
		BigDecimal currentBalance = service.getCurrentBalanceAndDate(date);
		return ResponseEntity.ok(currentBalance);
	}
}
