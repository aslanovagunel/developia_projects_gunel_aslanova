package spring.library_gunel_aslanova.response;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

@Data
public class SendSingleBookResponse {

	private Integer id;

	private String name;

	private String suggestion;

	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate fromDate;

	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate endDate;
}
