package springJava20.balance_management.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import springJava20.balance_management.entity.ExpensePlanEntity;
import springJava20.balance_management.entity.UserEntity;
import springJava20.balance_management.exception.MyException;
import springJava20.balance_management.repository.ExpensePlanRepository;
import springJava20.balance_management.request.ExpensePlanRequest;
import springJava20.balance_management.response.ExpensePlanCheckResponse;
import springJava20.balance_management.util.Message;

@Service
@Transactional
public class ExpensePlanService {

	@Autowired
	private ExpensePlanRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private UserService userService;

	public Integer addExpensePlan(ExpensePlanRequest req) {

		ExpensePlanEntity en = new ExpensePlanEntity();
		mapper.map(req, en);
		repository.save(en);

		return en.getId();

	}

	public ExpensePlanCheckResponse checkExpensePlan(Integer id) {
		Optional<ExpensePlanEntity> op = repository.findById(id);
		ExpensePlanEntity en = op.get();
		
		String username = userService.findUsername();
		UserEntity ent = userService.findByUsername(username);

		long dateDifference = ChronoUnit.DAYS.between(en.getStartDate(), en.getEndDate());
		if (dateDifference == 0) {
			throw new MyException(Message.PLAN_DURATION_CANNOT_BE_ZERO, null, Message.FORBIDDEN);
		}

		long dateCount = 0;
		LocalDate now = LocalDate.now();

		if (now.isBefore(en.getStartDate())) {
			throw new MyException(Message.EXPENSE_BEFORE_PLAN_START_NOT_ALLOWED, null, Message.FORBIDDEN);
		} else if (now.isAfter(en.getEndDate())) {
			dateCount = dateDifference;
		} else {
			dateCount = ChronoUnit.DAYS.between(en.getStartDate(), now);
		}

		BigDecimal dailyExpense = en.getTotalAmount().divide(BigDecimal.valueOf(dateDifference), RoundingMode.HALF_UP);

		BigDecimal planExpense = dailyExpense.multiply(BigDecimal.valueOf(dateCount));
		
		BigDecimal realExpense = expenseService.getCurrentBalanceAndDate(ent.getId(), now);
		if (realExpense == null) {
			realExpense = BigDecimal.ZERO;
		}


		BigDecimal difference = planExpense.subtract(realExpense);



		ExpensePlanCheckResponse resp = new ExpensePlanCheckResponse();
		resp.setTotalAmount(en.getTotalAmount());
		resp.setDaysPassed(dateCount);
		resp.setDifference(difference);
		resp.setNowDate(now);
		resp.setPlannedExpense(planExpense);
		resp.setRealExpense(realExpense);
		
		if (difference.compareTo(BigDecimal.ZERO) < 0)
			resp.setStatus("siz artıq planı keçmisiniz");
		else if (difference.compareTo(BigDecimal.ZERO) > 0)
			resp.setStatus("siz hələ plandan az xərcləmisiniz");
		else
			resp.setStatus("siz tam plan üzrə xərcləmisiniz");

		return resp;
	}
}
