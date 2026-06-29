package chess_hub.dto;

public class LeaderBoadyEntry {


        private Long memberId;
        private String playerName;

        private int played;
        private int wins;
        private int draws;
        private int losses;

        private double points;

        public LeaderBoadyEntry(Long memberId, String playerName) {
            this.memberId = memberId;
            this.playerName = playerName;
        }

        public Long getMemberId() {
            return memberId;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getPlayed() {
            return played;
        }

        public int getWins() {
            return wins;
        }

        public int getDraws() {
            return draws;
        }

        public int getLosses() {
            return losses;
        }

        public double getPoints() {
            return points;
        }

        public void addWin() {
            wins++;
            played++;
            points += 1.0;
        }

        public void addDraw() {
            draws++;
            played++;
            points += 0.5;
        }

        public void addLoss() {
            losses++;
            played++;
        }
    }

