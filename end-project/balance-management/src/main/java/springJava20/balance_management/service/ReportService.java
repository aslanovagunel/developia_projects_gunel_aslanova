package springJava20.balance_management.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import springJava20.balance_management.entity.UserEntity;
import springJava20.balance_management.repository.ReportRepository;
import springJava20.balance_management.request.ExpenseFilterRequest;
import springJava20.balance_management.request.IncomeFilterRequest;
import springJava20.balance_management.request.ReportDateRequest;
import springJava20.balance_management.response.ExpenseListResponse;
import springJava20.balance_management.response.ExpenseSingleResponse;
import springJava20.balance_management.response.IncomeListResponse;
import springJava20.balance_management.response.IncomeSingleResponse;
import springJava20.balance_management.response.ReportListResponse;
import springJava20.balance_management.response.ReportSingleResponse;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private IncomeService incomeService;

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private UserService userService;

	public ReportListResponse getIncomeAndExpensesBetweenDates(ReportDateRequest req) {
		IncomeFilterRequest r=new IncomeFilterRequest();
		mapper.map(req, r);
		IncomeListResponse resp = incomeService.getByDateRange(r);

		ExpenseFilterRequest r1 = new ExpenseFilterRequest();
		mapper.map(req, r1);
		ExpenseListResponse resp1 = expenseService.getByDateRange(r1);

		ReportListResponse res = new ReportListResponse();
		List<ReportSingleResponse> res1 = new ArrayList<ReportSingleResponse>();
		List<ExpenseSingleResponse> responses = resp1.getResponses();
		for (ExpenseSingleResponse b : responses) {
			ReportSingleResponse response = new ReportSingleResponse();
			mapper.map(b, response);
			res1.add(response);
		}
		res.setResponses(res1);

		List<IncomeSingleResponse> responses1 = resp.getResponses();
		for (IncomeSingleResponse b : responses1) {
			ReportSingleResponse response = new ReportSingleResponse();
			mapper.map(b, response);
			res1.add(response);
		}
		res.setResponses(res1);

		return res;
	}

	public BigDecimal getCurrentBalance() {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		BigDecimal currentBalance = incomeService.getCurrentBalance(en.getId());
		BigDecimal currentBalance2 = expenseService.getCurrentBalance(en.getId());
		
		BigDecimal cariBalance = currentBalance.subtract(currentBalance2);
		
		return cariBalance;

	}

	public BigDecimal getCurrentBalanceAndDate(LocalDate date) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		BigDecimal currentBalance = incomeService.getCurrentBalanceAndDate(en.getId(), date);
		BigDecimal currentBalance2 = expenseService.getCurrentBalanceAndDate(en.getId(), date);

		BigDecimal cariBalance = currentBalance.subtract(currentBalance2);

		return cariBalance;
	}
}
