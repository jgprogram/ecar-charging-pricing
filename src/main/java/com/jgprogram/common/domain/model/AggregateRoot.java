package com.jgprogram.common.domain.model;

import java.util.Objects;

public abstract class AggregateRoot<ID extends Identity> extends Entity {

    private ID aggregateId;

    protected AggregateRoot(ID aggregateId) {
        setAggregateId(aggregateId);
    }

    public ID aggregateId() {
        return aggregateId;
    }

    private void setAggregateId(ID aggregateId) {
        assertArgumentNotNull(aggregateId, "Aggregate id is required.");

        this.aggregateId = aggregateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AggregateRoot<?> that = (AggregateRoot<?>) o;
        return aggregateId.equals(that.aggregateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId);
    }
}
