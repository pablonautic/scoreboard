package eu.plgc.domain;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private final List<Match> matches = new ArrayList<>();

    public List<Match> getMatchesImmutable() {
        return List.of(matches.toArray(new Match[0]));
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public void removeMatch(Match match) {
        matches.remove(match);
    }

    public boolean hasMatch(Match match) {
        return matches.contains(match);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Scoreboard{");
        sb.append("matches=").append(matches);
        sb.append('}');
        return sb.toString();
    }
}
