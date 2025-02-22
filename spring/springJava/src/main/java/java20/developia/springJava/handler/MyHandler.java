package java20.developia.springJava.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java20.developia.springJava.exception.MyException;
import java20.developia.springJava.response.ErrorResponce;
import java20.developia.springJava.response.MyFieldError;

@RestControllerAdvice
public class MyHandler {

	@ExceptionHandler
	public ErrorResponce myHandleException(MyException e) {
		BindingResult result = e.getResult();
		ErrorResponce responce = new ErrorResponce();

		if (result != null) {
			List<MyFieldError> myFieldErrors = new ArrayList<MyFieldError>();
			List<FieldError> fieldErrors = result.getFieldErrors();

			for (FieldError fieldError : fieldErrors) {
				MyFieldError error = new MyFieldError();
				error.setField(fieldError.getField());
				error.setMessage(fieldError.getDefaultMessage());
				myFieldErrors.add(error);

				responce.setErrors(myFieldErrors);

			}

		}
		responce.setMessage(e.getMessage());
		responce.setDate(LocalDateTime.now());
		responce.setType(e.getType());
		return responce;
	}

	@ExceptionHandler
	public ErrorResponce handleAuthorizationDeniedException(AuthorizationDeniedException e) {
		ErrorResponce resp = new ErrorResponce();

		resp.setDate(LocalDateTime.now());
		resp.setMessage(e.getMessage());
		return resp;

	}
}
