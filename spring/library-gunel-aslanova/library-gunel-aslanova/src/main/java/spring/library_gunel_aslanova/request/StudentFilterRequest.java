package spring.library_gunel_aslanova.request;

import lombok.Data;

@Data

public class StudentFilterRequest {

	private String id;

	private String name;

	private String surname;

	private String email;

	private String phone;

	private String birthday;

	private Integer begin;

	private Integer length;

}
