package eu.plgc.domain;

import eu.plgc.domain.base.EntityBase;

import java.time.Instant;

public class Match extends EntityBase {

    private final Instant startTime;

    private final Team homeTeam;

    private int homeTeamScore;

    private final Team awayTeam;

    private int awayTeamScore;

    public Match(Instant startTime, Team homeTeam, Team awayTeam) {
        this.startTime = startTime;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        //scores = 0 implicitly
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }
}
