package com.vlt.indentityservice.repository;

import com.vlt.indentityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsernameContaining(String username);

    boolean existsUserByUsername(String username);

}
