package chess_hub.controller;

import chess_hub.entity.Member;
import chess_hub.entity.Tournament;
import chess_hub.repository.MemberRepository;
import chess_hub.repository.TournamentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class TournamentRegistrationController {

    private final MemberRepository memberRepository;
    private final TournamentRepository tournamentRepository;

    public TournamentRegistrationController(MemberRepository memberRepository,
                                            TournamentRepository tournamentRepository) {
        this.memberRepository = memberRepository;
        this.tournamentRepository = tournamentRepository;
    }

    // Show registration page
    @GetMapping("/{tournamentId}")
    public String showRegisterPage(@PathVariable Long tournamentId, Model model) {

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow();

        model.addAttribute("tournament", tournament);
        model.addAttribute("members", memberRepository.findAll());

        return "registerTourn";
    }

    // Register member to tournament
    @PostMapping
    public String registerMember(@RequestParam Long memberId,
                                 @RequestParam Long tournamentId) {

        Member member = memberRepository.findById(memberId).orElseThrow();
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow();

        member.getTournaments().add(tournament);

        memberRepository.save(member);

        return "redirect:/tournaments";
    }
}
