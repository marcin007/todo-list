package com.marcinwo.todolist.app.repository;

import com.marcinwo.todolist.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNameAndHasExpiredFalse(String username);
    boolean existsByUserNameAndHasExpiredFalse(String username);
    Optional<User> findByIdAndHasExpiredFalse(Long id);
    Optional<User> findByUserNameAndIsEnabledTrueAndHasExpiredFalse(String username);
    List<User> findAllByHasExpiredFalse();

}
