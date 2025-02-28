package spring.library_gunel_aslanova.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import spring.library_gunel_aslanova.entity.UserEntity;
import spring.library_gunel_aslanova.exception.MyException;
import spring.library_gunel_aslanova.repository.UserRepository;
import spring.library_gunel_aslanova.request.LibrarianAddRequest;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private LibrarianService librarianService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private ModelMapper mapper;

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
		en.setType("librarian");
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

}
