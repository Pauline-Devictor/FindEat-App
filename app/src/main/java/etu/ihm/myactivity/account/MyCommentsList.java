package etu.ihm.myactivity.account;

import java.util.ArrayList;

public class MyCommentsList extends ArrayList<MyComments> {

    public MyCommentsList(){
        add(new MyComments("Chez Jean","4/5","29/09","C'était très bon"));
        add(new MyComments("Miranda","2/5","27/09","Pas top"));
    }
}
