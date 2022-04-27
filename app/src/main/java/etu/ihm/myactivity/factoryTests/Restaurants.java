package etu.ihm.myactivity.factoryTests;

import android.graphics.Bitmap;

import java.util.ArrayList;

import etu.ihm.myactivity.restaurants.Commentaire;

public class Restaurants extends Lieux{

    public Restaurants(String name, Bitmap picture, String description, double grade, ArrayList<Commentaire> comments,double longitude,double latitude){
        super(name,picture,description,grade,comments,longitude,latitude);
        System.out.println("Restaurant créé");
    }
}
