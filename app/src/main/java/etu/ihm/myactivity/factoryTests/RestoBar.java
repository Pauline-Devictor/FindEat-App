package etu.ihm.myactivity.factoryTests;

import android.graphics.Bitmap;

import java.util.ArrayList;

import etu.ihm.myactivity.restaurants.Commentaire;


public class RestoBar extends Lieux{

    //Happy Hour

    private int debutHP;
    private int finHP;

    //RANDOM HAPPYHOUR

    public RestoBar(String name, String placeID, boolean openNow, String picture, double rate, double longitude, double latitude, Integer priceLevel, double distance, int debut, int fin){
        super(name,placeID, openNow, picture, rate, longitude, latitude, priceLevel, distance);
        this.debutHP = debut;
        this.finHP = fin;

        System.out.println("Bar créé");
    }
}
