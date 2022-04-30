package etu.ihm.myactivity.factoryTests;

import android.graphics.Bitmap;

import java.util.ArrayList;

import etu.ihm.myactivity.restaurants.Commentaire;

public class Restaurants extends Lieux{

    public Restaurants(String name, String placeID, boolean openNow, Bitmap picture, double rate, double longitude, double latitude, Integer priceLevel){
        super(name,placeID, openNow, picture, rate, longitude, latitude, priceLevel);
        System.out.println("Restaurant créé");
    }
}
