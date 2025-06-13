package springJava20.balance_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springJava20.balance_management.entity.ExpenseCategoryEntity;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategoryEntity, Integer> {

	@Query(value = "select * from expense_categories where user_id=?1 limit ?2,?3", nativeQuery = true)
	List<ExpenseCategoryEntity> getAll(Integer id, Integer begin, Integer length);

	@Query(value = "select * from expense_categories where name=?1", nativeQuery = true)
	Optional<ExpenseCategoryEntity> findByName(String name);

}
