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
        //arrange

        //act
        var exception = assertThrows(ValidationException.class, () -> scoreboardService.newMatch(null, TEAM3));

        //assert
        assertEquals("Home team cannot be null", exception.getMessage());
    }

    @Test
    void newMatch_forInvalidAwayTeam_throwsValidationException() {
        //arrange

        //act
        var exception = assertThrows(ValidationException.class, () -> scoreboardService.newMatch(TEAM3, null));

        //assert
        assertEquals("Away team cannot be null", exception.getMessage());
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

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10000})
    void updateMatchScore_forValidMatchScore_setNewScores(int score) {

        //arrange
        var match = scoreboardService.newMatch(TEAM1, TEAM2);
        //double check avoiding fake positives
        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getAwayTeamScore());

        //act
        var updatedMatch = scoreboardService.updateMatchScore(match, score, score);

        //assert
        assertNotNull(updatedMatch);
        assertEquals(match, updatedMatch);
        assertEquals(score, updatedMatch.getHomeTeamScore());
        assertEquals(score, updatedMatch.getAwayTeamScore());
    }

    @Test
    void updateMatchScore_forMatchNotOnScoreboard_throwsValidationException() {

        //arrange
        var match = new Match(SOME_TIME, TEAM1, TEAM2); //some random match

        //act
        var exception = assertThrows(ValidationException.class, () ->
                scoreboardService.updateMatchScore(match, HOME_SCORE, AWAY_SCORE));

        //assert
        assertEquals("Match '" + match.getId() + "' is not on scoreboard", exception.getMessage());
    }

    @Test
    void updateMatchScore_forNullMatch_throwsValidationException() {

        //arrange

        //act
        var exception = assertThrows(ValidationException.class, () ->
                scoreboardService.updateMatchScore(null, HOME_SCORE, AWAY_SCORE));

        //assert
        assertEquals("Match cannot be null", exception.getMessage());
    }

    //below two tests could be combined further using MethodSource

    @ParameterizedTest
    @ValueSource(ints = {-1, 10001})
    void updateMatchScore_whenHomeScoreIsOutOfRange_throwsValidationException(int score) {

        //arrange
        Match match = scoreboardService.newMatch(TEAM1, TEAM2);

        //act
        var exception = assertThrows(ValidationException.class, () ->
                scoreboardService.updateMatchScore(match, score, AWAY_SCORE));

        //assert
        assertEquals("Home score value '" + score + "' is not within expected range: [0,10000]",
                exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 10001})
    void updateMatchScore_whenAwayScoreIsOutOfRange_throwsValidationException(int score) {

        //arrange
        Match match = scoreboardService.newMatch(TEAM1, TEAM2);

        //act
        var exception = assertThrows(ValidationException.class, () ->
                scoreboardService.updateMatchScore(match, HOME_SCORE, score));

        //assert
        assertEquals("Away score value '" + score + "' is not within expected range: [0,10000]",
                exception.getMessage());
    }

    @Test
    void finishMatch_ForValidMatch_removesMatchFromScoreboard() {

        //arrange
        Match match = scoreboardService.newMatch(TEAM1, TEAM2);

        //act
        Match removedMatch = scoreboardService.finishMatch(match);

        //assert
        assertNotNull(removedMatch);
        assertEquals(match, removedMatch);
        assertFalse(scoreboardService.hasMatch(match));
    }

    @Test
    void finishMatch_forMatchNotOnScoreboard_throwsValidationException() {

        //arrange
        var match = new Match(SOME_TIME, TEAM1, TEAM2); //some random match

        //act
        var exception = assertThrows(ValidationException.class, () ->
                scoreboardService.finishMatch(match));

        //assert
        assertEquals("Match '" + match.getId() + "' is not on scoreboard", exception.getMessage());
    }

    @Test
    void finishMatch_forNullMatch_throwsValidationException() {

        //arrange

        //act
        var exception = assertThrows(ValidationException.class, () ->
                scoreboardService.finishMatch(null));

        //assert
        assertEquals("Match cannot be null", exception.getMessage());
    }
}
