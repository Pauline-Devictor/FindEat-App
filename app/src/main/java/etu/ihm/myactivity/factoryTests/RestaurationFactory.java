package etu.ihm.myactivity.factoryTests;

import android.graphics.Bitmap;

import org.osmdroid.util.GeoPoint;

public class RestaurationFactory extends LieuxFactory {


    public Lieux build(int type, String name, String placeID, boolean openNow, String picture, double rate, double longitude, double latitude, Integer priceLevel, double distance) throws Throwable{
        switch (type){
            case RESTAURANT: return new Restaurants(name,placeID,openNow, picture, rate, longitude, latitude, priceLevel, distance);
            case BAR: return new RestoBar(name,placeID,openNow, picture, rate, longitude, latitude, priceLevel, distance, 18,20);
            default: throw new Throwable("couldn't create");

        }
    }
}
