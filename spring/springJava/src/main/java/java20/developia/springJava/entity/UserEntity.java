package java20.developia.springJava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	private String username;

	private String password;

	private Integer enabled;

	private Integer userId;
}
