package chess_hub.service;

import chess_hub.entity.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PairingService {

    public List<String> generatePairings(List<Member> players) {

        Collections.shuffle(players);

        List<String> pairs = new ArrayList<>();

        for (int i = 0; i < players.size() - 1; i += 2) {

            String match =
                    players.get(i).getFullName()
                            + " vs "
                            + players.get(i + 1).getFullName();

            pairs.add(match);
        }

        return pairs;
    }
}