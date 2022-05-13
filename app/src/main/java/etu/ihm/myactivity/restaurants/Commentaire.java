package etu.ihm.myactivity.restaurants;

public class Commentaire {

    private String auteur;
    private String contenu;
    private String idResto;

    @Override
    public String toString() {
        return contenu+"\n"+" de "+auteur;
    }

    public Commentaire(String auteur, String contenu, String idResto) {
        this.auteur = auteur;
        this.contenu = contenu;
        this.idResto = idResto;
    }

    public Commentaire(){}


    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getIdResto() {
        return idResto;
    }

    public void setIdResto(String idResto) {
        this.idResto = idResto;
    }



}
