package spring.library_gunel_aslanova.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookFilterRequest {

	private String id;

	private String name;

	private String description;

	@Min(value = 0, message = "min 0 olsun")
	private Integer startPrice;

	@Max(value = 100, message = "max 100 olsun")
	private Integer endPrice;

	private LocalDate startDate;

	private LocalDate endDate;

}
