package springJava20.balance_management.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import springJava20.balance_management.config.MyConfig;
import springJava20.balance_management.request.ExpenseAddRequest;
import springJava20.balance_management.request.ExpenseCategoryFilterRequest;
import springJava20.balance_management.request.ExpenseFilterRequest;
import springJava20.balance_management.request.ExpenseUpdateRequest;
import springJava20.balance_management.response.ExpenseListResponse;
import springJava20.balance_management.response.ExpenseSingleResponse;
import springJava20.balance_management.service.ExpenseService;

@WebMvcTest(ExpenseController.class)
@Import(MyConfig.class)
public class ExpenseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ExpenseService expenseService;

	@Autowired
	private ObjectMapper objectMapper;

	@WithMockUser
	@Test
	public void testAddShouldReturn201Created() throws Exception {
		ExpenseAddRequest req = new ExpenseAddRequest(1, new BigDecimal(100), "description");
		String requestBody = objectMapper.writeValueAsString(req);

		Mockito.when(expenseService.add(req)).thenReturn(1);

		mockMvc.perform(post("/expense").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1)).andDo(print());

		Mockito.verify(expenseService, times(1)).add(req);
	}

	@WithMockUser
	@Test
	void testGetAllExpenses() throws Exception {
		int begin = 0;
		int length = 5;

		ExpenseListResponse mockResponse = new ExpenseListResponse();
		List<ExpenseSingleResponse> responseList = new ArrayList<>();

		ExpenseSingleResponse expense = new ExpenseSingleResponse();
		expense.setId(1);
		expense.setUserId(10);
		expense.setDate(LocalDate.of(2025, 6, 3));
		expense.setExpCategoryId(2);
		expense.setAmount(BigDecimal.valueOf(50.75));
		expense.setDescription("Lunch");

		responseList.add(expense);
		mockResponse.setResponses(responseList);

		when(expenseService.getAll(begin, length)).thenReturn(mockResponse);

		mockMvc.perform(get("/expense/begin/{begin}/length/{length}", begin, length)).andExpect(status().isOk())
				.andExpect(jsonPath("$.responses").isArray()).andExpect(jsonPath("$.responses[0].id").value(1))
				.andExpect(jsonPath("$.responses[0].userId").value(10))
				.andExpect(jsonPath("$.responses[0].amount").value(50.75))
				.andExpect(jsonPath("$.responses[0].description").value("Lunch")).andDo(print());

		verify(expenseService, times(1)).getAll(begin, length);
	}
	@WithMockUser
	@Test
	void testUpdateShouldReturn200Ok() throws Exception {
		ExpenseUpdateRequest req = new ExpenseUpdateRequest(1, new BigDecimal(100), "description");
		String requestBody = objectMapper.writeValueAsString(req);

		mockMvc.perform(put("/expense").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk()).andDo(print());

		Mockito.verify(expenseService, times(1)).update(req);
	}

	@Test
	@WithMockUser(authorities = "ROLE_DELETE_EXPENSE_CATEGORY")
	public void testDeleteExpenseCategory() throws Exception {
		Integer id = 1;

		mockMvc.perform(delete("/expense/{id}", id).with(csrf())).andExpect(status().isOk()).andDo(print());

		Mockito.verify(expenseService, times(1)).deleteById(id);
	}

	@Test
	@WithMockUser
	void testGetByCategoryAndDateRangeShouldReturn200Ok() throws Exception {
		ExpenseCategoryFilterRequest req = new ExpenseCategoryFilterRequest(0, 2, LocalDate.of(2025, 6, 1),
				LocalDate.of(2025, 6, 3), 1);
		String requestBody = objectMapper.writeValueAsString(req);

		ExpenseListResponse mockResponse = new ExpenseListResponse();
		Mockito.when(expenseService.getByCategoryAndDateRange(req)).thenReturn(mockResponse);

		mockMvc.perform(get("/expense/category-date-range").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(status().isOk()).andDo(print());

		Mockito.verify(expenseService, times(1)).getByCategoryAndDateRange(req);
	}

	@Test
	@WithMockUser
	void testGetByDateRangeShouldReturn200Ok() throws Exception {
		ExpenseFilterRequest req = new ExpenseFilterRequest(0, 2, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 3));
		String requestBody = objectMapper.writeValueAsString(req);

		ExpenseListResponse mockResponse = new ExpenseListResponse();
		Mockito.when(expenseService.getByDateRange(req)).thenReturn(mockResponse);

		mockMvc.perform(
				get("/expense/date-range").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk()).andDo(print());

		Mockito.verify(expenseService, times(1)).getByDateRange(req);
	}
}
