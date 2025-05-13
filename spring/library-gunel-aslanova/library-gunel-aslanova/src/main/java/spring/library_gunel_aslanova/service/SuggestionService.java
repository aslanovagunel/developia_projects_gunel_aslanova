package spring.library_gunel_aslanova.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.library_gunel_aslanova.entity.SuggestionEntity;
import spring.library_gunel_aslanova.repository.SuggestionRepository;
import spring.library_gunel_aslanova.request.SendBookRequest;

@Service
@Transactional
public class SuggestionService {

	@Autowired
	private SuggestionRepository repository;

	@Autowired
	private ModelMapper mapper;

	public Integer getRequestBook(SendBookRequest req, String name) {
		SuggestionEntity en=new SuggestionEntity();
		mapper.map(req, en);
		en.setName(name);
		repository.save(en);
		return en.getId();
		
	}

	public List<SuggestionEntity> getShowSuggestion() {
		List<SuggestionEntity> all = repository.findAll();
		return all;
	}

}
