package etu.ihm.myactivity.account;

import java.util.ArrayList;

public class MyCommentsList {

    public ArrayList<MyComments> commentList;

    public MyCommentsList(){
        commentList = new ArrayList<MyComments>();
        commentList.add(new MyComments("Chez Jean","4/5","29/09","C'était très bon"));
        commentList.add(new MyComments("Taco Bell","5/5","30/08","Meilleurs tacos que j'ai mangés"));
        commentList.add(new MyComments("Quick","3/5","15/04","C'était très bon. Le service est très agréable malgré que le serveur a renversé une bouteille de rouge sur ma fille."));
        commentList.add(new MyComments("Five guys","4/5","04/03","Très bon"));
        commentList.add(new MyComments("Subway","1/5","28/01","Pas bon du tout"));
    }

    public ArrayList<MyComments> getCommentList(){ return commentList; }
}
