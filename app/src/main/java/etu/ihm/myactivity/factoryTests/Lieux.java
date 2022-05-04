package etu.ihm.myactivity.factoryTests;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

import etu.ihm.myactivity.restaurants.Commentaire;

public abstract class Lieux implements Serializable {
    protected String name;
    protected String placeID;
    protected boolean openNow;
    protected String picture;
    protected double rate;
    protected double longitude;
    protected double latitude;
    protected Integer priceLevel;



    public Lieux(String name, String placeID, boolean openNow, String picture, double rate, double longitude, double latitude, Integer priceLevel) {
        this.name = name;
        this.placeID = placeID;
        this.openNow = openNow;
        this.picture = picture;
        this.rate = rate;
        this.longitude = longitude;
        this.latitude = latitude;
        this.priceLevel = priceLevel;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Integer getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(Integer priceLevel) {
        this.priceLevel = priceLevel;
    }
}
