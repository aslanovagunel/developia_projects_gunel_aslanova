package springJava20.balance_management.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeCategoryUpdateRequest {

	private Integer id;

	@NotBlank(message = "Kateqoriya adı boş ola bilməz.")
	@Size(min = 2, max = 50, message = "Kateqoriya adı 2 ilə 50 simvol arasında olmalıdır.")
	private String name;
}
