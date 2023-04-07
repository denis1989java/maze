package com.denis.monich.mazeapi.repository;

import com.denis.monich.mazeapi.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, String> {
}
