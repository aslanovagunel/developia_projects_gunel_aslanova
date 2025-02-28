package spring.library_gunel_aslanova.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.library_gunel_aslanova.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
