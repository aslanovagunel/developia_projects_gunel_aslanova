package spring.library_gunel_aslanova.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	private String username;

	private String password;

	private String email;

	private Integer userId;

	private String type;

	private Boolean enabled;

}
