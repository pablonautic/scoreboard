package eu.plgc.ui;

import eu.plgc.domain.Match;
import eu.plgc.domain.Team;
import eu.plgc.logic.ScoreboardService;

import java.time.Clock;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // in a real app this would be handled by a DI container like Spring or Guice
        var clock = Clock.systemUTC();
        var scoreboardService = new ScoreboardService(clock);
        var scoreboardWriter = new ScoreboardWriter(scoreboardService);

        // teams would come from some DB
        var mexico = new Team("Mexico");
        var canada = new Team("Canada");
        Match mexCan = scoreboardService.newMatch(mexico, canada);
        //we add some thread sleeps because Instant doesn't have the necessary resolution
        //and some times overlap
        Thread.sleep(100);

        var spain = new Team("Spain");
        var brazil = new Team("Brazil");
        Match spaBra = scoreboardService.newMatch(spain, brazil);
        Thread.sleep(100);

        var germany = new Team("Germany");
        var france = new Team("France");
        Match gerFra = scoreboardService.newMatch(germany, france);
        Thread.sleep(100);

        var uruguay = new Team("Uruguay");
        var italy = new Team("Italy");
        Match uruIta = scoreboardService.newMatch(uruguay, italy);
        Thread.sleep(100);

        var argentina = new Team("Argentina");
        var australia = new Team("Australia");
        Match argAus = scoreboardService.newMatch(argentina, australia);
        Thread.sleep(100);

        System.out.println(scoreboardWriter.write(true));

        /*
        For example, if following matches are started in the specified order and their scores
        respectively updated:
        a. Mexico 0 - Canada 5
        b. Spain 10 - Brazil 2
        c. Germany 2 - France 2
        d. Uruguay 6 - Italy 6
        e. Argentina 3 - Australia 1

        The summary should be as follows:
        1. Uruguay 6 - Italy 6
        2. Spain 10 - Brazil 2
        3. Mexico 0 - Canada 5
        4. Argentina 3 - Australia 1
        5. Germany 2 - France 2
         */

        scoreboardService.updateMatchScore(mexCan, 0 ,5);
        scoreboardService.updateMatchScore(spaBra, 10 ,2);
        scoreboardService.updateMatchScore(gerFra, 2 ,2);
        scoreboardService.updateMatchScore(uruIta, 6 ,6);
        scoreboardService.updateMatchScore(argAus, 3 ,1);

        System.out.println(scoreboardWriter.write(false));
    }
}
