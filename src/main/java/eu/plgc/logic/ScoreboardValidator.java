package eu.plgc.logic;

import eu.plgc.domain.Match;
import eu.plgc.domain.Scoreboard;
import eu.plgc.domain.Team;

public class ScoreboardValidator {

    private final int minScore;
    private final int maxScore;

    public ScoreboardValidator(int minScore, int maxScore) {
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public void newMatchValidate(Scoreboard scoreboard, Team homeTeam, Team awayTeam) {
        validateNotNull(homeTeam, "Home team cannot be null");
        validateNotNull(awayTeam, "Away team cannot be null");
        validateTeamMatch(scoreboard, homeTeam);
        validateTeamMatch(scoreboard, awayTeam);
    }

    public void updateMatchScoreValidate(Scoreboard scoreboard, Match match, int homeTeamScore, int awayTeamScore) {
        validateScoreboardHasMatch(scoreboard, match);
        validateScoreInRange(homeTeamScore, "Home");
        validateScoreInRange(awayTeamScore, "Away");
    }

    private void validateScoreInRange(int score, String prefix) {
        if (score < minScore || score > maxScore) {
            throw new ValidationException("%s score value '%d' is not within expected range: [%d , %d]"
                    .formatted(prefix, score, minScore, maxScore));
        }
    }

    private void validateScoreboardHasMatch(Scoreboard scoreboard, Match match) {
        if (!scoreboard.hasMatch(match)) {
            throw new ValidationException("Match '" + match.getId() + "' is not on scoreboard");
        }
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
