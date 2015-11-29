package com.mick88.convoytrucking.api.schema.models;

import com.google.gson.annotations.SerializedName;
import com.mick88.convoytrucking.api.ApiConstants;

/**
 * Created by Michal on 29/11/2015.
 */
public class Vehicle {
    public static final String IMAGE_PATH = ApiConstants.URL_STATICFILES + "vehicles/";
    public static final String
            VEHICLE_IMAGE_SMALL = "small/",
            VEHICLE_IMAGE_MEDIUM = "medium/";

    @SerializedName("modelid")
    int modelId;
    @SerializedName("image_file_name")
    String imageFileName;
    String name;
    int price;
    String colors;
    @SerializedName("top_speed")
    int topSpeed;
    int flags;

    public int getModelId() {
        return modelId;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getColors() {
        return colors;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public int getFlags() {
        return flags;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        return modelId == vehicle.modelId;
    }

    @Override
    public int hashCode() {
        return modelId;
    }

    /**
     * Gets full path to the vehicle image
     * @param size one of te following sizes: small, medium
     * @return full url to the image
     */
    public String getImageUrl(String size) {
        return IMAGE_PATH + size + getImageFileName();
    }
}
