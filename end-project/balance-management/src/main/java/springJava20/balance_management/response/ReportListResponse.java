package springJava20.balance_management.response;

import java.util.List;

import lombok.Data;

@Data
public class ReportListResponse {
	private List<ReportSingleResponse> responses;
}
