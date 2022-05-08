package etu.ihm.myactivity.home;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import etu.ihm.myactivity.R;

/**
 * This is the view adapter of the view from the MVC
 */
public class RestaurantsAdapter extends BaseAdapter {
    private final String TAG = "polytech-" + getClass().getSimpleName();

    private IListner listener;

    private Context context;
    private Activity activity = MainActivity.getInstance();

    private LayoutInflater inflater;

    private RestaurantsList model;

    private RestaurantListFragment viewFragment;

    public RestaurantsAdapter(Context context, RestaurantsList restaurantsList, RestaurantListFragment restaurantListFragment) {
        this.context = context;
        this.model=restaurantsList;
        this.viewFragment=restaurantListFragment;
        this.activity = activity;
        inflater = LayoutInflater.from(this.context);
    }

    public int getCount() {
        return model.size();
    }

    public Object getItem(int position) {
        return model.getRestaurant(position);
    }

    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        //Log.d(TAG,"getView position "+position+" pour une taille de "+model.size()+" et une taille fournie de "+getCount());
        LinearLayout layoutItem;

        //(1) : Réutilisation des layouts
        layoutItem = (LinearLayout) (convertView == null ? inflater.inflate(R.layout.restaurant_layout, parent, false) : convertView);


        //(2) : Récupération des TextView de notre layout
        TextView tvName = layoutItem.findViewById(R.id.restaurantName);
        TextView tvGrade = layoutItem.findViewById(R.id.restaurantGrade);
        TextView tvDistance = layoutItem.findViewById(R.id.restaurantDistance);
        //ImageView restaurantPicture = layoutItem.findViewById(R.id.restaurantPicture);

        //(3) : Renseignement des valeurs
        tvName.setText(model.getRestaurant(position).getName());
        tvGrade.setText("" + model.getRestaurant(position).getRate() + "/5");
        tvDistance.setText(String.format("%.1fkm", model.getRestaurant(position).getDistance()));
        //restaurantPicture.setImageBitmap(RestaurantsList.get(position).getPicture());

        layoutItem.setOnClickListener(click -> {
            listener.onClickRestaurant(position);
        });
        //On retourne l'item créé.
        return layoutItem;
    }

    public void refresh(RestaurantsList model){
        Log.d(TAG,"refreshing");
        this.model=model;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });





    }







    //abonnement pour click sur le nom...
    public void addListener(IListner listener) {
        this.listener = listener;
    }


}




