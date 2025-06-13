package springJava20.balance_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import springJava20.balance_management.repository.AuthorityRepository;

@Service
@Transactional
public class AuthorityService {

	@Autowired
	private AuthorityRepository repository;

	public void addUserAuthorities(String username) {
		repository.addUserAuthorities(username);

	}


}
