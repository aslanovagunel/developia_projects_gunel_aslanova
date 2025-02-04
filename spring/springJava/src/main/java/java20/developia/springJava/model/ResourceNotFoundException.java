package java20.developia.springJava.model;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
