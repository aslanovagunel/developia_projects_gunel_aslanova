package java20.developia.springJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java20.developia.springJava.exception.MyException;
import java20.developia.springJava.model.LibraryAdd;
import java20.developia.springJava.model.LibraryUpdate;
import java20.developia.springJava.response.LibraryListResponse;
import java20.developia.springJava.service.LibraryService;

@RestController
@RequestMapping(path = "/libraries")
public class LibraryController {

	@Autowired
	private LibraryService service;

	@GetMapping
	public LibraryListResponse getAll() {
		return service.getAll();
	}

	@PostMapping
	public void add(@Valid @RequestBody LibraryAdd libraryAdd, BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException("Melumatda problem var", result,"validation");
		}
		service.add(libraryAdd);
	}


	@GetMapping(path = "search")
	public LibraryListResponse search(@RequestParam(name = "query") String name) {
		return service.search(name);
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteById(@PathVariable Integer id) {
		service.deleteById(id);
	}
	
	@PutMapping(path = "/{id}")
	public void uptadeById(@PathVariable Integer id, @Valid @RequestBody LibraryUpdate libUpdate,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new MyException("Melumatda problem var", result, "validation");
		}
		service.updateById(id, libUpdate);
	}




}
