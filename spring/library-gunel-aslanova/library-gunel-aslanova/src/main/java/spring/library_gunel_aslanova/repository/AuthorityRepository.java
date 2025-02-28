package spring.library_gunel_aslanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import spring.library_gunel_aslanova.entity.AuthorityEntity;

@Transactional
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {

	@Modifying
	@Query(value = "insert into authorities(username,authority) select ?1,authority from authority_list where librarian=1", nativeQuery = true)
	void addLibrarianAuthorities(String username);


}
