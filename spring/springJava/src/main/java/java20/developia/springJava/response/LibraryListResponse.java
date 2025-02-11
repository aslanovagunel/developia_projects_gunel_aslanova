package java20.developia.springJava.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LibraryListResponse {
	private List<LibrarySingleResponse> libraries;
}
