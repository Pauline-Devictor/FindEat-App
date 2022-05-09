package etu.ihm.myactivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.util.Log;

import com.google.gson.Gson;

import org.osmdroid.util.GeoPoint;

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
import etu.ihm.myactivity.restaurants.Restaurant;
import etu.ihm.myactivity.restaurants.RestaurantFragment;

public class  GoogleAPI extends Thread {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    String data = "";
    ArrayList<String> restoList = new ArrayList<>();
    private int radius;
    private LocationGPS location;
    private ArrayList<FiltreEnum> filters;
    private int maxPrice;
    private String ville;
    private RestaurantsList restaurantsList;
    private String reference;
    private int numConstru=0; //Savoir si on passe Location ou nom ville



    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/";
    private static final String NEAY_BY_SEARCH = "nearbysearch/json?keyword=";
    private static final String TEXT_SEARCH = "textsearch/json?query=";
    private static final String TYPE_RESTAURANT = "type=restaurant&bar";
    private static final String MAX_PRICE = "maxprice=";
    private static final String API_KEY = "AIzaSyAaSrozKCZYHXJD4F5zynJxebwsvf5nA9I";
    public static final String PHOTO = "photo?maxwidth=400&maxheight=300&photo_reference=";
    private String URL = "";


    /**
     * PHOTOOOOOO
     * https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&maxheight=300&photo_reference=Aap_uEBCWua9-el2nF6NhMZR8HGRFJA-3OL_Z-aPwdKdHN8Xpqik96Nrm_bBCAXoIuSkEuOoHsmrDmlsJGyC1UbrAHYZQN-gyP-ZUrUkhSFvdRNwDMnibt1V65hEYPQcOy1Zsit87pL_xvZ1i3B_L1WLkWjQk87EIQlgQSNix7lBvKnK3lli&key=AIzaSyAaSrozKCZYHXJD4F5zynJxebwsvf5nA9I
     */

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

    public GoogleAPI(String reference){
        this.reference = reference;
        this.numConstru = 3;
    }

    //if this ville bla bla else recherche avec location

    @Override
    public void run(){

        if(this.numConstru==2){ //On est au debut
            restaurantsList.empty();
            runDebut();
        }
        else if(this.numConstru==1){ //Run avec location, filtres ...
            restaurantsList.empty();
            runTest();
        }
        else if(this.numConstru==3){
            getPhoto();
        }


    }

    public void runDebut(){
        //https://maps.googleapis.com/maps/api/place/textsearch/json?query=cafes+in+nice&key=AIzaSyAaSrozKCZYHXJD4F5zynJxebwsvf5nA9I
        this.URL = BASE_URL + TEXT_SEARCH +"restaurant+"+this.ville+"+"+"restaurant"+ "&key=" + API_KEY;
        Log.d("a", "URL vaut de BASE vaut " + URL);
        fetchData();

    }

    public void runTest() {
        Log.d(TAG,"runTest");
        String titreMaxPrice = "maxprice=";
        String URLlocation = "location=" + location.getLatitude() + "%2C" + location.getLongitude() + "&radius=" + radius;
        String filter = "";
        if(filters.isEmpty()){Log.d("a","filtreVide");titreMaxPrice = "&maxprice=";}
        for (FiltreEnum f : filters) {
            Log.d("a","le filtre vaut "+f.name());
            filter += f.name().toLowerCase(Locale.ROOT) + "&";
        }
        Log.d("a", "filtre vaut " + filter);

        this.URL = BASE_URL + NEAY_BY_SEARCH + filter + titreMaxPrice + maxPrice + "&" + URLlocation + "&" + TYPE_RESTAURANT + "&key=" + API_KEY;

        Log.d("a", "URL vaut " + URL);

        fetchData();

    }

    public void getPhoto(){
        String tmp =  BASE_URL+PHOTO+reference+"&key="+API_KEY;
        Log.d("a","url photo vaut : "+tmp);
        Bitmap image = null;

        try{
            URL url = new URL(tmp);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(image==null){ Log.d("a","bitmap null"); }
        Log.d("a","tout c'esrt bien passé :"+image.toString());
        RestaurantFragment.updateBitmap(image);
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
                //Log.d("a","dans le while "+line);
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

                if(tmp.getTypes().contains("bar")) {
                    resto = restaurationFactory.build(LieuxFactory.BAR, tmp.getName(), tmp.getPlace_id(), tmp.getOpening_hours().getOpen_now(), tmp.getPhotos().get(0).getPhoto_reference(), tmp.getRating(), tmp.getGeometry().getLocation().getLng(), tmp.getGeometry().getLocation().getLat(), tmp.getPrice_level(), distance(tmp.getGeometry().getLocation().getLat(),tmp.getGeometry().getLocation().getLng()));
                    Log.d("a", tmp.getName() + " C'est un restoBar ");
                }
                else{
                    resto = restaurationFactory.build(LieuxFactory.RESTAURANT, tmp.getName(), tmp.getPlace_id(), tmp.getOpening_hours().getOpen_now(), tmp.getPhotos().get(0).getPhoto_reference(), tmp.getRating(), tmp.getGeometry().getLocation().getLng(), tmp.getGeometry().getLocation().getLat(), tmp.getPrice_level(), distance(tmp.getGeometry().getLocation().getLat(),tmp.getGeometry().getLocation().getLng()));
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

    /**
     * Distance betweeen user and restaurant
     * @return distance in km
     */
    private double distance(double restoLat, double restoLong){
        GeoPoint userPoint = new GeoPoint(this.location.getLatitude(),this.location.getLongitude());
        GeoPoint restoPoint = new GeoPoint(restoLat, restoLong);
        return userPoint.distanceToAsDouble(restoPoint)/1000.;
    }

}

