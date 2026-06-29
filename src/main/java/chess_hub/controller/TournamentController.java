package chess_hub.controller;

import chess_hub.entity.Member;
import chess_hub.entity.Tournament;
import chess_hub.repository.MemberRepository;
import chess_hub.repository.TournamentRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentRepository repository;
    private final MemberRepository memberRepository;


    public TournamentController(TournamentRepository repository , MemberRepository memberRepository) {
        this.repository = repository;
        this.memberRepository = memberRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tournaments", repository.findAll());
        return "tournamentlist";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("tournament", new Tournament());
        return "tournamentform";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Tournament tournament) {
        repository.save(tournament);
        return "redirect:/tournaments";
    }

    // Register member to tournament
    @PostMapping
    public String registerMember(@RequestParam Long memberId,
                                 @RequestParam Long tournamentId) {

        Member member = memberRepository.findById(memberId).orElseThrow();
        Tournament tournament = repository.findById(tournamentId).orElseThrow();

        member.getTournaments().add(tournament);

        memberRepository.save(member);

        return "redirect:/tournaments";
    }
}
