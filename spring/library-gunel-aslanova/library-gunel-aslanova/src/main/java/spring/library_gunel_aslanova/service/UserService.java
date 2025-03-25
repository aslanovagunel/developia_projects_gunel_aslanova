package spring.library_gunel_aslanova.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import spring.library_gunel_aslanova.entity.UserEntity;
import spring.library_gunel_aslanova.exception.MyException;
import spring.library_gunel_aslanova.repository.UserRepository;
import spring.library_gunel_aslanova.request.LibrarianAddRequest;
import spring.library_gunel_aslanova.request.StudentAddRequest;
import spring.library_gunel_aslanova.util.Message;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private LibrarianService librarianService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private StudentService studentService;

	public Integer addLibrarian(LibrarianAddRequest req) {

		// check
		checkUsernameExists(req.getUsername());

		// librarian add
		Integer id = librarianService.add(req);

		// user add
		UserEntity en = new UserEntity();
		mapper.map(req, en);
		String encode = new BCryptPasswordEncoder().encode(en.getPassword());
		en.setPassword("{bcrypt}" + encode);
		en.setEnabled(true);
		en.setUserType("librarian");
		en.setUserId(id);
		repository.save(en);

		// authority add
		authorityService.addLibrarianAuthorities(req.getUsername());

		return id;
	}

	public void checkUsernameExists(String username) {
		Optional<UserEntity> op = repository.findById(username);
		if (op.isPresent()) {
			throw new MyException("bu sexs movcuddur", null, "conflict");
		}

	}

	public UserEntity findByUsername(String username) {
		Optional<UserEntity> op = repository.findById(username);
		if (!op.isPresent()) {
			throw new MyException(Message.NAME_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}
		return op.get();
	}

	public String findUsername() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return username;
	}

	public Integer addStudent(@Valid StudentAddRequest req) {
		// check student exists
		checkUsernameExists(req.getUsername());

		// add student
		Integer studentId = studentService.add(req);

		// add user
		UserEntity en = new UserEntity();
		mapper.map(req, en);
		String encode = new BCryptPasswordEncoder().encode(en.getPassword());
		en.setPassword("{bcrypt}" + encode);
		en.setUserType("student");
		en.setUserId(studentId);
		en.setEnabled(true);
		repository.save(en);


		// add authority
		authorityService.addStudentAuthorities(req.getUsername());
		return studentId;
	}

}
