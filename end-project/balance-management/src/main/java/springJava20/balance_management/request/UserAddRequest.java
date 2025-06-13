package springJava20.balance_management.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddRequest {

	@NotBlank(message = "İstifadəçi adı boş ola bilməz.")
	@Size(min = 3, max = 20, message = "İstifadəçi adı 3 ilə 20 simvol arasında olmalıdır.")
	private String username;

	@NotBlank(message = "Şifrə boş ola bilməz.")
	@Size(min = 8, message = "Şifrə ən az 8 simvol olmalıdır.")
	@Pattern(regexp = ".*[A-Z].*", message = "Şifrədə ən az bir böyük hərf olmalıdır.")
	@Pattern(regexp = ".*[a-z].*", message = "Şifrədə ən az bir kiçik hərf olmalıdır.")
	@Pattern(regexp = ".*\\d.*", message = "Şifrədə ən az bir rəqəm olmalıdır.")
	@Pattern(regexp = ".*[!@#$%^&*()].*", message = "Şifrədə ən az bir xüsusi simvol (!@#$%^&*()) olmalıdır.")
	private String password;
}
