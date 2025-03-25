package spring.library_gunel_aslanova.response;

import java.util.List;

import lombok.Data;

@Data
public class BookListResponse {

	private List<BookSingleResponse> books;

	private Integer totalSize;
}
