package springJava20.balance_management.request;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ReportDateRequest {

	@Min(value = 0, message = "Başlanğıc indeks sıfırdan kiçik ola bilməz.")
	private Integer begin;

	@Min(value = 1, message = "Uzunluq minimum 1 olmalıdır.")
	private Integer length;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate startDate;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate endDate;
}
