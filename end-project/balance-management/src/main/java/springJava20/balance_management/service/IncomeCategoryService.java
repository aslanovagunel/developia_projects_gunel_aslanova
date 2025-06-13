package springJava20.balance_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import springJava20.balance_management.entity.IncomeCategoryEntity;
import springJava20.balance_management.entity.UserEntity;
import springJava20.balance_management.exception.MyException;
import springJava20.balance_management.repository.IncomeCategoryRepository;
import springJava20.balance_management.request.IncomeCategoryAddRequest;
import springJava20.balance_management.request.IncomeCategoryUpdateRequest;
import springJava20.balance_management.response.IncomeCategoryListResponse;
import springJava20.balance_management.response.IncomeCategorySingleResponse;
import springJava20.balance_management.util.Message;

@Service
@Transactional
public class IncomeCategoryService {

	@Autowired
	private IncomeCategoryRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserService userService;

	public Integer add(IncomeCategoryAddRequest req) {
		checkCategoryExists(req.getName());

		IncomeCategoryEntity en = new IncomeCategoryEntity();
		mapper.map(req, en);
		String username = userService.findUsername();
		UserEntity entity = userService.findByUsername(username);
		en.setUserId(entity.getId());
		repository.save(en);
		return en.getId();
	}

	public void checkCategoryExists(String name) {
		Optional<IncomeCategoryEntity> op = repository.findByName(name);
		if (op.isPresent()) {
			throw new MyException(Message.CATEGORY_EXISTS, null, Message.CATEGORY_CONFLICT);
		}
	}

	public void deleteById(Integer id) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		Optional<IncomeCategoryEntity> op = repository.findById(id);
		if (en.getId() != op.get().getUserId()) {
			throw new MyException(Message.OTHER_USER_INCOME_CATEGORY_DELETE_NOT_ALLOWED, null, Message.FORBIDDEN);
		}
		Optional<IncomeCategoryEntity> op1 = repository.findById(id);
		if (!op.isPresent()) {
			throw new MyException(Message.INCOME_CATEGORY_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}
		repository.deleteById(id);

	}

	public void update(IncomeCategoryUpdateRequest req) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);

		Optional<IncomeCategoryEntity> op = repository.findById(req.getId());
		if (en.getId() != op.get().getUserId()) {
			throw new MyException(Message.OTHER_USER_INCOME_CATEGORY_UPDATE_NOT_ALLOWED, null, Message.FORBIDDEN);
		}
		Optional<IncomeCategoryEntity> op1 = repository.findById(req.getId());
		if (!op.isPresent()) {
			throw new MyException(Message.INCOME_CATEGORY_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}
		IncomeCategoryEntity entity = op1.get();
		entity.setName(req.getName());
		repository.save(entity);
	}

	public IncomeCategoryListResponse getAll(Integer begin, Integer lenght) {
		String username = userService.findUsername();
		UserEntity en = userService.findByUsername(username);
		List<IncomeCategoryEntity> all = repository.getAll(en.getId(), begin, lenght);

		IncomeCategoryListResponse resp = new IncomeCategoryListResponse();
		List<IncomeCategorySingleResponse> responses = new ArrayList<IncomeCategorySingleResponse>();
		for (IncomeCategoryEntity b : all) {
			IncomeCategorySingleResponse re = new IncomeCategorySingleResponse();
			mapper.map(b, re);
			responses.add(re);
		}

		resp.setResponses(responses);

		return resp;
	}

	public void findByCatId(Integer incCategoryId) {
		Optional<IncomeCategoryEntity> op = repository.findById(incCategoryId);
		if (!op.isPresent()) {
			throw new MyException(Message.INCOME_CATEGORY_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}

	}
}
