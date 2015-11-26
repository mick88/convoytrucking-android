package com.mick88.convoytrucking.api.schema.models;

import java.util.Locale;

/**
 * Created by Michal on 03/11/2015.
 */
public abstract class BaseModel {
    Long id;

    @Override
    public int hashCode() {
        if (id != null) return id.hashCode();
        return super.hashCode();
    }

    @Override
    public String toString() {
        final String name = getClass().getSimpleName();
        if (id != null) {
            return String.format(Locale.ENGLISH, "%s %s", name, id);
        } else {
            return name;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseModel baseModel = (BaseModel) o;

        return !(id != null ? !id.equals(baseModel.id) : baseModel.id != null);
    }

    public Long getId() {
        return id;
    }
}
