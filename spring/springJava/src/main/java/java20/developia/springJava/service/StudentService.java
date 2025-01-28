package java20.developia.springJava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java20.developia.springJava.model.Student;
import java20.developia.springJava.model.StudentUpdate;
import java20.developia.springJava.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	public List<Student> getAll() {
		return repository.findAll();
	}

	public void addStudent(Student student) {
		repository.save(student);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public List<Student> findStudent(String query) {
		return repository.findAllByPhoneContaining(query);
	}

	public Student findById(Integer id) {
		Optional<Student> op = repository.findById(id);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	public void update(Integer id, StudentUpdate update) {
		Student student = repository.findById(id).get();
		student.setName(update.getName());
		repository.save(student);
	}

}
