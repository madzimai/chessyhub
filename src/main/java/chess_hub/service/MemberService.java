package chess_hub.service;
import chess_hub.entity.Member;
import chess_hub.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Member save(Member member) {
        return repository.save(member);
    }

    public List<Member> findAll() {
        return repository.findAll();
    }
}
