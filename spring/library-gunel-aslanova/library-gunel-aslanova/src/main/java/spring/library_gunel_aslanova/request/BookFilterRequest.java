package spring.library_gunel_aslanova.request;

import java.time.LocalDate;

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

	private Integer startPrice;

	private Integer endPrice;

	private LocalDate startDate;

	private LocalDate endDate;

}
