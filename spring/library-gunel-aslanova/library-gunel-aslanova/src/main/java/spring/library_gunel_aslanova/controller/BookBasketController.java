package spring.library_gunel_aslanova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import spring.library_gunel_aslanova.request.LendBookRequest;
import spring.library_gunel_aslanova.request.ReturnBookRequest;
import spring.library_gunel_aslanova.response.LendBookListResponse;
import spring.library_gunel_aslanova.response.ShowLendBookListResponse;
import spring.library_gunel_aslanova.service.BookBasketService;
import spring.library_gunel_aslanova.service.EmailService;

@RestController
@RequestMapping(path = "/basket")
public class BookBasketController {

	@Autowired
	private BookBasketService service;

	@Autowired
	private EmailService emailService;

	@PostMapping(path = "/lend-book")
	@PreAuthorize(value = "hasAuthority('ROLE_LEND_BOOK')")
	public ResponseEntity<LendBookListResponse> lendBook(@RequestBody LendBookRequest req) {
		LendBookListResponse resp = service.lendBook(req);
		return new ResponseEntity<LendBookListResponse>(resp, HttpStatus.OK);
	}

	@GetMapping(path = "/show-lend-book")
	@PreAuthorize(value = "hasAuthority('ROLE_SHOW_LEND_BOOK')")
	public ResponseEntity<ShowLendBookListResponse> showLendBook() {
		ShowLendBookListResponse resp = service.showLendBook();
		return new ResponseEntity<ShowLendBookListResponse>(resp, HttpStatus.OK);
	}

	@PutMapping(path = "/return-book")
	@PreAuthorize(value = "hasAuthority('ROLE_RETURN_BOOK')")
	public ResponseEntity<ShowLendBookListResponse> returnBook(@RequestBody ReturnBookRequest req) {
		ShowLendBookListResponse resp = service.returnBook(req);
		return new ResponseEntity<ShowLendBookListResponse>(resp, HttpStatus.OK);
	}

	@GetMapping(path = "/show-return-book")
	@PreAuthorize(value = "hasAuthority('ROLE_SHOW_RETURN_BOOK')")
	public ResponseEntity<ShowLendBookListResponse> showReturnBook() {
		ShowLendBookListResponse resp = service.showReturnBook();
		return new ResponseEntity<ShowLendBookListResponse>(resp, HttpStatus.OK);
	}

	@GetMapping(path = "/show-late-return-book")
	@PreAuthorize(value = "hasAuthority('ROLE_LATE_RETURN_BOOK')")
	public ResponseEntity<ShowLendBookListResponse> getLateReturnedBooks() {
		ShowLendBookListResponse resp = service.getLateReturnedBooks();
		return new ResponseEntity<ShowLendBookListResponse>(resp, HttpStatus.OK);
	}

	@PostMapping(path = "/send-email")
	@PreAuthorize(value = "hasAuthority('ROLE_SEND_EMAIL')")
	public ResponseEntity<String> sendEmail() throws JsonProcessingException {
		service.sendEmail();
		return ResponseEntity.ok("mesaj gonderildi");
	}

	@PostMapping(path = "/send-emailii")
	@PreAuthorize(value = "hasAuthority('ROLE_SEND_EMAILI')")
	public ResponseEntity<String> sendEmailii() {
		emailService.sendOverdueEmail("aslanovagunel409@gmai.coml", "gunel", "kitabi qaytar");
		return ResponseEntity.ok("mesaj gonderildi");
	}
}
