package com.mick88.convoytrucking.api.schema.models;

import com.google.gson.annotations.SerializedName;
import com.mick88.convoytrucking.api.ApiConstants;

import java.util.Locale;

/**
 * Created by Michal on 25/11/2015.
 */
public class House extends BaseModel {
    public static final String IMAGES_URL = ApiConstants.URL_MEDIA + "houses/";
    public static final String IMAGE_SIZE_LARGE = "large";
    public static final String IMAGE_SIZE_MEDIUM = "medium";
    public static final String IMAGE_SIZE_SMALL = "small";

    @SerializedName("image_name")
    String imageName;
    float x, y, z, r;
    int interior;
    float range;
    int price;
    String location;
    int parking;
    @SerializedName("owner_id")
    SimplePlayer owner;

    @Override
    public String toString() {
        return getAddress();
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

    /**
     * Gets absolute url of the house image in selected size.
     * If size value is not one of the valid values, the method will return url successfully, but the url will not be valid.
     * @param size one of the following constants: IMAGE_SIZE_LARGE, IMAGE_SIZE_MEDIUM, IMAGE_SIZE_SMALL
     */
    public String getImageUrl(String size) {
        return String.format(Locale.ENGLISH, "%s%s/%s", IMAGES_URL, size, getImageName());
    }

    public String getAddress() {
        return String.format(Locale.ENGLISH, "%d %s", id, location);
    }

    public SimplePlayer getOwner() {
        return owner;
    }

    public boolean isForSale() {
        return owner == null;
    }
}
