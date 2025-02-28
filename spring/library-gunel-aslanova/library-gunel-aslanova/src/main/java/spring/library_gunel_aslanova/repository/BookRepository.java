package spring.library_gunel_aslanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.library_gunel_aslanova.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {

}
