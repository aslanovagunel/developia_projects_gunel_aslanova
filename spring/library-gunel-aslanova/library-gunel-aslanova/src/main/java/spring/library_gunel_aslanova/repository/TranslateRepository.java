package spring.library_gunel_aslanova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.library_gunel_aslanova.entity.TranslateEntity;

public interface TranslateRepository extends JpaRepository<TranslateEntity, Integer> {

	List<TranslateEntity> findAllByLanguage(String language);

}
