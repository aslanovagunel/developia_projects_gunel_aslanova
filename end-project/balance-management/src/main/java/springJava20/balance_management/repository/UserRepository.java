package springJava20.balance_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springJava20.balance_management.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	@Query(value = "select * from users where username=?1", nativeQuery = true)
	Optional<UserEntity> findByUsername(String username);

}
