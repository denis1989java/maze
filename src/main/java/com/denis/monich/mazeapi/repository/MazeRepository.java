package com.denis.monich.mazeapi.repository;

import com.denis.monich.mazeapi.model.Maze;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MazeRepository extends JpaRepository<Maze, String> {
}
