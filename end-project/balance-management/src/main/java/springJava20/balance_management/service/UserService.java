package springJava20.balance_management.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import springJava20.balance_management.entity.UserEntity;
import springJava20.balance_management.exception.MyException;
import springJava20.balance_management.repository.UserRepository;
import springJava20.balance_management.request.UserAddRequest;
import springJava20.balance_management.util.Message;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private ModelMapper mapper;

	public void checkUsernameExists(String username) {
		Optional<UserEntity> op = repository.findByUsername(username);
		if (op.isPresent()) {
			throw new MyException(Message.USER_EXISTS_MESSAGE, null, Message.USER_CONFLICT);
		}
	}

	public Integer add(UserAddRequest req) {
		checkUsernameExists(req.getUsername());

		// user add
		UserEntity en = new UserEntity();
		mapper.map(req, en);
		String encode = new BCryptPasswordEncoder().encode(en.getPassword());
		en.setPassword("{bcrypt}" + encode);
		en.setEnabled(true);
		repository.save(en);
		Integer id = en.getId();

		// authority add
		authorityService.addUserAuthorities(req.getUsername());

		return id;
	}
	public UserEntity findByUsername(String username) {
		Optional<UserEntity> op = repository.findByUsername(username);
		if (!op.isPresent()) {
			throw new MyException(Message.USER_NOT_FOUND, null, Message.USERNAME_NOT_FOUND);
		}
		return op.get();
	}

	public String findUsername() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return username;
	}
}

