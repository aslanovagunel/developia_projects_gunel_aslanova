package spring.library_gunel_aslanova.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.library_gunel_aslanova.response.TranslateListResponse;
import spring.library_gunel_aslanova.service.TranslateService;

@RestController
@RequestMapping(path = "/translates")
public class TranslateController {

	@Autowired
	private TranslateService service;

	@GetMapping
	public ResponseEntity<TranslateListResponse> getMenu(@RequestHeader(name = "Accept-Language") Locale l) {
		String language = l.getLanguage();

		TranslateListResponse resp = service.getMenu(language);
		return ResponseEntity.ok(resp);
	}

}
