package java20.developia.springJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java20.developia.springJava.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
