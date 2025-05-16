package spring.library_gunel_aslanova.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.library_gunel_aslanova.entity.LibrarianEntity;
import spring.library_gunel_aslanova.repository.LibrarianRepository;
import spring.library_gunel_aslanova.request.LibrarianAddRequest;

@Service
@Transactional
public class LibrarianService {

	@Autowired
	private LibrarianRepository repository;

	@Autowired
	private ModelMapper mapper;

	public Integer add(LibrarianAddRequest req) {
		LibrarianEntity en = new LibrarianEntity();
		mapper.map(req, en);
		repository.save(en);
		return en.getId();

	}



}
