package spring.library_gunel_aslanova.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.library_gunel_aslanova.entity.SuggestionEntity;
import spring.library_gunel_aslanova.entity.UserEntity;
import spring.library_gunel_aslanova.exception.MyException;
import spring.library_gunel_aslanova.repository.UserRepository;
import spring.library_gunel_aslanova.request.LibrarianAddRequest;
import spring.library_gunel_aslanova.request.StudentAddRequest;
import spring.library_gunel_aslanova.response.SendListBookResponse;
import spring.library_gunel_aslanova.response.SendSingleBookResponse;
import spring.library_gunel_aslanova.util.Message;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private LibrarianService librarianService;

	@Autowired
	private ShowLendBookService showLendBookService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private SuggestionService suggestionService;

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

	public void addStudent(StudentAddRequest req, Integer studentId) {
		UserEntity en = new UserEntity();
		mapper.map(req, en);
		String encode = new BCryptPasswordEncoder().encode(en.getPassword());
		en.setPassword("{bcrypt}" + encode);
		en.setUserType("student");
		en.setUserId(studentId);
		en.setEnabled(true);
		repository.save(en);
		
	}

	public SendListBookResponse getShowSuggestion() {
		SendListBookResponse resp = new SendListBookResponse();
		List<SuggestionEntity> list = suggestionService.getShowSuggestion();
		List<SendSingleBookResponse> l = new ArrayList<SendSingleBookResponse>();
		for (SuggestionEntity s : list) {
			SendSingleBookResponse r = new SendSingleBookResponse();
			mapper.map(s, r);
			l.add(r);
		}
		resp.setBooks(l);
		return resp;
	}

}

