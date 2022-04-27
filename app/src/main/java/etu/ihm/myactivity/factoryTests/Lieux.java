package etu.ihm.myactivity.factoryTests;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

import etu.ihm.myactivity.restaurants.Commentaire;

public abstract class Lieux implements Serializable {
    protected String name;
    protected Bitmap picture;
    protected String description;
    protected double grade;
    protected ArrayList<Commentaire> comments;
    protected double longitude;
    protected double latitude;

    public Lieux(String name,Bitmap picture,String description,double grade,ArrayList<Commentaire> comments,double longitude,double latitude){
        this.name = name;
        this.picture =picture;
        this.description = description;
        this.grade = grade;
        this.comments = comments;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public double getGrade() {
        return grade;
    }

    public ArrayList<Commentaire> getComments() {
        return comments;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
