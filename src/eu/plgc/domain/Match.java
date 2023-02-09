package eu.plgc.domain;

import eu.plgc.domain.base.EntityBase;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Match extends EntityBase {

    private final ZonedDateTime startTime;

    private final Team homeTeam;

    private int homeTeamScore;

    private final Team awayTeam;

    private int awayTeamScore;

    public Match(UUID id, ZonedDateTime startTime, Team homeTeam, Team awayTeam) {
        super(id);
        this.startTime = startTime;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        //scores = 0 implicitly
    }

    public ZonedDateTime getStartTime() {
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
