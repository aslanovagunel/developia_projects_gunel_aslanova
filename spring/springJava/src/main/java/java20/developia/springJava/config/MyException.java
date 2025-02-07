package java20.developia.springJava.config;

import org.springframework.validation.BindingResult;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class MyException extends RuntimeException {

	private BindingResult result;
	private String type;


	public MyException(String message, BindingResult result, String type) {
		super(message);
		this.result = result;
		this.type = type;
	}


}
