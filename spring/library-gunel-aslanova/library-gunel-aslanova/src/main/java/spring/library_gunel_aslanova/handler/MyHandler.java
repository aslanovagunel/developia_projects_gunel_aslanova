package spring.library_gunel_aslanova.handler;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import spring.library_gunel_aslanova.exception.MyException;
import spring.library_gunel_aslanova.response.MyErrorResponse;
import spring.library_gunel_aslanova.response.MyFieldError;

@RestControllerAdvice
public class MyHandler {

	@Autowired
	private ModelMapper mapper;

	@ExceptionHandler
	public MyErrorResponse myHandler(MyException e) {

		MyErrorResponse resp = new MyErrorResponse();
		BindingResult result = e.getResult();

		if (result == null) {
			List<FieldError> errors = new ArrayList<FieldError>();
			List<MyFieldError> myErr = new ArrayList<MyFieldError>();

			for (FieldError f : errors) {
				MyFieldError m = new MyFieldError();
				mapper.map(f, m);
				myErr.add(m);
			}
			resp.setErrors(myErr);
		}

		resp.setMessage(e.getMessage());
		resp.setType(e.getType());
		return resp;
	}

	@ExceptionHandler
	public MyErrorResponse handleAuthorizationDeniedException(AuthorizationDeniedException e) {
		MyErrorResponse resp = new MyErrorResponse();

//		resp.setDate(LocalDateTime.now());
		resp.setMessage(e.getMessage());
		return resp;

	}
}
