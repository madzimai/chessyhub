package chess_hub.entity;

import jakarta.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Tournament tournament;

    @ManyToOne
    private Member whitePlayer;

    @ManyToOne
    private Member blackPlayer;

    private String result;
    // "WHITE_WIN", "BLACK_WIN", "DRAW"

    private Integer roundNumber;

    public Game() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Member getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(Member whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public Member getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(Member blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }
}