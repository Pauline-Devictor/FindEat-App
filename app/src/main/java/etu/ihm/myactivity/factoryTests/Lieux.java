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

    public Lieux(String name,Bitmap picture,String description,double grade,ArrayList<Commentaire> comments){
        this.name = name;
        this.picture =picture;
        this.description = description;
        this.grade = grade;
        this.comments = comments;
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
}
