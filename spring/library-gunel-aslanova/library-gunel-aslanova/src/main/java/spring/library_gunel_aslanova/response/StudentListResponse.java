package spring.library_gunel_aslanova.response;

import java.util.List;

import lombok.Data;

@Data
public class StudentListResponse {
	private List<StudentSingleResponse> books;

	private Long totalSize;
}
