package chess_hub.repository;


import chess_hub.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("""
SELECT m
FROM Member m
WHERE LOWER(m.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(m.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(m.phoneNumber) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Member> search(@Param("keyword") String keyword);
    List<Member> findByFullNameContainingIgnoreCase(String fullName);
    
}
