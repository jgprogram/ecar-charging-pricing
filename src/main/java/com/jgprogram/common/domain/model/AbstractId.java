package com.jgprogram.common.domain.model;

import com.jgprogram.common.exception.BusinessLogicException;

import java.util.Objects;

public abstract class AbstractId extends DomainObject implements Identity {

    private String id;

    protected AbstractId(String id) {
        setId(id);
    }

    /**
     * Use it to validate id eg. when you need valid UUID format
     *
     * @param id
     * @throws BusinessLogicException when id is invalid
     */
    abstract protected void validateId(String id) throws BusinessLogicException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractId that = (AbstractId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String id() {
        return id;
    }

    private void setId(String id) {
        assertArgumentNotEmpty(id, "The basic id is required");

        validateId(id);

        this.id = id;
    }
}
