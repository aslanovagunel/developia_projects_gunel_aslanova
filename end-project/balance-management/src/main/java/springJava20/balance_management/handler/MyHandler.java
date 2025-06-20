package springJava20.balance_management.handler;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import springJava20.balance_management.exception.MyException;
import springJava20.balance_management.response.MyErrorResponse;
import springJava20.balance_management.response.MyFieldError;

@RestControllerAdvice
public class MyHandler {

	@Autowired
	private ModelMapper mapper;

	@ExceptionHandler
	public MyErrorResponse myHandler(MyException e) {

		MyErrorResponse resp = new MyErrorResponse();
		BindingResult result = e.getResult();

		if (result != null) {
			List<FieldError> errors = result.getFieldErrors();
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

	@ExceptionHandler
	public MyErrorResponse handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e) {
		MyErrorResponse resp = new MyErrorResponse();

//		resp.setDate(LocalDateTime.now());
		resp.setMessage(e.getMessage());
		return resp;

	}

	@ExceptionHandler
	public MyErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		MyErrorResponse resp = new MyErrorResponse();

//		resp.setDate(LocalDateTime.now());
		resp.setMessage(e.getMessage());
		return resp;

	}
}
