package spring.library_gunel_aslanova.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookFilterRequestForStudent {

	private Integer categoryId;

	private String name;

	private Integer begin;

	private Integer length;


}
