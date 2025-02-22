package java20.developia.springJava.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java20.developia.springJava.entity.UserEntity;
import java20.developia.springJava.exception.MyException;
import java20.developia.springJava.repository.UserRepository;
import java20.developia.springJava.request.StudentAddRequest;
import java20.developia.springJava.request.UserUpdateRequest;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper mapper;

	public void addStudent(StudentAddRequest req, Integer id) {
		UserEntity en = new UserEntity();
		mapper.map(req, en);
		
		String pass = en.getPassword();
		String encoded = new BCryptPasswordEncoder().encode(pass);

		en.setPassword("{bcrypt}" + encoded);
		en.setUserId(id);

		repository.save(en);
	}

	public void cheskStudentExists(StudentAddRequest req) {
		Optional<UserEntity> op = repository.findById(req.getUsername());
		if (op.isPresent()) {
			throw new MyException("movcuddur", null, "conflict");
		}
	}

	public void changePassword(UserUpdateRequest req) {
		Optional<UserEntity> op = repository.findById(req.getUsername());
		if (!op.isPresent()) {
			throw new MyException("username tapilmadi", null, "not-found");
		}
		UserEntity en = op.get();
		String encode = new BCryptPasswordEncoder().encode(req.getPassword());
		en.setPassword("{bcrypt}" + encode);

		repository.save(en);

	}
	
}

	


