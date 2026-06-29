package chess_hub.controller;

import chess_hub.entity.Tournament;
import chess_hub.repository.TournamentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentRepository repository;

    public TournamentController(TournamentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tournaments", repository.findAll());
        return "tournament-list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("tournament", new Tournament());
        return "tournament-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Tournament tournament) {
        repository.save(tournament);
        return "redirect:/tournaments";
    }
}
