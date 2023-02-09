package eu.plgc.domain;

import eu.plgc.domain.base.EntityBase;

public class Team extends EntityBase {

    private final String name;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
