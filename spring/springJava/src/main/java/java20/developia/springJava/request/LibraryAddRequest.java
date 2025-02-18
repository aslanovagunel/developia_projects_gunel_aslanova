package java20.developia.springJava.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LibraryAddRequest {

	@Size(min = 2, message = "min 2 setir")
	@Size(max = 100, message = "max 100 setir")
	private String name;

	@Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "nomreni duzgun formada qeyd edin")
	private String phone;

	@Size(min = 5, message = "min 5 setir")
	@Size(max = 200, message = "max 200 setir")
	private String address;

	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "gmail'i duzgun formada qeyd edin")
	private String email;
}
