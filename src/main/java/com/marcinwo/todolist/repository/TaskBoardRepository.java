package com.marcinwo.todolist.repository;

import com.marcinwo.todolist.entity.TasksBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskBoardRepository extends JpaRepository<TasksBoard, Long> {
}
