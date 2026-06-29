package chess_hub.service;

import java.util.ArrayList;
import java.util.Comparator;

import chess_hub.dto.LeaderBoadyEntry;
import chess_hub.entity.Game;
import chess_hub.repository.GameRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class LeaderBoadyService {

    private final GameRepository gameRepository;

    public LeaderBoadyService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<LeaderBoadyEntry> calculate(Long tournamentId) {

        List<Game> games = gameRepository.findByTournamentId(tournamentId);

        Map<Long, LeaderBoadyEntry> table = new HashMap<>();

        for (Game game : games) {

            var white = game.getWhitePlayer();
            var black = game.getBlackPlayer();

            table.putIfAbsent(
                    white.getId(),
                    new LeaderBoadyEntry(white.getId(), white.getFullName())
            );

            table.putIfAbsent(
                    black.getId(),
                    new LeaderBoadyEntry(black.getId(), black.getFullName())
            );

            LeaderBoadyEntry whiteEntry = table.get(white.getId());
            LeaderBoadyEntry blackEntry = table.get(black.getId());

            switch (game.getResult()) {

                case "WHITE_WIN" -> {
                    whiteEntry.addWin();
                    blackEntry.addLoss();
                }

                case "BLACK_WIN" -> {
                    blackEntry.addWin();
                    whiteEntry.addLoss();
                }

                case "DRAW" -> {
                    whiteEntry.addDraw();
                    blackEntry.addDraw();
                }
            }
        }

        List<LeaderBoadyEntry> leaderboard =
                new ArrayList<>(table.values());

        leaderboard.sort(
                Comparator.comparingDouble(
                                LeaderBoadyEntry::getPoints)
                        .reversed()
        );

        return leaderboard;
    }
}
