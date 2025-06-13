package springJava20.balance_management.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCategorySingleResponse {

	private Integer id;

	private Integer userId;

	private LocalDate createdDate;

	private String name;
}
