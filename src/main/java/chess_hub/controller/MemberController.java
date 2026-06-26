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

        if (member.getId() != null) {
            Member existing = repository.findById(member.getId())
                    .orElseThrow();

            existing.setFullName(member.getFullName());
            existing.setPhoneNumber(member.getPhoneNumber());
            existing.setEmail(member.getEmail());
            existing.setRating(member.getRating());

            repository.save(existing);
        } else {
            repository.save(member);
        }

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
    @GetMapping("/edit/{id}")
    public String editMember(@PathVariable Long id, Model model) {

        Member member = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member id: " + id));

        model.addAttribute("member", member);

        return "memberform";
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id) {

        repository.deleteById(id);

        return "redirect:/members";
    }
}
