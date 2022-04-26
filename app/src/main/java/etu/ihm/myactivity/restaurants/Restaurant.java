package etu.ihm.myactivity.restaurants;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class Restaurant implements Serializable {
    private Bitmap picture;
    private String name;
    private String description;
    private double grade;
    private ArrayList<Commentaire> comments;


    public Restaurant(Bitmap picture, String name, String description, double grade, ArrayList<Commentaire> comments) {
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

    public double getGrade() {
        return grade;
    }

}
