package spring.library_gunel_aslanova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.library_gunel_aslanova.repository.AuthorityRepository;

@Service
@Transactional
public class AuthorityService {

	@Autowired
	private AuthorityRepository repository;

	public void addLibrarianAuthorities(String username) {
		repository.addLibrarianAuthorities(username);

	}

}
