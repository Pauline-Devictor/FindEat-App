package etu.ihm.myactivity.factoryTests;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

import etu.ihm.myactivity.restaurants.Commentaire;

public class Restaurants extends Lieux{

    public Restaurants(String name, String placeID, boolean openNow, String picture, double rate, double longitude, double latitude, Integer priceLevel, double distance){
        super(name,placeID, openNow, picture, rate, longitude, latitude, priceLevel, distance);
        System.out.println("Restaurant créé");
    }
}
