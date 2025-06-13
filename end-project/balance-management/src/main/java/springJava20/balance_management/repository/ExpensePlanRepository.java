package springJava20.balance_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springJava20.balance_management.entity.ExpensePlanEntity;

public interface ExpensePlanRepository extends JpaRepository<ExpensePlanEntity, Integer> {

}
