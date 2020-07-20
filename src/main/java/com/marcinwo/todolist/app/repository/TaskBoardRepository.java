package com.marcinwo.todolist.app.repository;

import com.marcinwo.todolist.app.entity.TasksBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TaskBoardRepository extends JpaRepository<TasksBoard, Long> {
    Optional<TasksBoard> findByIdAndOwnerIdAndHasExpiredFalse(Long id, Long userId);
    Set<TasksBoard> findAllByUsersUserName(String s);
    Optional<TasksBoard> findAllByIdAndHasExpiredFalse(Long id);
}
