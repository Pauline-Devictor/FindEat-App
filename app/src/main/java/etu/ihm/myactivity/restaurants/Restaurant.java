package etu.ihm.myactivity.restaurants;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Restaurant {
    private Bitmap picture;
    private String name;
    private String description;
    private float grade;
    private ArrayList<Commentaire> comments;


    public Restaurant(Bitmap picture, String name, String description, float grade, ArrayList<Commentaire> comments) {
        this.picture = picture;
        this.name = name;
        this.description = description;
        this.grade = grade;
        this.comments = comments;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getGrade() {
        return grade;
    }

}
