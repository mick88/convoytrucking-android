package com.mick88.convoytrucking.api.schema.models;

/**
 * Simple player model, contains only player's name and id
 * Created by Michal on 26/11/2015.
 */
public class SimplePlayer extends BaseModel {
    String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
