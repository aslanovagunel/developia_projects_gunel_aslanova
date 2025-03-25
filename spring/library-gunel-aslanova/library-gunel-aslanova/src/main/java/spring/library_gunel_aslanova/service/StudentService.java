package spring.library_gunel_aslanova.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.library_gunel_aslanova.entity.StudentEntity;
import spring.library_gunel_aslanova.repository.StudentRepository;
import spring.library_gunel_aslanova.request.StudentAddRequest;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository repository;

	@Autowired
	private ModelMapper mapper;

	public Integer add(StudentAddRequest req) {
		StudentEntity en = new StudentEntity();
		mapper.map(req, en);
		repository.save(en);
		return en.getId();
	}

}
