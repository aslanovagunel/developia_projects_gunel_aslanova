package springJava20.balance_management.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpensePlanRequest {

	private BigDecimal totalAmount;

	private LocalDate startDate;

	private LocalDate endDate;
}
