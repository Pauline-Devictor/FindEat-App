package etu.ihm.myactivity.factoryTests;

import android.graphics.Bitmap;

import java.util.ArrayList;

import etu.ihm.myactivity.restaurants.Commentaire;

public abstract class LieuxFactory {
    public static final int RESTAURANT=1;
    public static final int BAR=2;

    abstract public Lieux build(String name, int type, Bitmap picture, String description,
                                   double grade, ArrayList<Commentaire> comments,double longitude,double latitude) throws Throwable;
}
