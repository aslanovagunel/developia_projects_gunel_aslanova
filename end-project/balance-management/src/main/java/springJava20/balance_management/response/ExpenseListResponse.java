package springJava20.balance_management.response;

import java.util.List;

import lombok.Data;

@Data
public class ExpenseListResponse {
	private List<ExpenseSingleResponse> responses;
}
