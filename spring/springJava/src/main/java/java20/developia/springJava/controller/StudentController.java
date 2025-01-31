package java20.developia.springJava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java20.developia.springJava.model.Student;
import java20.developia.springJava.model.StudentUpdate;
import java20.developia.springJava.service.StudentService;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

	@Autowired
	private StudentService service;

	@GetMapping
	public List<Student> getAll() {
		return service.getAll();
	}

	@GetMapping(path = "mini-google")
	public List<Student> findStudent(@RequestParam(name = "search") String query) {
		return service.findStudent(query);
	}

	@GetMapping(path = "/{id}")
	public Student findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public void addStudent(@RequestBody Student student) {
		service.addStudent(student);
	}

	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable Integer id) {
		service.deleteById(id);
	}

	@PutMapping(path = "/{id}")
	public void update(@PathVariable Integer id, @RequestBody StudentUpdate update) {
		service.update(id, update);
	}

}
