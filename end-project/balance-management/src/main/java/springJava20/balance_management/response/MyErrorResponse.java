package springJava20.balance_management.response;

import java.util.List;

import lombok.Data;

@Data

public class MyErrorResponse {
	private List<MyFieldError> errors;
	private String message;
	private String type;

}
