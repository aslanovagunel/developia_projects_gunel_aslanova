package spring.library_gunel_aslanova.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class LibrarianAddRequest {

	@Size(min = 2, max = 50, message = "Ad 2-50 simvol arasında olmalıdır!")
	private String name;

	@Size(min = 2, max = 50, message = "Soyad 2-50 simvol arasında olmalıdır!")
	private String surname;

	@NotBlank(message = "Email boş ola bilməz!")
	@Email(message = "Email formatı düzgün olmalıdır!")
	private String email;

	@NotBlank(message = "İstifadəçi adı boş ola bilməz!")
	@Size(min = 3, max = 30, message = "İstifadəçi adı 3-30 simvol arasında olmalıdır!")
	private String username;

	@NotBlank(message = "Şifrə boş ola bilməz!")
	private String password;

	@NotBlank(message = "Telefon nömrəsi boş ola bilməz!")
	// @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Telefon nömrəsi düzgün
	// formatda olmalıdır!")
	private String phone;

	@NotNull(message = "Doğum tarixi boş ola bilməz!")
	@Past(message = "Doğum tarixi keçmişdə olmalıdır!")
	private LocalDate birthday;
}
