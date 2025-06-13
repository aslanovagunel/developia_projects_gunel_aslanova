package springJava20.balance_management.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ExpensePlanCheckResponse {

	private BigDecimal totalAmount;

	private LocalDate nowDate;

	private Long daysPassed;

	private BigDecimal plannedExpense;

	private BigDecimal realExpense;

	private BigDecimal difference;

	private String status;

}
