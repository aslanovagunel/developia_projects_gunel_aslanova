package springJava20.balance_management.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import springJava20.balance_management.config.MyConfig;
import springJava20.balance_management.request.ReportDateRequest;
import springJava20.balance_management.response.ReportListResponse;
import springJava20.balance_management.response.ReportSingleResponse;
import springJava20.balance_management.service.ReportService;

@WebMvcTest(ReportController.class)
@Import(MyConfig.class)
public class ReportControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReportService reportService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@WithMockUser
	void testGetIncomeAndExpensesBetweenDates() throws Exception {
		ReportDateRequest request = new ReportDateRequest();
		request.setStartDate(LocalDate.of(2025, 6, 1));
		request.setEndDate(LocalDate.of(2025, 6, 2));

		ReportSingleResponse res1 = new ReportSingleResponse();
		res1.setId(1);
		res1.setUserId(10);
		res1.setDate(LocalDate.of(2025, 6, 1));
		res1.setExpCategoryId(1);
		res1.setIncCategoryId(null);
		res1.setAmount(BigDecimal.valueOf(100));
		res1.setDescription("r");

		ReportSingleResponse res2 = new ReportSingleResponse();
		res2.setId(2);
		res2.setUserId(10);
		res2.setDate(LocalDate.of(2025, 6, 2));
		res2.setExpCategoryId(null);
		res2.setIncCategoryId(1);
		res2.setAmount(BigDecimal.valueOf(500));
		res2.setDescription("gh");

		List<ReportSingleResponse> transactions = List.of(res1, res2);

		ReportListResponse mockResponse = new ReportListResponse();
		mockResponse.setResponses(transactions);

		when(reportService.getIncomeAndExpensesBetweenDates(any())).thenReturn(mockResponse);

		mockMvc.perform(get("/report/expense-income-date").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.responses[0].id").value(1))
				.andExpect(jsonPath("$.responses[0].amount").value(100))
				.andExpect(jsonPath("$.responses[0].description").value("r"))
				.andExpect(jsonPath("$.responses[1].id").value(2))
				.andExpect(jsonPath("$.responses[1].amount").value(500))
				.andExpect(jsonPath("$.responses[1].description").value("gh"));
	}
	
	@Test
    @WithMockUser
    void testGetCurrentBalanceAndDate() throws Exception {
        LocalDate date = LocalDate.of(2025, 6, 3);
        BigDecimal mockBalance = BigDecimal.valueOf(999);

        when(reportService.getCurrentBalanceAndDate(date)).thenReturn(mockBalance);

        mockMvc.perform(get("/report/date/{date}", date.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("999"));
    }

	@Test
	@WithMockUser
	void testGetCurrentBalance() throws Exception {

		BigDecimal mockBalance = BigDecimal.valueOf(999);

		when(reportService.getCurrentBalance()).thenReturn(mockBalance);

		mockMvc.perform(get("/report/current-balance")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$").value(999));
	}

}
