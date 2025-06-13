package springJava20.balance_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import springJava20.balance_management.exception.MyException;
import springJava20.balance_management.request.UserAddRequest;
import springJava20.balance_management.response.UserAddResponse;
import springJava20.balance_management.service.UserService;
import springJava20.balance_management.util.Message;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping()
	public ResponseEntity<UserAddResponse> add(@Valid @RequestBody UserAddRequest req,
			BindingResult result) {
		if(result.hasErrors()) {
			throw new MyException(Message.VALIDATION_MESSAGE, result, Message.VALIDATION_TYPE);
		}
		Integer id = service.add(req);
		UserAddResponse resp = new UserAddResponse();
		resp.setId(id);
		return new ResponseEntity<UserAddResponse>(resp, HttpStatus.CREATED);
	}

}
