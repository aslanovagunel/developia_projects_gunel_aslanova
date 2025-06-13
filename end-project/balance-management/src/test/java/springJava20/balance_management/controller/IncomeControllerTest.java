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
import springJava20.balance_management.request.IncomeAddRequest;
import springJava20.balance_management.request.IncomeCategoryFilterRequest;
import springJava20.balance_management.request.IncomeFilterRequest;
import springJava20.balance_management.request.IncomeUpdateRequest;
import springJava20.balance_management.response.IncomeListResponse;
import springJava20.balance_management.response.IncomeSingleResponse;
import springJava20.balance_management.service.IncomeService;

@WebMvcTest(IncomeController.class)
@Import(MyConfig.class)
public class IncomeControllerTest {

	private static final String END_POINT_PATH = "/income";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IncomeService incomeService;

	@Autowired
	private ObjectMapper objectMapper;

	@WithMockUser
	@Test
	public void testAddShouldReturn201Created() throws Exception {
		IncomeAddRequest req = new IncomeAddRequest(1, new BigDecimal(100), "description");
		String requestBody = objectMapper.writeValueAsString(req);

		Mockito.when(incomeService.add(req)).thenReturn(1);

		mockMvc.perform(post(END_POINT_PATH).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1)).andDo(print());

		Mockito.verify(incomeService, times(1)).add(req);
	}

	@WithMockUser
	@Test
	void testGetAllIncomes() throws Exception {
		int begin = 0;
		int length = 5;

		IncomeListResponse mockResponse = new IncomeListResponse();
		List<IncomeSingleResponse> responseList = new ArrayList<>();

		IncomeSingleResponse income = new IncomeSingleResponse();
		income.setId(1);
		income.setUserId(10);
		income.setDate(LocalDate.of(2025, 6, 3));
		income.setIncCategoryId(3);
		income.setAmount(BigDecimal.valueOf(1200.50));
		income.setDescription("Salary");

		responseList.add(income);
		mockResponse.setResponses(responseList);

		when(incomeService.getAll(begin, length)).thenReturn(mockResponse);

		mockMvc.perform(get("/income/begin/{begin}/length/{length}", begin, length)).andExpect(status().isOk())
				.andExpect(jsonPath("$.responses").isArray()).andExpect(jsonPath("$.responses[0].id").value(1))
				.andExpect(jsonPath("$.responses[0].userId").value(10))
				.andExpect(jsonPath("$.responses[0].amount").value(1200.50))
				.andExpect(jsonPath("$.responses[0].description").value("Salary")).andDo(print());

		verify(incomeService, times(1)).getAll(begin, length);
	}

	@WithMockUser
	@Test
	void testUpdateShouldReturn200Ok() throws Exception {
		IncomeUpdateRequest req = new IncomeUpdateRequest(1, new BigDecimal(100), "description");
		String requestBody = objectMapper.writeValueAsString(req);

		mockMvc.perform(put(END_POINT_PATH).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk()).andDo(print());

		Mockito.verify(incomeService, times(1)).update(req);
	}

	@Test
	@WithMockUser(authorities = "ROLE_DELETE_EXPENSE_CATEGORY")
	public void testDeleteExpenseCategory() throws Exception {
		Integer id = 1;

		mockMvc.perform(delete("/income/{id}", id).with(csrf())).andExpect(status().isOk()).andDo(print());

		Mockito.verify(incomeService, times(1)).deleteById(id);
	}

	@Test
	@WithMockUser
	void testGetByCategoryAndDateRangeShouldReturn200Ok() throws Exception {
		IncomeCategoryFilterRequest req = new IncomeCategoryFilterRequest(0, 2, LocalDate.of(2025, 6, 1),
				LocalDate.of(2025, 6, 3), 1);
		String requestBody = objectMapper.writeValueAsString(req);

		IncomeListResponse mockResponse = new IncomeListResponse();
		Mockito.when(incomeService.getByCategoryAndDateRange(req)).thenReturn(mockResponse);

		mockMvc.perform(get("/income/category-date-range").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(status().isOk()).andDo(print());

		Mockito.verify(incomeService, times(1)).getByCategoryAndDateRange(req);
	}

	@Test
	@WithMockUser
	void testGetByDateRangeShouldReturn200Ok() throws Exception {
		IncomeFilterRequest req = new IncomeFilterRequest(0, 2, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 3));
		String requestBody = objectMapper.writeValueAsString(req);

		IncomeListResponse mockResponse = new IncomeListResponse();
		Mockito.when(incomeService.getByDateRange(req)).thenReturn(mockResponse);

		mockMvc.perform(
				get("/income/date-range").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk()).andDo(print());

		Mockito.verify(incomeService, times(1)).getByDateRange(req);
	}
}
