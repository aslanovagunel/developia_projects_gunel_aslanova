package springJava20.balance_management.response;

import java.util.List;

import lombok.Data;

@Data
public class IncomeCategoryListResponse {
	private List<IncomeCategorySingleResponse> responses;
}
