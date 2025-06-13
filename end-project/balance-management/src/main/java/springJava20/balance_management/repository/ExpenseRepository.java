package springJava20.balance_management.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springJava20.balance_management.entity.ExpenseEntity;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Integer> {

	@Query(value = "select * from expenses where user_id=?1 limit ?2,?3", nativeQuery = true)
	List<ExpenseEntity> getAll(Integer id, Integer begin, Integer length);

	@Query(value = "select * from expenses where user_id=?1 and exp_category_id=?2 and created_date between ?3 and ?4 limit ?5,?6", nativeQuery = true)
	List<ExpenseEntity> getByCategoryAndDateRange(Integer id, Integer expCategoryId, LocalDate startDate,
			LocalDate endDate, Integer begin, Integer length);

	@Query(value = "select * from expenses where user_id=?1 and created_date between ?2 and ?3 limit ?4,?5", nativeQuery = true)
	List<ExpenseEntity> getByDateRange(Integer id, LocalDate startDate, LocalDate endDate, Integer begin,
			Integer length);

	@Query(value = "select sum(amount) from expenses where user_id=?1 ", nativeQuery = true)
	BigDecimal getCurrentBalance(Integer id);

	@Query(value = "select sum(amount) from expenses where user_id=?1 and created_date=?2", nativeQuery = true)
	BigDecimal getCurrentBalanceAndDate(Integer id, LocalDate date);



}
