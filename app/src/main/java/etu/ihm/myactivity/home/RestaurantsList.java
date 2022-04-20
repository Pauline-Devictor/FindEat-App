package etu.ihm.myactivity.home;

import java.util.ArrayList;

import etu.ihm.myactivity.restaurants.Restaurant;

public class RestaurantsList {

    private static ArrayList<Restaurant> restaurantsArrayList = new ArrayList<>();

    static {
        restaurantsArrayList.add(new Restaurant(null,"Le Madison","description",3.5f,null));
        restaurantsArrayList.add(new Restaurant(null,"Le Lys d'Or","description",3f,null));
        restaurantsArrayList.add(new Restaurant(null,"Le Flambadou","description",4f,null));
        restaurantsArrayList.add(new Restaurant(null,"La Source","description",2.5f,null));
        restaurantsArrayList.add(new Restaurant(null,"L'École Buissonière","description",3.5f,null));
        restaurantsArrayList.add(new Restaurant(null,"Chez Jean","description",3.5f,null));
        restaurantsArrayList.add(new Restaurant(null,"Patate","description",3.5f,null));
        restaurantsArrayList.add(new Restaurant(null,"Rouvelong","description",4.5f,null));
    }

    public static Restaurant get(int index) {
        return restaurantsArrayList.get(index);
    }

    public static void remove(int index) {
        restaurantsArrayList.remove(index);
    }

    public static int size() {
        return restaurantsArrayList.size();
    }
}