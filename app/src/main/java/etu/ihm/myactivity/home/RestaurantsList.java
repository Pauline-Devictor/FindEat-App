package etu.ihm.myactivity.home;

import android.util.Log;

import java.util.ArrayList;

import etu.ihm.myactivity.factoryTests.Lieux;
import etu.ihm.myactivity.factoryTests.LieuxFactory;
import etu.ihm.myactivity.restaurants.Restaurant;

public class RestaurantsList {

    private static ArrayList<Lieux> restaurantsArrayList = new ArrayList<>();

    static {

        try {
            restaurantsArrayList.add(LieuxFactory.build("Le Madison",1,null,"description",3.5f,null));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        try {
            restaurantsArrayList.add(LieuxFactory.build("Le Lys d'Or",1,null,"description",3.5f,null));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        /**
        restaurantsArrayList.add(new Restaurant(null,"Le Flambadou","description",4f,null));
        restaurantsArrayList.add(new Restaurant(null,"La Source","description",2.5f,null));
        restaurantsArrayList.add(new Restaurant(null,"L'École Buissonière","description",3.5f,null));
        restaurantsArrayList.add(new Restaurant(null,"Chez Jean","description",3.5f,null));
        restaurantsArrayList.add(new Restaurant(null,"Patate","description",3.5f,null));
        restaurantsArrayList.add(new Restaurant(null,"Rouvelong","description",4.5f,null));
         **/


    }

    public static Lieux get(int index) {
        return restaurantsArrayList.get(index);
    }

    public static void remove(int index) {
        restaurantsArrayList.remove(index);
    }

    public static int size() {
        return restaurantsArrayList.size();
    }

    public static void add(Lieux restaurant){restaurantsArrayList.add(restaurant);
    Log.d("a","passe ADd");}
}