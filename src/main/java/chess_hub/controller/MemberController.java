package chess_hub.controller;

import chess_hub.entity.Member;
import chess_hub.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository repository;

    public MemberController(MemberRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/register")
    public String showForm(Model model) {

        model.addAttribute("member", new Member());

        return "memberform";
    }

    @PostMapping("/register")
    public String saveMember(@ModelAttribute Member member) {

        repository.save(member);

        return "redirect:/members";
    }

    @GetMapping
    public String listMembers(Model model) {

        model.addAttribute(
                "members",
                repository.findAll()
        );

        return "memberlist";
    }
}
