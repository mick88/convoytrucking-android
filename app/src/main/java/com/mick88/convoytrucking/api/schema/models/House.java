package com.mick88.convoytrucking.api.schema.models;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

/**
 * Created by Michal on 25/11/2015.
 */
public class House extends BaseModel {
    @SerializedName("image_name")
    String imageName;
    float x, y, z, r;
    int interior;
    float range;
    int price;
    String location;
    int parking;

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%d %s", id, location);
    }

    public String getImageName() {
        return imageName;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getR() {
        return r;
    }

    public int getInterior() {
        return interior;
    }

    public float getRange() {
        return range;
    }

    public int getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public int getParking() {
        return parking;
    }
}
