package eu.plgc.domain;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private final List<Match> matches = new ArrayList<>();

    public List<Match> getMatches() {
        return matches;
    }
}
