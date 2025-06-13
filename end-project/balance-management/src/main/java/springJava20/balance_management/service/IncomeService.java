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
import springJava20.balance_management.entity.IncomeEntity;
import springJava20.balance_management.entity.UserEntity;
import springJava20.balance_management.exception.MyException;
import springJava20.balance_management.repository.IncomeRepository;
import springJava20.balance_management.request.IncomeAddRequest;
import springJava20.balance_management.request.IncomeCategoryFilterRequest;
import springJava20.balance_management.request.IncomeFilterRequest;
import springJava20.balance_management.request.IncomeUpdateRequest;
import springJava20.balance_management.response.IncomeListResponse;
import springJava20.balance_management.response.IncomeSingleResponse;
import springJava20.balance_management.util.Message;

@Service
@Transactional
public class IncomeService {

	@Autowired
	private IncomeRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserService userService;

	@Autowired
	private IncomeCategoryService incomeCategoryService;

	public Integer add(IncomeAddRequest req) {
		incomeCategoryService.findByCatId(req.getIncCategoryId());

		String username = userService.findUsername();
		UserEntity entity = userService.findByUsername(username);

		IncomeEntity en = new IncomeEntity();
		mapper.map(req, en);
		en.setId(null);
		en.setUserId(entity.getId());
		repository.save(en);

		return en.getId();
	}

	public void deleteById(Integer id) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		Optional<IncomeEntity> op = repository.findById(id);
		if (en.getId() != op.get().getUserId()) {
			throw new MyException(Message.OTHER_USER_INCOME_DELETE_NOT_ALLOWED, null, Message.FORBIDDEN);
		}
		Optional<IncomeEntity> op1 = repository.findById(id);
		if (!op.isPresent()) {
			throw new MyException(Message.INCOME_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}
		repository.deleteById(id);

	}

	public void update(IncomeUpdateRequest req) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		Optional<IncomeEntity> op = repository.findById(req.getId());
		if (en.getId() != op.get().getUserId()) {
			throw new MyException(Message.OTHER_USER_INCOME_UPDATE_NOT_ALLOWED, null, Message.FORBIDDEN);
		}
		Optional<IncomeEntity> op1 = repository.findById(req.getId());
		if (!op.isPresent()) {
			throw new MyException(Message.INCOME_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}
		IncomeEntity entity = op1.get();
		entity.setAmount(req.getAmount());
		entity.setDescription(req.getDescription());
		repository.save(entity);
	}

	public IncomeListResponse getAll(Integer begin, Integer lenght) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);
		List<IncomeEntity> all = repository.getAll(en.getId(), begin, lenght);

		IncomeListResponse resp = new IncomeListResponse();
		List<IncomeSingleResponse> responses = new ArrayList<IncomeSingleResponse>();
		for (IncomeEntity b : all) {
			IncomeSingleResponse re = new IncomeSingleResponse();
			mapper.map(b, re);
			responses.add(re);
		}

		resp.setResponses(responses);

		return resp;
	}

	public IncomeListResponse getByCategoryAndDateRange(IncomeCategoryFilterRequest r) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		List<IncomeEntity> list = repository.getByCategoryAndDateRange(en.getId(), r.getIncCategoryId(),
				r.getStartDate(), r.getEndDate(), r.getBegin(), r.getLength());
		IncomeListResponse resp = new IncomeListResponse();
		List<IncomeSingleResponse> responses = new ArrayList<IncomeSingleResponse>();
		for (IncomeEntity b : list) {
			IncomeSingleResponse re = new IncomeSingleResponse();
			mapper.map(b, re);
			responses.add(re);
		}
		resp.setResponses(responses);

		return resp;
	}

	public IncomeListResponse getByDateRange(IncomeFilterRequest r) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		List<IncomeEntity> list = repository.getByDateRange(en.getId(), r.getStartDate(), r.getEndDate(), r.getBegin(),
				r.getLength());
		IncomeListResponse resp = new IncomeListResponse();
		List<IncomeSingleResponse> responses = new ArrayList<IncomeSingleResponse>();
		for (IncomeEntity b : list) {
			IncomeSingleResponse re = new IncomeSingleResponse();
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
