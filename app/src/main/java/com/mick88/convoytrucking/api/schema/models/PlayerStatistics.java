package com.mick88.convoytrucking.api.schema.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Michal on 27/11/2015.
 */
public class PlayerStatistics extends BaseModel {
    int artic;
    int dumper;
    int van;
    int tanker;
    int cement;
    int arrests;
    int jailed;
    int fined;
    int gta;
    int coach;
    int plane;
    int heli;
    int tow;
    int limo;
    int trash;
    int armored;
    int burglar;
    int heist;
    int failed;
    int overloads;
    @SerializedName("truck_loads")
    int truckLoads;
    int fuel;
    int interest;
    int odometer;
    int time;
    String updated;

    public int getArtic() {
        return artic;
    }

    public int getDumper() {
        return dumper;
    }

    public int getVan() {
        return van;
    }

    public int getTanker() {
        return tanker;
    }

    public int getCement() {
        return cement;
    }

    public int getArrests() {
        return arrests;
    }

    public int getJailed() {
        return jailed;
    }

    public int getFined() {
        return fined;
    }

    public int getGta() {
        return gta;
    }

    public int getCoach() {
        return coach;
    }

    public int getPlane() {
        return plane;
    }

    public int getHeli() {
        return heli;
    }

    public int getTow() {
        return tow;
    }

    public int getLimo() {
        return limo;
    }

    public int getTrash() {
        return trash;
    }

    public int getArmored() {
        return armored;
    }

    public int getBurglar() {
        return burglar;
    }

    public int getHeist() {
        return heist;
    }

    public int getFailed() {
        return failed;
    }

    public int getOverloads() {
        return overloads;
    }

    public int getTruckLoads() {
        return truckLoads;
    }

    public int getFuel() {
        return fuel;
    }

    public int getInterest() {
        return interest;
    }

    public int getOdometer() {
        return odometer;
    }

    public int getTime() {
        return time;
    }

    public String getUpdated() {
        return updated;
    }
}
