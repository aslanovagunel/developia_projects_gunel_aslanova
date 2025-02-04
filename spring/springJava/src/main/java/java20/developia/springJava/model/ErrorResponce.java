package java20.developia.springJava.model;

import java.time.LocalDate;
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
	private LocalDate date;
}
