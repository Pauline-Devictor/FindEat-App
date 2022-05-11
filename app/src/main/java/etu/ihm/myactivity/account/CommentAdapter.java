package etu.ihm.myactivity.account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import etu.ihm.myactivity.R;

public class CommentAdapter extends BaseAdapter {

    private MyCommentsList myCommentsList;
    private LayoutInflater inflater;

    public CommentAdapter(Context context, List<MyComments> MyCommentsList){
        this.inflater=LayoutInflater.from(context);
        this.myCommentsList=myCommentsList;
    }

    @Override
    public int getCount() { return this.myCommentsList.size(); }

    @Override
    public Object getItem(int position) { return this.myCommentsList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout;
        layout = (LinearLayout) (convertView==null ? inflater.inflate(R.layout.adapter_comment,parent,false) : convertView);
        ((TextView)layout.findViewById(R.id.restaurantNameComment)).setText(myCommentsList.get(position).getRestaurantName());
        ((TextView)layout.findViewById(R.id.rating)).setText(myCommentsList.get(position).getRating());
        ((TextView)layout.findViewById(R.id.date)).setText(myCommentsList.get(position).getDate());
        ((TextView)layout.findViewById(R.id.comment)).setText(myCommentsList.get(position).getComment());
        return layout;
    }

}
