package java20.developia.springJava.model;

import lombok.Data;

@Data
public class BookNotFoundException extends RuntimeException {

	public BookNotFoundException(String message) {
		super(message);
	}

}
