package etu.ihm.myactivity.restaurants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import etu.ihm.myactivity.R;

public class CommentsAdapter extends BaseAdapter {

    private List<Commentaire> commentsList;
    private LayoutInflater inflater;

    public CommentsAdapter(Context context,List<Commentaire> commentaireList){
        this.commentsList=commentaireList;
        this.inflater=LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return this.commentsList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.commentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout;
        layout = (LinearLayout) (convertView==null ? inflater.inflate(R.layout.adapter_comments,parent,false) : convertView);
        ((TextView)layout.findViewById(R.id.auteur)).setText(commentsList.get(position).getAuteur());
        ((TextView)layout.findViewById(R.id.contenu)).setText(commentsList.get(position).getContenu());
        return layout;
    }
}
