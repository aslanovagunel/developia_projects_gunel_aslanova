package java20.developia.springJava;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java20.developia.springJava.config.MyException;
import java20.developia.springJava.model.ErrorResponce;
import java20.developia.springJava.model.MyFieldError;

@RestControllerAdvice
public class myHandle {

	@ExceptionHandler
	public ErrorResponce myHandleException(MyException e) {
		BindingResult result = e.getResult();
		ErrorResponce responce = new ErrorResponce();

		if (result != null) {
			List<MyFieldError> myFieldErrors = new ArrayList<MyFieldError>();
			List<FieldError> fieldErrors = result.getFieldErrors();
			MyFieldError error = new MyFieldError();

			for (FieldError fieldError : fieldErrors) {
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
}
