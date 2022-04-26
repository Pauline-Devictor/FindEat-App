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
        Log.d(TAG,"controller created");
    }

    public void filterOver3(){
        Log.d(TAG,"filter over 3");
        model.filterOver3();
    }

    public void filterOver4(){
        Log.d(TAG,"filter over 4");
        model.filterOver4();
    }

}
