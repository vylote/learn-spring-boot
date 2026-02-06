package com.vlt.indentityservice.repository;

import com.vlt.indentityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByUsernameContaining(String username);

    boolean existsUserByUsername(String username);

    Optional<User> findByUsername(String username);
}
