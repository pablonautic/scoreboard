package eu.plgc.logic;

import eu.plgc.domain.Match;
import eu.plgc.domain.Team;
import eu.plgc.objectMothers.TeamObjectMother;
import eu.plgc.utils.MutableClock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreboardServiceTests {


    private static final Instant SOME_TIME = Instant.parse("2023-02-09T10:15:30.00Z");

    private final Team TEAM1 = TeamObjectMother.create();
    private final Team TEAM2 = TeamObjectMother.create();
    private final Team TEAM3 = TeamObjectMother.create();

    private static final int HOME_SCORE = 1;
    private static final int AWAY_SCORE = 2;

    private ScoreboardService scoreboardService;
    private MutableClock clock;

    @BeforeEach
    void beforeEach() {
        clock = new MutableClock();
        scoreboardService = new ScoreboardService(clock);
    }

    @Test
    void newMatch_forValidTeams_startsMatchAndReturnsInstanceWith0to0Score() {

        //arrange
        clock.setInstant(SOME_TIME);

        //act
        Match match = scoreboardService.newMatch(TEAM1, TEAM2);

        //assert
        assertNotNull(match);
        assertTrue(scoreboardService.hasMatch(match));

        assertEquals(TEAM1, match.getHomeTeam());
        assertEquals(TEAM2, match.getAwayTeam());
        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getAwayTeamScore());
        assertEquals(SOME_TIME, match.getStartTime());
    }

    @Test
    void newMatch_forInvalidHomeTeam_throwsValidationException() {
        // TODO
    }

    @Test
    void newMatch_forInvalidAwayTeam_throwsValidationException() {
        // TODO
    }

    @Test
    void newMatch_forHomeTeamAlreadyPlaying_throwsValidationException() {

        //arrange
        scoreboardService.newMatch(TEAM1, TEAM2);

        //act
        var exception = assertThrows(ValidationException.class, () -> scoreboardService.newMatch(TEAM1, TEAM3));

        //assert
        assertEquals("Team '" + TEAM1.getName() + "' is already playing a match", exception.getMessage());
    }

    @Test
    void newMatch_forAwayTeamAlreadyPlaying_throwsValidationException() {

        //arrange
        scoreboardService.newMatch(TEAM1, TEAM2);

        //act
        var exception = assertThrows(ValidationException.class, () -> scoreboardService.newMatch(TEAM3, TEAM1));

        //assert
        assertEquals("Team '" + TEAM1.getName() + "' is already playing a match", exception.getMessage());
    }

    @Test
    void updateScore_forValidMatchAndScores_setNewScores() {

        //arrange
        var match = scoreboardService.newMatch(TEAM1, TEAM2);
        //double check avoiding fake positives
        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getAwayTeamScore());

        //act
        var updatedMatch = scoreboardService.updateScore(match, HOME_SCORE, AWAY_SCORE);

        //assert
        assertNotNull(updatedMatch);
        assertEquals(match, updatedMatch);
        assertEquals(HOME_SCORE, updatedMatch.getHomeTeamScore());
        assertEquals(AWAY_SCORE, updatedMatch.getAwayTeamScore());
    }

    @Test
    void updateScore_forMatchNotOnScoreboard_throwsValidationException() {

        //arrange
        var match = new Match(SOME_TIME, TEAM1, TEAM2); //some random match

        //act
        var exception = assertThrows(ValidationException.class, () ->
                scoreboardService.updateScore(match, HOME_SCORE, AWAY_SCORE));

        //assert
        assertEquals("Match '" + match.getId() + "' is not on scoreboard", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 10001})
    void updateScore_whenHomeScoreIsOutOfRange_throwsValidationException(int score) {

        //arrange
        Match match = scoreboardService.newMatch(TEAM1, TEAM2);

        //act
        var exception = assertThrows(ValidationException.class, () ->
                scoreboardService.updateScore(match, HOME_SCORE, AWAY_SCORE));

        //assert
        assertEquals("Home score value '" + score + "' is not within expected range: [0,10000]",
                exception.getMessage());
    }

    //TODO away score
}
