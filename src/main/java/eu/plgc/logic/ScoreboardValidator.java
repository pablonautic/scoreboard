package eu.plgc.logic;

import eu.plgc.domain.Scoreboard;
import eu.plgc.domain.Team;

public class ScoreboardValidator {

    public void newMatchValidate(Scoreboard scoreboard, Team homeTeam, Team awayTeam) {
        validateNotNull(homeTeam, "Home team cannot be null");
        validateNotNull(awayTeam, "Away team cannot be null");
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

    private void validateNotNull(Object o, String message) {
        if (o == null) {
            throw new ValidationException(message);
        }
    }
}
