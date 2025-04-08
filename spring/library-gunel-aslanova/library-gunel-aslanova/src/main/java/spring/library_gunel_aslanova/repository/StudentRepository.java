package spring.library_gunel_aslanova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.library_gunel_aslanova.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {


	@Query(value = "select * from students where id like %?1%  and lower(name) like %?2% and lower(surname) like %?3% and phone like %?4% and email like %?5% and birthday like %?6% limit ?7 ,?8", nativeQuery = true)
	List<StudentEntity> search(String id, String name, String surname, String phone, String email, String birthday,
			Integer begin, Integer length);

	@Query(value = "select count(*) from students where id like %?1%  and lower(name) like %?2% and lower(surname) like %?3% and phone like %?4% and email like %?5% and birthday like %?6% limit ?7 ,?8", nativeQuery = true)
	Long searchResultCount(String id, String name, String surname, String phone, String email, String birthday,
			Integer begin, Integer length);

}
