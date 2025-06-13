package springJava20.balance_management.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCategoryAddRequest {

	private LocalDate createdDate = LocalDate.now();

	@NotBlank(message = "Kateqoriya adı boş ola bilməz.")
	@Size(min = 2, max = 50, message = "Kateqoriya adı 2 ilə 50 simvol arasında olmalıdır.")
	private String name;


}
