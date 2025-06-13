package springJava20.balance_management.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeSingleResponse {

	private Integer id;

	private Integer userId;

	private LocalDate date;

	private Integer incCategoryId;

	private BigDecimal amount;

	private String description;
}
