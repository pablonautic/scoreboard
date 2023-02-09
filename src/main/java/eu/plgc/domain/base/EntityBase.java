package eu.plgc.domain.base;

import java.util.UUID;

public abstract class EntityBase {

    private final UUID id;

    protected EntityBase(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    //other properties like create time etc. could go here
}
