package etu.ihm.myactivity.home;

import android.util.Log;

/**
 * This is the controller of the MVC
 */
public class FilterController {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    private RestaurantListFragment view;
    private RestaurantsList model;

    public FilterController(RestaurantListFragment view, RestaurantsList model){
        this.view=view;
        this.model=model;
        //Log.d(TAG,"controller created");
    }

    public void sortByDistance(){
        model.sortByDistance();
    }

    public void sortByGrade(){
        model.sortByGrade();
    }

}
