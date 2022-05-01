package etu.ihm.myactivity;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import etu.ihm.myactivity.factoryTests.Lieux;
import etu.ihm.myactivity.factoryTests.RestaurationFactory;
import etu.ihm.myactivity.factoryTests.LieuxFactory;
import etu.ihm.myactivity.home.RestaurantsList;
import etu.ihm.myactivity.restaurants.FiltreEnum;

public class  GoogleAPI extends Thread {

    String data = "";
    ArrayList<String> restoList = new ArrayList<>();
    private int radius;
    private LocationGPS location;
    private ArrayList<FiltreEnum> filters;
    private int maxPrice;
    private String ville;
    private RestaurantsList restaurantsList;
    private int numConstru=0; //Savoir si on passe Location ou nom ville


    //filtres alimtentaire dans jquery maxprice
    //rating et price level a nous de le faire a la main

    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/";
    private static final String NEAY_BY_SEARCH = "nearbysearch/json?keyword=&";
    private static final String TEXT_SEARCH = "textsearch/json?query=";


    //ON MET TYPE RESTAURANT ET TYPE BAR MAIS A SEPRARER APRES SI BESOIN
    private static final String TYPE_RESTAURANT = "type=restaurant&bar";




    private static final String MAX_PRICE = "maxprice=";


    private static final String API_KEY = "AIzaSyAaSrozKCZYHXJD4F5zynJxebwsvf5nA9I";

    private String URL = "";


    //https://maps.googleapis.com/maps/api/place/
    // nearbysearch/json?keyword=cruise&
    // location=43.6153531%2C7.0719072
    // &radius=1500
    // &type=restaurant
    // &key=AIzaSyAaSrozKCZYHXJD4F5zynJxebwsvf5nA9I

    //https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=pizza&location=43.6153531%2C7.0719072&radius=1500&type=restaurant&key=AIzaSyAaSrozKCZYHXJD4F5zynJxebwsvf5nA9I

    //https://maps.googleapis.com/maps/api/place/textsearch/json?query=cafes+in+nice&key=AIzaSyAaSrozKCZYHXJD4F5zynJxebwsvf5nA9I


    //2eme constructeur si cherche avec text places
    public GoogleAPI(RestaurantsList restaurantsList, int radius, LocationGPS location, ArrayList<FiltreEnum> filters, int maxPrice) {
        this.radius = radius;
        this.location = location;
        this.filters = filters;
        this.maxPrice = maxPrice;
        this.restaurantsList = restaurantsList;
        this.numConstru=1;
    }

    public GoogleAPI(String ville, RestaurantsList restaurantsList) {
        this.ville = ville;
        this.restaurantsList = restaurantsList;
        this.numConstru = 2;

    }

    //if this ville bla bla else recherche avec location

    @Override
    public void run(){
        if(this.numConstru==2){ //On est au debut
            runDebut();
        }
        else if(this.numConstru==1){ //Run avec location, filtres ...
            runTest();
        }


    }

    public void runDebut(){
        //https://maps.googleapis.com/maps/api/place/textsearch/json?query=cafes+in+nice&key=AIzaSyAaSrozKCZYHXJD4F5zynJxebwsvf5nA9I
        this.URL = BASE_URL + TEXT_SEARCH +"restaurant+"+this.ville+"+"+"restaurant"+ "&key=" + API_KEY;
        Log.d("a", "URL vaut de BASE vaut " + URL);
        fetchData();

    }

    public void runTest() {
        String URLlocation = "location=" + location.getLatitude() + "%2C" + location.getLongitude() + "&radius=" + radius;
        String filter = "";
        for (FiltreEnum f : filters) {
            filter += f.name().toLowerCase(Locale.ROOT) + "&";
        }
        Log.d("a", "filtre vaut " + filter);

        this.URL = BASE_URL + NEAY_BY_SEARCH + filter + "maxprice=" + maxPrice + "&" + URLlocation + "&" + TYPE_RESTAURANT + "&key=" + API_KEY;

        Log.d("a", "URL vaut " + URL);

        fetchData();

    }


    public void fetchData() {

        Log.d("a", "run passe");

        try {
            URL url = new URL(this.URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;


            while ((line = bufferedReader.readLine()) != null) {
                Log.d("a","dans le while "+line);
                data = data + line;

            }


            Log.d("a", "onnnn est laaaaa");
            decodage(data);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


    }


    private void decodage(String s) throws Throwable {
        Log.d("a","passe Decodage");

        LieuxFactory restaurationFactory = new RestaurationFactory();
        Gson gson = new Gson();
        PlacesApiParser response = gson.fromJson(data, PlacesApiParser.class);
        String status = response. getStatus();
        if(status.matches("OK")){
            List<PlacesApiParser.ResultsDTO> resultsDTOList = response.getResults();
            PlacesApiParser.ResultsDTO results = resultsDTOList.get(0);
            Log.d("premier",results.toString());


            restaurantsList.empty();
            Lieux resto;

            for(int i=0;i<resultsDTOList.size();i++){
                PlacesApiParser.ResultsDTO tmp = resultsDTOList.get(i);
                Log.d("info",tmp.toString());
                if(tmp.getOpening_hours()==null){continue;}
                Bitmap bitmap = null; //TRAITER POUR CHAQUE TMP la valeur de ce bitmap, stocker String reference ?

                if(tmp.getTypes().contains("bar")) {
                    resto = restaurationFactory.build(LieuxFactory.BAR, tmp.getName(), tmp.getPlace_id(), tmp.getOpening_hours().getOpen_now(), bitmap, tmp.getRating(), tmp.getGeometry().getLocation().getLng(), tmp.getGeometry().getLocation().getLat(), tmp.getPrice_level());
                    Log.d("a", tmp.getName() + " C'est un restoBar ");
                }
                else{
                    resto = restaurationFactory.build(LieuxFactory.RESTAURANT, tmp.getName(), tmp.getPlace_id(), tmp.getOpening_hours().getOpen_now(), bitmap, tmp.getRating(), tmp.getGeometry().getLocation().getLng(), tmp.getGeometry().getLocation().getLat(), tmp.getPrice_level());
                    Log.d("a", tmp.getName() + " C'est un vrai resto ");

                }
                restaurantsList.add(resto);
                Log.d("a","on a add un resto" + resto.getName());
            }

        }
        else{
            Log.d("a","AUCUN RESTO TROUVE");
        }
    }

}

