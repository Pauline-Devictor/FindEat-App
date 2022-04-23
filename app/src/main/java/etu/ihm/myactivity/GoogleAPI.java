package etu.ihm.myactivity;

import android.app.ProgressDialog;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class  GoogleAPI extends Thread {

    private static String API_KEY = "AIzaSyAaSrozKCZYHXJD4F5zynJxebwsvf5nA9I";
    String data = "";
    ArrayList<String> restoList = new ArrayList<>();
    private int radius;
    private LocationGPS location;

    //https://maps.googleapis.com/maps/api/place/
    // nearbysearch/json?keyword=cruise&
    // location=43.6153531%2C7.0719072
    // &radius=1500
    // &type=restaurant
    // &key=AIzaSyAaSrozKCZYHXJD4F5zynJxebwsvf5nA9I




    public GoogleAPI(int radius, LocationGPS location){
        this.radius = radius;
        this.location = location;
    }



    @Override
    public void run(){

        Log.d("a","run passe");

        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?fields=formatted_address%2Cname%2Crating%2Copening_hours%2Cgeometry&input=Museum%20of%20Contemporary%20Art%20Australia&inputtype=textquery&key=AIzaSyAaSrozKCZYHXJD4F5zynJxebwsvf5nA9I");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while((line= bufferedReader.readLine())!=null){
                Log.d("a","dans le while "+line);
                data = data+line;
            }

            if(!data.isEmpty()){
                JSONObject jsonObject = new JSONObject(data);
                JSONArray resto = jsonObject.getJSONArray("Resto");

                restoList.clear();

                for(int i=0;i<resto.length();i++){
                    JSONObject names = resto.getJSONObject(i);
                    String name = names.getString("name");
                    restoList.add(name);
                    Log.d("a","for final " +name);


                }
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}

