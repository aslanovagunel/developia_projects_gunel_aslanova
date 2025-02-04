package java20.developia.springJava.model;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MyException extends RuntimeException {

	private BindingResult result;

	public MyException(String message, BindingResult result) {
		super(message);
		this.result = result;
	}
	
}
