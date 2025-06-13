package springJava20.balance_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import springJava20.balance_management.entity.ExpenseCategoryEntity;
import springJava20.balance_management.entity.UserEntity;
import springJava20.balance_management.exception.MyException;
import springJava20.balance_management.repository.ExpenseCategoryRepository;
import springJava20.balance_management.request.ExpenseCategoryAddRequest;
import springJava20.balance_management.request.ExpenseCategoryUpdateRequest;
import springJava20.balance_management.response.ExpenseCategoryListResponse;
import springJava20.balance_management.response.ExpenseCategorySingleResponse;
import springJava20.balance_management.util.Message;

@Service
@Transactional
public class ExpenseCategoryService {

	@Autowired
	private ExpenseCategoryRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserService userService;

	public Integer add(ExpenseCategoryAddRequest req) {
		checkCategoryExists(req.getName());

		ExpenseCategoryEntity en = new ExpenseCategoryEntity();
		mapper.map(req, en);
		String username = userService.findUsername();
		UserEntity entity = userService.findByUsername(username);
		en.setUserId(entity.getId());
		repository.save(en);
		return en.getId();
	}

	public void checkCategoryExists(String name) {
		Optional<ExpenseCategoryEntity> op = repository.findByName(name);
		if(op.isPresent()) {
			throw new MyException(Message.CATEGORY_EXISTS, null, Message.CATEGORY_CONFLICT);
		}
		
	}

	public void deleteById(Integer id) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		Optional<ExpenseCategoryEntity> op = repository.findById(id);
		if (en.getId() != op.get().getUserId()) {
			throw new MyException(Message.OTHER_USER_EXPENSE_CATEGORY_DELETE_NOT_ALLOWED, null, Message.FORBIDDEN);
		}
		Optional<ExpenseCategoryEntity> op1 = repository.findById(id);
		if (!op.isPresent()) {
			throw new MyException(Message.EXPENSE_CATEGORY_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}
		repository.deleteById(id);

	}

	public void update(ExpenseCategoryUpdateRequest req) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		Optional<ExpenseCategoryEntity> op = repository.findById(req.getId());
		if (en.getId() != op.get().getUserId()) {
			throw new MyException(Message.OTHER_USER_EXPENSE_CATEGORY_UPDATE_NOT_ALLOWED, null, Message.FORBIDDEN);
		}
		Optional<ExpenseCategoryEntity> op1 = repository.findById(req.getId());
		if (!op.isPresent()) {
			throw new MyException(Message.EXPENSE_CATEGORY_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}
		ExpenseCategoryEntity entity = op1.get();
		entity.setName(req.getName());
		repository.save(entity);
	}

	public ExpenseCategoryListResponse getAll(Integer begin, Integer length) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);
		List<ExpenseCategoryEntity> all = repository.getAll(en.getId(), begin, length);

		ExpenseCategoryListResponse resp = new ExpenseCategoryListResponse();
		List<ExpenseCategorySingleResponse> responses = new ArrayList<ExpenseCategorySingleResponse>();
		for (ExpenseCategoryEntity b : all) {
			ExpenseCategorySingleResponse re = new ExpenseCategorySingleResponse();
			mapper.map(b, re);
			responses.add(re);
		}

		resp.setResponses(responses);

		return resp;
	}

	public void findByCatId(Integer exsCategoryId) {
		Optional<ExpenseCategoryEntity> op = repository.findById(exsCategoryId);
		if (!op.isPresent()) {
			throw new MyException(Message.EXPENSE_CATEGORY_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}

	}
}
