package springJava20.balance_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springJava20.balance_management.entity.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {

}
