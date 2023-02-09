package logic;

import eu.plgc.domain.Match;
import eu.plgc.domain.Team;
import eu.plgc.logic.ScoreboardService;
import eu.plgc.logic.ValidationException;
import objectMothers.TeamObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreboardServiceTests {

    private final Team team1 = TeamObjectMother.create();

    private final Team team2 = TeamObjectMother.create();

    private final Team team3 = TeamObjectMother.create();

    private ScoreboardService scoreboardService;

    @BeforeEach
    void beforeEach() {
        scoreboardService = new ScoreboardService();
    }

    @Test
    void newMatch_forValidTeams_startsMatchAndReturnsInstanceWith0to0Score() {

        //arrange

        //act
        Match match = scoreboardService.newMatch(team1, team2);

        //assert
        assertNotNull(match);
        assertEquals(team1, match.getHomeTeam());
        assertEquals(team2, match.getAwayTeam());
        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getAwayTeamScore());
        //TODO assertEquals(match.getStartTime())

    }

    @Test
    void newMatch_forHomeTeamAlreadyPlaying_throwsValidationException() {

        //arrange
        scoreboardService.newMatch(team1, team2);

        //act
        var exception = assertThrows(ValidationException.class, () -> scoreboardService.newMatch(team1, team3));

        //assert
        assertEquals("Team '" + team1.getName() + "' is already playing a match", exception.getMessage());
    }

    @Test
    void newMatch_forAwayTeamAlreadyPlaying_throwsValidationException() {

        //arrange
        scoreboardService.newMatch(team1, team2);

        //act
        var exception = assertThrows(ValidationException.class, () -> scoreboardService.newMatch(team3, team1));

        //assert
        assertEquals("Team '" + team1.getName() + "' is already playing a match", exception.getMessage());
    }
}
