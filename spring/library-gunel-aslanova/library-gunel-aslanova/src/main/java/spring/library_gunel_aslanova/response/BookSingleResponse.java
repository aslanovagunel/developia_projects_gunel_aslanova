package spring.library_gunel_aslanova.response;

import java.math.BigDecimal;
import java.time.LocalDate;

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

	private LocalDate publishDate;
}
