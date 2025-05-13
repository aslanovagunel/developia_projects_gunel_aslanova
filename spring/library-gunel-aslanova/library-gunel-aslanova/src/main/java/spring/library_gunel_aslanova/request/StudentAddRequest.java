package spring.library_gunel_aslanova.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class StudentAddRequest {

	@Size(min = 2, max = 50, message = "Ad 2-50 simvol arasında olmalıdır!")
	private String name;

	@Size(min = 2, max = 50, message = "Soyad 2-50 simvol arasında olmalıdır!")
	private String surname;

	@NotBlank(message = "Email boş ola bilməz!")
	@Email(message = "Email formatı düzgün olmalıdır!")
	private String email;

	@Size(min = 3, max = 30, message = "İstifadəçi adı 3-30 simvol arasında olmalıdır!")
	private String username;

	@NotBlank(message = "Şifrə boş ola bilməz!")
	private String password;

	@NotBlank(message = "Telefon nömrəsi boş ola bilməz!")
	private String phone;

	@NotNull(message = "Doğum tarixi boş ola bilməz!")
	@Past(message = "Doğum tarixi keçmişdə olmalıdır!")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;
}
