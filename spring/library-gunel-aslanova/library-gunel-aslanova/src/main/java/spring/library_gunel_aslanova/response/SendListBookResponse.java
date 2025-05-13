package spring.library_gunel_aslanova.response;

import java.util.List;

import lombok.Data;

@Data
public class SendListBookResponse {
	private List<SendSingleBookResponse> books;

	private Integer totalSize;
}
