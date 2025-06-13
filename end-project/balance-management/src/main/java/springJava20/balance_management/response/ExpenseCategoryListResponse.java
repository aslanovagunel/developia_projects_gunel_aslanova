package springJava20.balance_management.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseCategoryListResponse {
	private List<ExpenseCategorySingleResponse> responses;
}
