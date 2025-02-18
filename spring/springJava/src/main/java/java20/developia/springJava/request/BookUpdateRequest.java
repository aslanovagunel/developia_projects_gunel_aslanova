package java20.developia.springJava.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookUpdateRequest {

	private Integer id;

	@Size(min = 2, message = "min 2")
	@Size(max = 6, message = "max 6")
	private String name;

	@Size(min = 12, message = "min 12")
	@Size(max = 60, message = "max 60")
	private String description;

	private BigDecimal price;

	private String author;

	private String color;

	private Integer pageCount;

	private Integer quantity;

	private Double weight;

	private LocalDate date;

	private String email;

}
