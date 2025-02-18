package java20.developia.springJava.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErrorResponce {
	private List<MyFieldError> errors;
	private String message;
	private String type;
	private LocalDateTime date;
}
