package eu.plgc.domain.base;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityBase that = (EntityBase) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
