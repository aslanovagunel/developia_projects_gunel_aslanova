package java20.developia.springJava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java20.developia.springJava.entity.StudentEntity;
import java20.developia.springJava.exception.MyException;
import java20.developia.springJava.repository.StudentRepository;
import java20.developia.springJava.request.StudentAddRequest;
import java20.developia.springJava.request.StudentUpdateRequest;
import java20.developia.springJava.response.StudentAddResponse;
import java20.developia.springJava.response.StudentListResponse;
import java20.developia.springJava.response.StudentSingleResponse;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private ModelMapper mapper;


	public StudentAddResponse add(StudentAddRequest req) {
		StudentEntity entity = new StudentEntity();
		mapper.map(req, entity);
		
		repository.save(entity);
		
		userService.cheskStudentExists(req);
		userService.addStudent(req, entity.getId());

		authorityService.addStudentAuthorities(req);

		StudentAddResponse resp = new StudentAddResponse();
		resp.setId(entity.getId());
		return resp;

	}

	public void deleteById(Integer id) {
		Optional<StudentEntity> byId = repository.findById(id);
		if (!byId.isPresent()) {
			throw new MyException("id tapilmadi", null, "id-not-found");
		}
		repository.deleteById(id);
	}

	public StudentListResponse find(String name) {
		List<StudentEntity> entities = repository.findAllByNameContaining(name);
		List<StudentSingleResponse> singleResponces = new ArrayList<StudentSingleResponse>();
		StudentListResponse listResponce = new StudentListResponse();

		for (StudentEntity s : entities) {
			StudentSingleResponse sR = new StudentSingleResponse();
			mapper.map(s, sR);
			singleResponces.add(sR);
		}
		listResponce.setStudents(singleResponces);

		return listResponce;
	}

	public StudentSingleResponse findById(Integer id) {
		Optional<StudentEntity> op = repository.findById(id);
		if (!op.isPresent()) {
			throw new MyException("id tapilmadi", null, "id-not-found");
		}
		StudentSingleResponse response = new StudentSingleResponse();
		StudentEntity entity = op.get();
		mapper.map(entity, response);

		return response;

	}

	public void update(Integer id, StudentUpdateRequest update) {
		Optional<StudentEntity> byId = repository.findById(id);
		if (!byId.isPresent()) {
			throw new MyException("id tapilmadi", null, "id-not-found");
		}
		StudentEntity entity = byId.get();
		mapper.map(update, entity);
		repository.save(entity);

	}


}
