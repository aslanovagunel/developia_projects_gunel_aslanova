package springJava20.balance_management.controller;

import static org.mockito.Mockito.times;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

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
import springJava20.balance_management.repository.ExpensePlanRepository;
import springJava20.balance_management.request.ExpensePlanRequest;
import springJava20.balance_management.response.ExpensePlanCheckResponse;
import springJava20.balance_management.service.ExpensePlanService;
import springJava20.balance_management.service.ExpenseService;
import springJava20.balance_management.service.UserService;

@WebMvcTest(ExpensePlanController.class)
@Import(MyConfig.class)
public class ExpensePlanControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ExpensePlanService expensePlanService;

	@MockBean
	private ExpenseService expenseService;

	@MockBean
	private UserService userService;

	@MockBean
	private ExpensePlanRepository expensePlanRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@WithMockUser
	@Test
	public void testAddShouldReturn201Created() throws Exception {
		ExpensePlanRequest req = new ExpensePlanRequest(new BigDecimal(100), LocalDate.of(2025, 6, 1),
				LocalDate.of(2025, 6, 2));
		String requestBody = objectMapper.writeValueAsString(req);

		Mockito.when(expensePlanService.addExpensePlan(req)).thenReturn(1);

		mockMvc.perform(post("/expense-plan").with(csrf()).contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1)).andDo(print());

		Mockito.verify(expensePlanService, times(1)).addExpensePlan(req);
	}

	@WithMockUser
	@Test
	void testCheckExpensePlanShouldReturn200Ok() throws Exception {
		int planId = 1;

	    ExpensePlanCheckResponse mockResponse = new ExpensePlanCheckResponse();
	    mockResponse.setTotalAmount(BigDecimal.valueOf(1000));
	    mockResponse.setRealExpense(BigDecimal.valueOf(400));
	    mockResponse.setStatus("siz hələ plandan az xərcləmisiniz");

	    Mockito.when(expensePlanService.checkExpensePlan(planId)).thenReturn(mockResponse);

	    mockMvc.perform(get("/expense-plan/id/{id}", planId))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	        .andExpect(jsonPath("$.totalAmount").value(1000))
	        .andExpect(jsonPath("$.realExpense").value(400))
	        .andExpect(jsonPath("$.status").value("siz hələ plandan az xərcləmisiniz"))
	        .andDo(print());

	    Mockito.verify(expensePlanService, times(1)).checkExpensePlan(planId);
	}



}
