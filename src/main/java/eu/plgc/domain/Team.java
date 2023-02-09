package eu.plgc.domain;

import eu.plgc.domain.base.EntityBase;

import java.util.UUID;

public class Team extends EntityBase {

    private final String name;

    public Team(UUID id, String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
