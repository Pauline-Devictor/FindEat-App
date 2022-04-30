package etu.ihm.myactivity.factoryTests;

import android.graphics.Bitmap;

public class RestaurationFactory extends LieuxFactory {


    public Lieux build(int type,String name, String placeID, boolean openNow, Bitmap picture, double rate, double longitude, double latitude, Integer priceLevel) throws Throwable{
        switch (type){
            case RESTAURANT: return new Restaurants(name,placeID,openNow, picture, rate, longitude, latitude, priceLevel);
            case BAR: return new RestoBar(name,placeID,openNow, picture, rate, longitude, latitude, priceLevel,18,20);
            default: throw new Throwable("couldn't create");

        }
    }
}
