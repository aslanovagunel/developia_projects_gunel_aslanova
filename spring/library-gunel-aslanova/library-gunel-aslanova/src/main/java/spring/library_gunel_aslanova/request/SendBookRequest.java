package spring.library_gunel_aslanova.request;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendBookRequest {

	private String suggestion;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate fromDate;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate endDate;


}
