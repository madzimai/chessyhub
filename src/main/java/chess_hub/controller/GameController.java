package chess_hub.controller;

import chess_hub.entity.Game;
import chess_hub.entity.Member;
import chess_hub.entity.Tournament;
import chess_hub.repository.GameRepository;
import chess_hub.repository.MemberRepository;
import chess_hub.repository.TournamentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameRepository gameRepository;
    private final MemberRepository memberRepository;
    private final TournamentRepository tournamentRepository;

    public GameController(GameRepository gameRepository,
                          MemberRepository memberRepository,
                          TournamentRepository tournamentRepository) {
        this.gameRepository = gameRepository;
        this.memberRepository = memberRepository;
        this.tournamentRepository = tournamentRepository;
    }

    @GetMapping("/new/{tournamentId}")
    public String createGame(@PathVariable Long tournamentId, Model model) {

        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow();

        model.addAttribute("tournament", tournament);
        model.addAttribute("members", memberRepository.findAll());
        model.addAttribute("game", new Game());

        return "gameform";
    }

    @PostMapping("/save")
    public String saveGame(@RequestParam Long tournamentId,
                           @RequestParam Long whiteId,
                           @RequestParam Long blackId,
                           @RequestParam String result,
                           @RequestParam Integer roundNumber) {

        Game game = new Game();

        game.setTournament(tournamentRepository.findById(tournamentId).orElseThrow());
        game.setWhitePlayer(memberRepository.findById(whiteId).orElseThrow());
        game.setBlackPlayer(memberRepository.findById(blackId).orElseThrow());
        game.setResult(result);
        game.setRoundNumber(roundNumber);

        gameRepository.save(game);

        return "redirect:/tournaments";
    }

    @GetMapping("/tournament/{id}")
    public String viewGames(@PathVariable Long id, Model model) {

        model.addAttribute("games", gameRepository.findByTournamentId(id));

        return "gamelist";
    }
}
