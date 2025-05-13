package spring.library_gunel_aslanova.controller;

import static org.mockito.Mockito.times;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import spring.library_gunel_aslanova.config.MyConfig;
import spring.library_gunel_aslanova.request.BookAddRequest;
import spring.library_gunel_aslanova.service.BookService;

@WebMvcTest(BookController.class)
@Import(MyConfig.class)
public class BookControllerTest {

	private static final String END_POINT_PATH = "/books";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Autowired
	private ObjectMapper objectMapper;

	@WithMockUser
	@Test
	public void testAddShouldReturn201Created() throws Exception {
		BookAddRequest r = new BookAddRequest("name", "A detailed description of the book.", new BigDecimal("19.99"),
				"Author Name", "Red", 300, 50, 0.5, LocalDate.of(2020, 5, 15));

		// BookAddResponse res = new BookAddResponse(1);

		String requestBody = objectMapper.writeValueAsString(r);
		Mockito.when(bookService.add(r)).thenReturn(1);
		
		mockMvc.perform(post(END_POINT_PATH).with(csrf()).contentType("application/json")
				.content(requestBody))
				.andExpect(status().isCreated()).andDo(print());


		Mockito.verify(bookService, times(1)).add(r);
	}

	@WithMockUser
	@Test
	void testAddBook_ValidationError() throws Exception {
		BookAddRequest r = new BookAddRequest("", "", new BigDecimal("12"), "", "", 23, 43, 0.8,
				LocalDate.of(2020, 5, 15));


		Mockito.when(bookService.add(r)).thenReturn(1);
		String requestBody = objectMapper.writeValueAsString(r);
		mockMvc.perform(post(END_POINT_PATH).with(csrf()).contentType("application/json").content(requestBody))
				.andExpect(status().isBadRequest());
	}


}
