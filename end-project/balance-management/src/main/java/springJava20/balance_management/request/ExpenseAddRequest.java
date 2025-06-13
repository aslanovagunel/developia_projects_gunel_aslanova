package springJava20.balance_management.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseAddRequest {

	@NotNull(message = "Xərc kateqoriyası seçilməlidir.")
	@Min(value = 1, message = "Xərc kateqoriyası ID-si minimum 1 olmalıdır.")
	private Integer expCategoryId;

	@NotNull(message = "Məbləğ boş ola bilməz.")
	@DecimalMin(value = "0.01", message = "Məbləğ minimum 0.01 olmalıdır.")
	@Digits(integer = 10, fraction = 2, message = "Məbləğ maksimum 10 rəqəmli tam və 2 onluq hissədən ibarət ola bilər.")
	private BigDecimal amount;

	@NotBlank(message = "Təsvir boş ola bilməz.")
	@Size(max = 255, message = "Təsvir maksimum 255 simvol ola bilər.")
	private String description;

}
