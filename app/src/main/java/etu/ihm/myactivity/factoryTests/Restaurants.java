package etu.ihm.myactivity.factoryTests;

import android.graphics.Bitmap;

import java.util.ArrayList;

import etu.ihm.myactivity.restaurants.Commentaire;

public class Restaurants extends Lieux{

    public Restaurants(String name, Bitmap picture, String description, double grade, ArrayList<Commentaire> comments){
        super(name,picture,description,grade,comments);
        System.out.println("Restaurant créé");
    }
}
