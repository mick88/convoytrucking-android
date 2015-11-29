package com.mick88.convoytrucking.api.schema.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Michal on 29/11/2015.
 */
public class Dealer {
    @SerializedName("dealerid")
    int dealerId;
    @SerializedName("offered_models")
    int[] offeredModels;
    @SerializedName("image_path")
    String imagePath;
    @SerializedName("dealername")
    String name;
    float dealerx;
    float dealery;
    float dealerz;
    float spawnx;
    float spawny;
    float spawnz;
    float spawnr;

    @Override
    public String toString() {
        return name;
    }

    public int getDealerId() {
        return dealerId;
    }

    public int[] getOfferedModels() {
        return offeredModels;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public float getDealerx() {
        return dealerx;
    }

    public float getDealery() {
        return dealery;
    }

    public float getDealerz() {
        return dealerz;
    }

    public float getSpawnx() {
        return spawnx;
    }

    public float getSpawny() {
        return spawny;
    }

    public float getSpawnz() {
        return spawnz;
    }

    public float getSpawnr() {
        return spawnr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dealer dealer = (Dealer) o;

        return dealerId == dealer.dealerId;
    }

    @Override
    public int hashCode() {
        return dealerId;
    }
}
