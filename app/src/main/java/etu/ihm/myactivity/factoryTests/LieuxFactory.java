package etu.ihm.myactivity.factoryTests;

import android.graphics.Bitmap;

import java.util.ArrayList;

import etu.ihm.myactivity.restaurants.Commentaire;

public class LieuxFactory extends RestaurationFactory {


    public Lieux build(String name, int type, Bitmap picture, String description,
                       double grade, ArrayList<Commentaire> comments) throws Throwable{
        switch (type){
            case RESTAURANT: return new Restaurants(name,picture,description,grade,comments);
            case BAR: return new Bar(name,picture,description,grade,comments);
            default: throw new Throwable("couldn't create");

        }
    }
}
