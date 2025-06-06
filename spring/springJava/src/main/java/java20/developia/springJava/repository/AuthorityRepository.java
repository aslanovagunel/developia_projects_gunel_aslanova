package java20.developia.springJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import java20.developia.springJava.entity.AuthorityEntity;

@Transactional
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {

	@Query(value = "insert into authorities(username,authority) select ?1 ,authority from authority_list where student=1", nativeQuery = true)
	@Modifying
	public void addStudentAuthorities(String username);
}
