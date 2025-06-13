package springJava20.balance_management.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import springJava20.balance_management.entity.ExpenseEntity;
import springJava20.balance_management.entity.UserEntity;
import springJava20.balance_management.exception.MyException;
import springJava20.balance_management.repository.ExpenseRepository;
import springJava20.balance_management.request.ExpenseAddRequest;
import springJava20.balance_management.request.ExpenseCategoryFilterRequest;
import springJava20.balance_management.request.ExpenseFilterRequest;
import springJava20.balance_management.request.ExpenseUpdateRequest;
import springJava20.balance_management.response.ExpenseListResponse;
import springJava20.balance_management.response.ExpenseSingleResponse;
import springJava20.balance_management.util.Message;

@Service
@Transactional
public class ExpenseService {

	@Autowired
	private ExpenseRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserService userService;

	@Autowired
	private ExpenseCategoryService expenseCategoryService;

	public Integer add(ExpenseAddRequest req) {
		expenseCategoryService.findByCatId(req.getExpCategoryId());

		String username = userService.findUsername();
		UserEntity entity = userService.findByUsername(username);

		ExpenseEntity en = new ExpenseEntity();
		mapper.map(req, en);
		en.setId(null);
		en.setUserId(entity.getId());
		repository.save(en);

		System.out.println(en.toString());

		return en.getId();
	}

	public void deleteById(Integer id) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		Optional<ExpenseEntity> op = repository.findById(id);
		if (en.getId() != op.get().getUserId()) {
			throw new MyException(Message.OTHER_USER_EXPENSE_DELETE_NOT_ALLOWED, null, Message.FORBIDDEN);
		}
		Optional<ExpenseEntity> op1 = repository.findById(id);
		if (!op.isPresent()) {
			throw new MyException(Message.EXPENSE_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}
		repository.deleteById(id);

	}

	public void update(ExpenseUpdateRequest req) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		Optional<ExpenseEntity> op = repository.findById(req.getId());
		if (en.getId() != op.get().getUserId()) {
			throw new MyException(Message.OTHER_USER_EXPENSE_UPDATE_NOT_ALLOWED, null, Message.FORBIDDEN);
		}
		Optional<ExpenseEntity> op1 = repository.findById(req.getId());
		if (!op.isPresent()) {
			throw new MyException(Message.EXPENSE_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}
		ExpenseEntity entity = op1.get();
		entity.setAmount(req.getAmount());
		entity.setDescription(req.getDescription());
		repository.save(entity);
	}

	public ExpenseListResponse getAll(Integer begin, Integer length) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);
		List<ExpenseEntity> all = repository.getAll(en.getId(), begin, length);

		ExpenseListResponse resp = new ExpenseListResponse();
		List<ExpenseSingleResponse> responses = new ArrayList<ExpenseSingleResponse>();
		for (ExpenseEntity b : all) {
			ExpenseSingleResponse re = new ExpenseSingleResponse();
			mapper.map(b, re);
			responses.add(re);
		}

		resp.setResponses(responses);

		return resp;
	}

	public ExpenseListResponse getByCategoryAndDateRange(ExpenseCategoryFilterRequest req) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		List<ExpenseEntity> list = repository.getByCategoryAndDateRange(en.getId(), req.getExpCategoryId(),
				req.getStartDate(), req.getEndDate(), req.getBegin(), req.getLength());
		ExpenseListResponse resp = new ExpenseListResponse();
		List<ExpenseSingleResponse> responses = new ArrayList<ExpenseSingleResponse>();
		for (ExpenseEntity b : list) {
			ExpenseSingleResponse re = new ExpenseSingleResponse();
			mapper.map(b, re);
			responses.add(re);
		}
		resp.setResponses(responses);

		return resp;
	}

	public ExpenseListResponse getByDateRange(ExpenseFilterRequest req) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		List<ExpenseEntity> list = repository.getByDateRange(en.getId(), req.getStartDate(), req.getEndDate(),
				req.getBegin(), req.getLength());
		ExpenseListResponse resp = new ExpenseListResponse();
		List<ExpenseSingleResponse> responses = new ArrayList<ExpenseSingleResponse>();
		for (ExpenseEntity b : list) {
			ExpenseSingleResponse re = new ExpenseSingleResponse();
			mapper.map(b, re);
			responses.add(re);
		}
		resp.setResponses(responses);

		return resp;
	}

	public BigDecimal getCurrentBalance(Integer id) {
		BigDecimal currentBalance = repository.getCurrentBalance(id);
		return currentBalance;
	}

	public BigDecimal getCurrentBalanceAndDate(Integer id, LocalDate date) {
		BigDecimal currentBalance = repository.getCurrentBalanceAndDate(id, date);
		return currentBalance;
	}
}
