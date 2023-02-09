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

    public int calculateTotalScore() {
        return homeTeamScore + awayTeamScore;
    }

    public String getDisplayString(boolean includeStartTime) {
        return "%s %d - %s %d%s"
                .formatted(homeTeam.getName(),
                        homeTeamScore,
                        awayTeam.getName(),
                        awayTeamScore,
                        includeStartTime ? " " + startTime : "");
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Match{");
        sb.append("startTime=").append(startTime);
        sb.append(", homeTeam=").append(homeTeam);
        sb.append(", homeTeamScore=").append(homeTeamScore);
        sb.append(", awayTeam=").append(awayTeam);
        sb.append(", awayTeamScore=").append(awayTeamScore);
        sb.append('}');
        return sb.toString();
    }
}
