package eu.plgc.objectMothers;

import com.flextrade.jfixture.JFixture;
import eu.plgc.domain.Team;

import java.util.UUID;

public class TeamObjectMother {

    private static final JFixture fixture = new JFixture();

    public static Team create() {
        return fixture.create(Team.class);
    }

}
