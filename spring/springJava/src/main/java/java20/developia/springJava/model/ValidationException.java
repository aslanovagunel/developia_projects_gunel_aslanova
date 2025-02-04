package java20.developia.springJava.model;

import org.springframework.validation.BindingResult;

import lombok.Data;

@Data
public class ValidationException extends RuntimeException {
	private BindingResult result;

	public ValidationException(String message, BindingResult result) {
		super(message);
		this.result = result;
	}

}
