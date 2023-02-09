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
    void getDisplayString_forGivenMatch_returnsString() {

        //arrange
        var match = new Match(Instant.MIN, TEAM1, TEAM2);
        match.setHomeTeamScore(3);
        match.setAwayTeamScore(7);

        //act
        var result = match.getDisplayString();

        //assert
        assertEquals("Poland 3 - Mexico 7", result);
    }
}
