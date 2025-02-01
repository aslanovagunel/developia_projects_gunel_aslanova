package java20.developia.springJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java20.developia.springJava.model.Computer;
import java20.developia.springJava.service.ComputerService;

@RestController
@RequestMapping(path = "/computers")
public class ComputerController {

	@Autowired
	private ComputerService service;

	@PostMapping
	public void add(@Valid @RequestBody Computer computer) {
		service.add(computer);
	}
}
