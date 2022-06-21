package com.example.demo.Repository;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Repo extends JpaRepository<Users,Long> {
    Optional<Users> findUsersByEmail(String email);
}
