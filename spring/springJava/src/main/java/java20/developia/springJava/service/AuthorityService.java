package java20.developia.springJava.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java20.developia.springJava.repository.AuthorityRepository;
import java20.developia.springJava.request.StudentAddRequest;

@Service
@Transactional
public class AuthorityService {

	@Autowired
	private AuthorityRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	public void addStudentAuthorities(StudentAddRequest req) {
		
		repository.addStudentAuthorities(req.getUsername());

	}

}

	


