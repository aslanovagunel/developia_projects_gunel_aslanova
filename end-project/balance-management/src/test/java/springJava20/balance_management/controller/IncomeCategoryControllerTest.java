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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import springJava20.balance_management.config.MyConfig;
import springJava20.balance_management.request.IncomeCategoryAddRequest;
import springJava20.balance_management.request.IncomeCategoryUpdateRequest;
import springJava20.balance_management.response.IncomeCategoryListResponse;
import springJava20.balance_management.response.IncomeCategorySingleResponse;
import springJava20.balance_management.service.IncomeCategoryService;

@WebMvcTest(IncomeCategoryController.class)
@Import(MyConfig.class)
public class IncomeCategoryControllerTest {

	private static final String END_POINT_PATH = "/income-category";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IncomeCategoryService incomeCategoryService;

	@Autowired
	private ObjectMapper objectMapper;

	@WithMockUser
	@Test
	public void testAddShouldReturn201Created() throws Exception {
		IncomeCategoryAddRequest r = new IncomeCategoryAddRequest(LocalDate.of(2025, 06, 03), "name");

		String requestBody = objectMapper.writeValueAsString(r);
		Mockito.when(incomeCategoryService.add(r)).thenReturn(1);

		mockMvc.perform(post(END_POINT_PATH).with(csrf()).contentType("application/json").content(requestBody))
				.andExpect(status().isCreated()).andDo(print());

		Mockito.verify(incomeCategoryService, times(1)).add(r);
	}

	@WithMockUser
	@Test
	public void testUpdateShouldReturn200Ok() throws Exception {
		IncomeCategoryUpdateRequest request = new IncomeCategoryUpdateRequest(1, "Updated Name");

		String requestBody = objectMapper.writeValueAsString(request);
		Mockito.doNothing().when(incomeCategoryService).update(request);

		mockMvc.perform(put(END_POINT_PATH).with(csrf()).contentType("application/json").content(requestBody))
				.andExpect(status().isOk()).andDo(print());

		Mockito.verify(incomeCategoryService, times(1)).update(request);
	}

	@Test
	@WithMockUser(authorities = "ROLE_DELETE_EXPENSE_CATEGORY")
	public void testDeleteExpenseCategory() throws Exception {
		Integer id = 1;

		Mockito.doNothing().when(incomeCategoryService).deleteById(id);

		mockMvc.perform(delete(END_POINT_PATH + "/" + id).with(csrf())).andExpect(status().isOk()).andDo(print());

		Mockito.verify(incomeCategoryService, times(1)).deleteById(id);
	}

	@Test
	@WithMockUser
	void testGetAllExpenseCategories() throws Exception {
		int begin = 0;
		int length = 5;

		IncomeCategoryListResponse mockResponse = new IncomeCategoryListResponse();
		List<IncomeCategorySingleResponse> responses = new ArrayList<>();
		IncomeCategorySingleResponse singleResponse = new IncomeCategorySingleResponse();
		singleResponse.setId(1);
		singleResponse.setUserId(10);
		singleResponse.setCreatedDate(LocalDate.of(2025, 6, 3));
		singleResponse.setName("Food");

		responses.add(singleResponse);
		mockResponse.setResponses(responses);

		when(incomeCategoryService.getAll(begin, length)).thenReturn(mockResponse);

		mockMvc.perform(get("/income-category/begin/{begin}/length/{length}", begin, length)).andExpect(status().isOk())
				.andExpect(jsonPath("$.responses").isArray()).andExpect(jsonPath("$.responses[0].id").value(1))
				.andExpect(jsonPath("$.responses[0].userId").value(10))
				.andExpect(jsonPath("$.responses[0].createdDate").value("2025-06-03"))
				.andExpect(jsonPath("$.responses[0].name").value("Food")).andDo(print());

		verify(incomeCategoryService, times(1)).getAll(begin, length);
	}
}
