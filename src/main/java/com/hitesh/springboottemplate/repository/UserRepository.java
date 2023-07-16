package com.hitesh.springboottemplate.repository;
import com.hitesh.springboottemplate.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, String> {
    public Optional<Users> findByUsername(String user_id);
    Boolean existsUsersByUsername(String email);
}
