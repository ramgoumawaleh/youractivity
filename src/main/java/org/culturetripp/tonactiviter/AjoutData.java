package org.culturetripp.tonactiviter;

import java.util.Date;
import javafx.scene.image.Image;

public class AjoutData {

    private String nom;
    private String prenom;
    private String NomActiviter;
    private String image;
    private Date date;




    public AjoutData(String nom,String prenom ,String NomActiviter,String  image,Date date){
        this.nom=nom;
        this.prenom=prenom;
        this.NomActiviter=NomActiviter;
        this.image=image;
        this.date=date;
    }

    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getNomActiviter() {
        return NomActiviter;

    }
    public String getImage() {
        return image;
    }
    public Date getDate() {
        return date;
    }


    public String getTitle() {
        return "";
    }
}

