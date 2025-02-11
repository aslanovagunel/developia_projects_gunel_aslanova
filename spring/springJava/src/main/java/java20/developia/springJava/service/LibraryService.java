package java20.developia.springJava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java20.developia.springJava.exception.MyException;
import java20.developia.springJava.model.LibraryAdd;
import java20.developia.springJava.model.LibraryEntity;
import java20.developia.springJava.model.LibraryUpdate;
import java20.developia.springJava.repository.LibraryRepository;
import java20.developia.springJava.response.LibraryListResponse;
import java20.developia.springJava.response.LibrarySingleResponse;

@Service
public class LibraryService {

	@Autowired
	private LibraryRepository repository;

	@Autowired
	private ModelMapper mapper;

	public LibraryListResponse getAll() {

		List<LibraryEntity> entities = repository.findAll();
		List<LibrarySingleResponse> singleResponses = new ArrayList<LibrarySingleResponse>();

		for (LibraryEntity libEn : entities) {
			LibrarySingleResponse response = new LibrarySingleResponse();
			mapper.map(libEn, response);
			singleResponses.add(response);
		}
		LibraryListResponse listResponse = new LibraryListResponse();
		listResponse.setLibraries(singleResponses);

		return listResponse;
	}

	public void add(LibraryAdd libraryAdd) {
		LibraryEntity entity = new LibraryEntity();
		mapper.map(libraryAdd, entity);
		repository.save(entity);

	}

	public LibraryListResponse search(String name) {
		List<LibraryEntity> entities = repository.findAllByNameContaining(name);

		if (entities.isEmpty()) {
			throw new MyException("axtarisinizin neticesi yoxdur", null, "search-not-found");
		}
		List<LibrarySingleResponse> singleResponses = new ArrayList<LibrarySingleResponse>();

		for (LibraryEntity entity : entities) {
			LibrarySingleResponse response = new LibrarySingleResponse();
			mapper.map(entity, response);
			singleResponses.add(response);
		}
		LibraryListResponse listResponse = new LibraryListResponse();
		listResponse.setLibraries(singleResponses);
		return listResponse;
	}

	public void deleteById(Integer id) {
		Optional<LibraryEntity> byId = repository.findById(id);
		if (!byId.isPresent()) {
			throw new MyException("id tapilmadi", null, "id-not-found");
		}
		repository.deleteById(id);

	}

	public void updateById(Integer id, LibraryUpdate libUpdate) {
		Optional<LibraryEntity> byId = repository.findById(id);
		if (!byId.isPresent()) {
			throw new MyException("id tapilmadi", null, "id-not-found");
		}

		LibraryEntity entity = byId.get();
		mapper.map(libUpdate, entity);
		repository.save(entity);

	}

}
