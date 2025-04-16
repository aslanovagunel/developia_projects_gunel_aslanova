package spring.library_gunel_aslanova.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import spring.library_gunel_aslanova.entity.TranslateEntity;
import spring.library_gunel_aslanova.repository.TranslateRepository;
import spring.library_gunel_aslanova.response.TranslateListResponse;
import spring.library_gunel_aslanova.response.TranslateSingleResponse;

@Service
@Transactional
public class TranslateService {

	@Autowired
	private TranslateRepository repository;

	@Autowired
	private ModelMapper mapper;

	public TranslateListResponse getMenu(String language) {
		List<TranslateEntity> entities = repository.findAllByLanguage(language);

		TranslateListResponse resp = new TranslateListResponse();
		List<TranslateSingleResponse> responses = new ArrayList<TranslateSingleResponse>();
		for (TranslateEntity b : entities) {
			TranslateSingleResponse r = new TranslateSingleResponse();
			mapper.map(b, r);
			responses.add(r);
		}

		resp.setList(responses);
		return resp;

	}

}
