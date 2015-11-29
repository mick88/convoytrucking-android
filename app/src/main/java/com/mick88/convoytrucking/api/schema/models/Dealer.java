package com.mick88.convoytrucking.api.schema.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.mick88.convoytrucking.api.ApiConstants;

/**
 * Created by Michal on 29/11/2015.
 */
public class Dealer implements Parcelable {
    public static final String IMAGES_URL = ApiConstants.URL_STATICFILES;
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

    public String getImageUrl() {
        return IMAGES_URL + getImagePath();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.dealerId);
        dest.writeIntArray(this.offeredModels);
        dest.writeString(this.imagePath);
        dest.writeString(this.name);
        dest.writeFloat(this.dealerx);
        dest.writeFloat(this.dealery);
        dest.writeFloat(this.dealerz);
        dest.writeFloat(this.spawnx);
        dest.writeFloat(this.spawny);
        dest.writeFloat(this.spawnz);
        dest.writeFloat(this.spawnr);
    }

    public Dealer() {
    }

    protected Dealer(Parcel in) {
        this.dealerId = in.readInt();
        this.offeredModels = in.createIntArray();
        this.imagePath = in.readString();
        this.name = in.readString();
        this.dealerx = in.readFloat();
        this.dealery = in.readFloat();
        this.dealerz = in.readFloat();
        this.spawnx = in.readFloat();
        this.spawny = in.readFloat();
        this.spawnz = in.readFloat();
        this.spawnr = in.readFloat();
    }

    public static final Parcelable.Creator<Dealer> CREATOR = new Parcelable.Creator<Dealer>() {
        public Dealer createFromParcel(Parcel source) {
            return new Dealer(source);
        }

        public Dealer[] newArray(int size) {
            return new Dealer[size];
        }
    };
}
