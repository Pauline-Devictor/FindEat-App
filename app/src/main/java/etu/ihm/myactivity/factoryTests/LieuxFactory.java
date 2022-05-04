package etu.ihm.myactivity.factoryTests;

import android.graphics.Bitmap;

import java.util.ArrayList;

import etu.ihm.myactivity.restaurants.Commentaire;

public abstract class LieuxFactory {
    public static final int RESTAURANT=1;
    public static final int BAR=2;

    abstract public Lieux build(int type,String name, String placeID, boolean openNow, String picture, double rate, double longitude, double latitude, Integer priceLevel) throws Throwable;
}
