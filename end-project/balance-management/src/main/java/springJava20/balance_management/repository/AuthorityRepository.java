package springJava20.balance_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import springJava20.balance_management.entity.AuthorityEntity;

@Transactional
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {

	@Modifying
	@Query(value = "insert into authorities(username,authority) select ?1,authority from authority_list where user_num=1", nativeQuery = true)
	void addUserAuthorities(String username);

}
