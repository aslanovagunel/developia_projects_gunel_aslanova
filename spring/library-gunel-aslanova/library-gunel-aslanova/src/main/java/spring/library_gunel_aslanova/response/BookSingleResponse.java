package spring.library_gunel_aslanova.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;

@Data
public class BookSingleResponse {
	private Integer id;

	private String name;

	private String description;

	private BigDecimal price;

	private String author;

	private String color;

	private Integer pageCount;

	private Integer quantity;

	private Double weight;

	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate publishDate;
}
