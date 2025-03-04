package spring.library_gunel_aslanova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.library_gunel_aslanova.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

	List<BookEntity> findAllByNameContaining(String name);

}
