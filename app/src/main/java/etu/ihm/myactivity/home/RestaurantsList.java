package etu.ihm.myactivity.home;
import android.util.Log;
import java.io.Serializable;
import java.util.ArrayList;
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
        //restaurantsArrayList.add(new Restaurant(null, "Le Madison", "description", 3.5f, null));
        /*restaurantsArrayList.add(new Restaurant(null, "Le Lys d'Or", "description", 3f, null));
         restaurantsArrayList.add(new Restaurant(null,"Le Flambadou","description",4f,null));
         restaurantsArrayList.add(new Restaurant(null,"La Source","description",2.5f,null));
         restaurantsArrayList.add(new Restaurant(null,"L'École Buissonière","description",3.5f,null));
         restaurantsArrayList.add(new Restaurant(null,"Chez Jean","description",3.5f,null));
         restaurantsArrayList.add(new Restaurant(null,"Patate","description",3.5f,null));
         restaurantsArrayList.add(new Restaurant(null,"Rouvelong","description",4.5f,null));
         **/
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
        restaurantsArrayList.add(lieux);
        Log.d(TAG,"resto "+lieux.getName()+" ajouté");
        Log.d("a", "passe ADd");
        setChanged();
        notifyObservers();
    }
    public void filterOver4(){
        Log.d(TAG,"filter over 4 method, former size = "+size());
        restaurantsArrayList.removeIf(restaurant -> restaurant.getGrade()<4.5);
        Log.d(TAG,"new size = "+size());
        setChanged();
        notifyObservers(this);
    }
    public void empty(){
        restaurantsArrayList = new ArrayList<>();
        notifyObservers(this);
    }

    public static ArrayList<Lieux> getRestaurantsArrayList() {
        return restaurantsArrayList;
    }
}