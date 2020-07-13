package com.marcinwo.todolist.app.repository;

import com.marcinwo.todolist.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    Optional<User> findByUserNameAndHasExpiredFalse(String username);
    Optional<User> findByIdAndHasExpiredFalse(Long id);
}
