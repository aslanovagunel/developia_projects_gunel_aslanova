package springJava20.balance_management.response;

import java.util.List;

import lombok.Data;

@Data
public class IncomeListResponse {
	private List<IncomeSingleResponse> responses;
}
