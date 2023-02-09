package eu.plgc.ui;

import eu.plgc.logic.ScoreboardService;

public class ScoreboardWriter {

    private final ScoreboardService scoreboardService;

    public ScoreboardWriter(ScoreboardService scoreboardService) {
        this.scoreboardService = scoreboardService;
    }

    public String write(boolean includeStartTime) {
        var sb = new StringBuilder();
        scoreboardService.getSummary()
                .forEach(match ->
                        sb.append(match.getDisplayString(includeStartTime)).append("\n"));
        return sb.toString();
    }

}
