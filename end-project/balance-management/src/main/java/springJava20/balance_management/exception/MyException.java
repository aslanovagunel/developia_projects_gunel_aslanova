package springJava20.balance_management.exception;

import org.springframework.validation.BindingResult;

import lombok.Data;

@Data
public class MyException extends RuntimeException {

	private BindingResult result;

	private String type;

	public MyException(String message, BindingResult result, String type) {
		super(message);
		this.result = result;
		this.type = type;
	}
}
