package java20.developia.springJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java20.developia.springJava.model.Computer;
import java20.developia.springJava.repository.ComputerRepository;

@Service
public class ComputerService {

	@Autowired
	private ComputerRepository repository;

	public void add(@Valid Computer computer) {
		repository.save(computer);
	}

}
