package java20.developia.springJava.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentAddRequest {

	@NotBlank
	@Size(max = 10, message = "max 10 setir")
	private String name;

	@Min(value = 2)
	@Max(value = 10)
	private int grade;

	@Past
	private LocalDate birthdate;

	private String surName;

	@Pattern(regexp = "^\\(\\d{3}\\)-\\d{3}-\\d{3}$")
	private String phone;

	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	private String email;

	private String address;
}
