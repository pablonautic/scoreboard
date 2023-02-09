package eu.plgc.logic;

import eu.plgc.domain.Match;
import eu.plgc.domain.Team;

import java.time.Clock;
import java.util.List;

public class ScoreboardService {

    private Clock clock;

    public ScoreboardService(Clock clock) {
        this.clock = clock;
    }

    /**
     * 1. Start a new game, assuming initial score 0 – 0 and adding it the scoreboard
     *
     * @param homeTeam
     * @param awayTeam
     * @return new match instance
     */
    public Match newMatch(Team homeTeam, Team awayTeam) {
        //TODO validation
        return new Match(clock.instant(), homeTeam, awayTeam);
    }

    /**
     * 2. Update score. This should receive a pair of absolute scores: home team score and away
     * team score.
     *
     * @param match
     * @param homeTeamScore
     * @param awayTeamScore
     * @return same match instance with updated scores
     */
    public Match updateScore(Match match, int homeTeamScore, int awayTeamScore) {
        //TODO
        return null;
    }

    /**
     * 3. Finish game currently in progress. This removes a match from the scoreboard.
     *
     * @param match
     * @return finished match instance
     */
    public Match finishMatch(Match match) {
        //TODO
        return null;
    }

    /**
     * 4. Get a summary of games in progress ordered by their total score. The games with the same
     * total score will be returned ordered by the most recently started match in the scoreboard.
     *
     * @return
     */
    public List<Match> getSummary() {
        //TODO
        return null;
    }
}
