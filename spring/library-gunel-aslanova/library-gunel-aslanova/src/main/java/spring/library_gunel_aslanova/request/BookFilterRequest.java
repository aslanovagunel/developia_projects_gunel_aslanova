package spring.library_gunel_aslanova.request;

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

	private String startPrice;

	private String endPrice;

	private String startDate;

	private String endDate;

}
