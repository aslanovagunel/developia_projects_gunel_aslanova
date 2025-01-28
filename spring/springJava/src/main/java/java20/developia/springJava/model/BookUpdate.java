package java20.developia.springJava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class BookUpdate {

	private Integer id;
	private String name;
	private String description;

}
