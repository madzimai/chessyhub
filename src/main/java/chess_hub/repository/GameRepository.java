package chess_hub.repository;

import chess_hub.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByTournamentId(Long tournamentId);
}