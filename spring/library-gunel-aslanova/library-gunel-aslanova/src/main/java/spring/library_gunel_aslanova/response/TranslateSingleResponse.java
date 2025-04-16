package spring.library_gunel_aslanova.response;

import lombok.Data;

@Data
public class TranslateSingleResponse {

	private Integer id;

	private String language;

	private String word;

	private String translate;
}
