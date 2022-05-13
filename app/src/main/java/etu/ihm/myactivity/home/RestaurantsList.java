package etu.ihm.myactivity.home;
import android.os.Parcelable;
import android.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observable;
import java.util.stream.Collectors;

import etu.ihm.myactivity.factoryTests.Lieux;
import etu.ihm.myactivity.restaurants.Restaurant;
/**
 * This is the Model from the MVC
 */
public class RestaurantsList extends Observable implements Serializable {
    private static String TAG = "polytech-RestaurantsList";
    private static ArrayList<Lieux> restaurantsArrayList;

    public RestaurantsList(){
        restaurantsArrayList = new ArrayList<>();
    }
    public Lieux getRestaurant(int index) {
        return restaurantsArrayList.get(index);
    }
    public void remove(int index) {
        restaurantsArrayList.remove(index);
    }
    public int size() {
        return restaurantsArrayList.size();
    }
    public void add(Lieux lieux) {
        if (!restaurantsArrayList.contains(lieux))
            restaurantsArrayList.add(lieux);
        Log.d(TAG,"resto "+lieux.getName()+" ajouté");
        Log.d("a", "passe ADd");
        setChanged();
        notifyObservers();
    }

    public void sortByDistance(){
        restaurantsArrayList.sort(Lieux.DISTANCE_COMPARATOR);
        setChanged();
        notifyObservers();
    }

    public void sortByGrade(){
        restaurantsArrayList.sort(Lieux.GRADE_COMPARATOR);
        setChanged();
        notifyObservers();
    }

    public void empty(){
        restaurantsArrayList.clear();
        notifyObservers(this);
    }

    public static ArrayList<Lieux> getRestaurantsArrayList() {
        return restaurantsArrayList;
    }

    public Lieux getRestaurantById(String id){
        for (Lieux l : restaurantsArrayList){
            if (l.getPlaceID().equals(id)){
                return l;
            }
        }
        return null;
    }
}