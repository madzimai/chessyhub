package chess_hub.controller;

import chess_hub.entity.Member;
import chess_hub.repository.TournamentRepository;
import chess_hub.service.PairingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pairings")
public class PairingController {

    private final TournamentRepository tournamentRepository;
    private final PairingService pairingService;

    public PairingController(TournamentRepository tournamentRepository,
                             PairingService pairingService) {
        this.tournamentRepository = tournamentRepository;
        this.pairingService = pairingService;
    }

    @GetMapping("/{tournamentId}")
    public String generatePairings(@PathVariable Long tournamentId, Model model) {

        List<Member> players =
                tournamentRepository.findById(tournamentId)
                        .orElseThrow()
                        .getMembers();

        model.addAttribute("pairs", pairingService.generatePairings(players));

        return "pairings";
    }

}
