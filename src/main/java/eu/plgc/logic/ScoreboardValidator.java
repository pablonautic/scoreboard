package eu.plgc.logic;

import eu.plgc.domain.Scoreboard;
import eu.plgc.domain.Team;

public class ScoreboardValidator {

    public void newMatchValidate(Scoreboard scoreboard, Team homeTeam, Team awayTeam) {
        validateTeamMatch(scoreboard, homeTeam);
        validateTeamMatch(scoreboard, awayTeam);
    }

    private void validateTeamMatch(Scoreboard scoreboard, Team team) {
        if (scoreboardHasTeam(scoreboard, team)) {
            throw new ValidationException("Team '%s' is already playing a match".formatted(team.getName()));
        }
    }

    private boolean scoreboardHasTeam(Scoreboard scoreboard, Team team) {
        return scoreboard.getMatchesImmutable().stream().anyMatch(m -> m.getHomeTeam().equals(team));
    }
}
