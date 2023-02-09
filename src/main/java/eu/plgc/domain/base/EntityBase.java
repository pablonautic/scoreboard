package eu.plgc.domain.base;

import java.util.UUID;

public abstract class EntityBase {

    private final UUID id;

    // original idea was to a dedicated generator but for the sake of simplicity we generate it here.
    protected EntityBase() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    //other properties like create time etc. could go here
}
