package springJava20.balance_management.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ReportSingleResponse {

	private Integer id;

	private Integer userId;

	private LocalDate date;

	private Integer expCategoryId;

	private Integer incCategoryId;

	private BigDecimal amount;

	private String description;
}
