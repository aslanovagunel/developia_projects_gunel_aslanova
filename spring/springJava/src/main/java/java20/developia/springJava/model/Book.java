package java20.developia.springJava.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(min = 3, message = "min 3 setir")
	@Size(max = 40, message = "max 40 setir")
	private String name;

	@NotNull
	private String description;

	@PositiveOrZero
	@Min(value = 0, message = "min 0 olmalidir")
	@Digits(integer = 5, fraction = 2)
	private BigDecimal price;

	private String author;
	private String color;
	private Integer pageCount;
	private Integer quantity;
	private Double weight;

	@PastOrPresent
	private LocalDate date;


	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "email yazilmalidir")
	private String email;

}
