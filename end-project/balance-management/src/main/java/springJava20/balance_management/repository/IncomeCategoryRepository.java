package springJava20.balance_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springJava20.balance_management.entity.IncomeCategoryEntity;

public interface IncomeCategoryRepository extends JpaRepository<IncomeCategoryEntity, Integer> {

	@Query(value = "select * from income_categories where user_id=?1 limit ?2,?3", nativeQuery = true)
	List<IncomeCategoryEntity> getAll(Integer id, Integer begin, Integer length);

	@Query(value = "select * from income_categories where name=?1", nativeQuery = true)
	Optional<IncomeCategoryEntity> findByName(String name);

}
