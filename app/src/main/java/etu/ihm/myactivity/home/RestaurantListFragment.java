package etu.ihm.myactivity.home;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import etu.ihm.myactivity.R;
import etu.ihm.myactivity.restaurants.RestaurantFragment;

//import androidx.fragment.app.Fragment;
import android.app.Fragment;
import android.widget.ListView;
import android.widget.Toast;

public class RestaurantListFragment extends Fragment /*implements IListner, View.OnClickListener*/ {
/*
    private OnButtonClickedListener mCallback;

    public interface OnButtonClickedListener {
        void onButtonClicked(View view);
    }
 */

    public RestaurantListFragment() {}

    //TODO: Avoid non-default constructors in fragments: use a default constructor plus Fragment#setArguments(Bundle) instead

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        //rootView.findViewById(R.id.restoList).setOnClickListener(this);

        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(getActivity());

        ListView list = rootView.findViewById(R.id.restoList);

        list.setAdapter(restaurantsAdapter);

        //restaurantsAdapter.addListener(this);

        return rootView;
    }
/*
    @Override
    public void onClick(View v) {
        mCallback.onButtonClicked(v);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }

    private void createCallbackToParentActivity() {
        try {
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()
                    + " must implement OnButtonClickedListener");
        }
    }

    @Override
    public void onClickRestaurant(int position) {
        Toast toast = Toast.makeText(getActivity(), "restaurant " + position, Toast.LENGTH_SHORT);
        toast.show();
        Fragment restaurantFragment = new RestaurantFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, restaurantFragment);
        transaction.commit();
    }
 */

}
