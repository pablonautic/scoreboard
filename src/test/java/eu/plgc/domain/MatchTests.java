package eu.plgc.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchTests {

    private final Team TEAM1 = new Team("Poland");
    private final Team TEAM2 = new Team("Mexico");

    @Test
    void calculateTotalScore_forGivenMatch_returnsSum() {

        //arrange
        var match = new Match(Instant.MIN, TEAM1, TEAM2);
        match.setHomeTeamScore(3);
        match.setAwayTeamScore(7);

        //act
        var result = match.calculateTotalScore();

        //assert
        assertEquals(10, result);
    }

    @Test
    void getDisplayString_forGivenMatchWithoutTime_returnsString() {

        //arrange
        var match = new Match(Instant.MIN, TEAM1, TEAM2);
        match.setHomeTeamScore(3);
        match.setAwayTeamScore(7);

        //act
        var result = match.getDisplayString(false);

        //assert
        assertEquals("Poland 3 - Mexico 7", result);
    }

    @Test
    void getDisplayString_forGivenMatchWithTime_returnsString() {

        //arrange
        var now = Instant.now();
        var match = new Match(now, TEAM1, TEAM2);
        match.setHomeTeamScore(3);
        match.setAwayTeamScore(7);

        //act
        var result = match.getDisplayString(true);

        //assert
        assertEquals("Poland 3 - Mexico 7 " + now, result);
    }
}
