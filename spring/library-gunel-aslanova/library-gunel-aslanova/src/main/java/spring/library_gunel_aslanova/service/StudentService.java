package spring.library_gunel_aslanova.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.library_gunel_aslanova.config.MyConfig;
import spring.library_gunel_aslanova.entity.StudentEntity;
import spring.library_gunel_aslanova.entity.UserEntity;
import spring.library_gunel_aslanova.exception.MyException;
import spring.library_gunel_aslanova.repository.StudentRepository;
import spring.library_gunel_aslanova.request.StudentAddRequest;
import spring.library_gunel_aslanova.request.StudentFilterRequest;
import spring.library_gunel_aslanova.request.StudentUpdateRequest;
import spring.library_gunel_aslanova.response.StudentListResponse;
import spring.library_gunel_aslanova.response.StudentSingleResponse;
import spring.library_gunel_aslanova.util.Message;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private MyConfig myConfig;

	public Integer add(StudentAddRequest req) {

		// check student exists
		userService.checkUsernameExists(req.getUsername());

		// add student
		String username = userService.findUsername();
		UserEntity entity = userService.findByUsername(username);

		StudentEntity en = new StudentEntity();
		mapper.map(req, en);
		en.setLibrarianCode(entity.getUserId());
		repository.save(en);
		Integer studentId = en.getId();

		// add user
		userService.addStudent(req, studentId);

		// add authority
		authorityService.addStudentAuthorities(req.getUsername());
		return studentId;
	}

	public void deleteById(Integer id) {
		Optional<StudentEntity> op = repository.findById(id);
		if (!op.isPresent()) {
			throw new MyException(Message.NAME_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}

		String username = userService.findUsername();
		UserEntity entity = userService.findByUsername(username);
		if (!entity.getUserId().equals(op.get().getLibrarianCode())) {
			throw new MyException("basqa kitabxanacinin telebesini sile bilmersen", null, "accsess-denied");
		}
		repository.deleteById(id);
	}

	public Integer update(StudentUpdateRequest req) {
		Optional<StudentEntity> op = repository.findById(req.getId());
		if (!op.isPresent()) {
			throw new MyException(Message.NAME_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}
		StudentEntity en = op.get();

		String username = userService.findUsername();
		UserEntity entity = userService.findByUsername(username);
		if (!entity.getUserId().equals(op.get().getLibrarianCode())) {
			throw new MyException("basqa kitabxanacinin telebesini redakte ede bilmersen", null, "accsess-denied");
		}

		mapper.map(req, en);
		repository.save(en);
		return en.getId();

	}

	public StudentListResponse search(StudentFilterRequest req) {

		Long searchResultCount = repository.searchResultCount(req.getId(), req.getName(), req.getSurname(),
				req.getPhone(), req.getEmail(), req.getBirthday(), req.getBegin(), req.getLength());

		if (searchResultCount > myConfig.getRowCountLimit()) {
			throw new MyException(
					"netice coxdur max " + myConfig.getRowCountLimit() + " qeder melumat gele biler,saheleri doldurun",
					null, "data-too-long");
		}

		List<StudentEntity> entities = repository.search(req.getId(), req.getName(), req.getSurname(), req.getPhone(),
				req.getEmail(), req.getBirthday(), req.getBegin(), req.getLength());

		StudentListResponse resp = new StudentListResponse();

		String username = userService.findUsername();
		UserEntity entity = userService.findByUsername(username);

		List<StudentSingleResponse> responses = new ArrayList<StudentSingleResponse>();
		for (StudentEntity en : entities) {
			if (!entity.getUserId().equals(en.getLibrarianCode())) {
				throw new MyException("basqa kitabxanacinin telebesini axtara bilmersen", null, "accsess-denied");
			}
			StudentSingleResponse r = new StudentSingleResponse();
			mapper.map(en, r);
			responses.add(r);
		}
		resp.setBooks(responses);
		resp.setTotalSize(searchResultCount);
		return resp;
	}

	public StudentEntity findById(Integer studentCode) {
		Optional<StudentEntity> op = repository.findById(studentCode);
		if (!op.isPresent()) {
			throw new MyException(Message.NAME_NOT_FOUND, null, Message.ID_NOT_FOUND);
		}
		StudentEntity en = op.get();
		return en;

	}

}