package etu.ihm.myactivity.account;


public class MyComments {

    public String restaurantNameComment;
    public String rating;
    public String date;
    public String comment;

    public MyComments(String restaurantName,String rating,String date,String comment){
        this.restaurantNameComment=restaurantName;
        this.rating=rating;
        this.date=date;
        this.comment=comment;
    }

    public String getRestaurantName() {
        return restaurantNameComment;
    }

    public String getRating() {
        return rating;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }
}
