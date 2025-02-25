package spring.library_gunel_aslanova.response;

import java.util.List;

import lombok.Data;

@Data

public class MyErrorResponse {
	private List<MyFieldError> errors;
	private String message;
}
