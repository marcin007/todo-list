package com.marcinwo.todolist.app.repository;

import com.marcinwo.todolist.app.entity.Task;
import com.marcinwo.todolist.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long > {
    Set<Task> findAllByUsersIdAndHasExpiredFalse(Long userId);
    Optional<Task>findByIdAndHasExpiredFalse(Long ig);
    List<Task> findAllByUsersUserNameAndHasExpiredFalse(String username);

}
