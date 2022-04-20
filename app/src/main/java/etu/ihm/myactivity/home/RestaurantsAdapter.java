package etu.ihm.myactivity.home;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.restaurants.Restaurant;

/**
 * Modify by Fred on 15/03/2022.
 */

public class RestaurantsAdapter extends BaseAdapter {
    private IListner listener;

    private Context context;

    private LayoutInflater inflater;

    public RestaurantsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    public int getCount() {
        return RestaurantsList.size();
    }
    public Object getItem(int position) {
        return RestaurantsList.get(position);
    }
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;

        //(1) : Réutilisation des layouts
        layoutItem = (LinearLayout) (convertView == null ? inflater.inflate(R.layout.restaurant_layout, parent, false) : convertView);


        //(2) : Récupération des TextView de notre layout
        TextView tvName = layoutItem.findViewById(R.id.restaurantName);
        TextView tvDescription = layoutItem.findViewById(R.id.restaurantDescription);
        TextView tvGrade = layoutItem.findViewById(R.id.restaurantGrade);
        ImageView restaurantPicture = layoutItem.findViewById(R.id.restaurantPicture);

        //(3) : Renseignement des valeurs
        tvName.setText(RestaurantsList.get(position).getName());
        tvGrade.setText("Note: "+ RestaurantsList.get(position).getGrade());
        restaurantPicture.setImageBitmap(RestaurantsList.get(position).getPicture());

        //(4) Changement de la couleur du fond de notre item
        tvGrade.setTextColor( RestaurantsList.get(position).getGrade() >= 3 ? Color.GREEN : Color.RED);

        layoutItem.setOnClickListener( click -> {
            listener.onClickRestaurant(position);
        });
        //On retourne l'item créé.
        return layoutItem;
    }

    //abonnement pour click sur le nom...
    public void addListener(IListner listener) {
        this.listener = listener;
    }

}




