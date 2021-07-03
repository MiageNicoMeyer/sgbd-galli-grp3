package fr.miage.fsgbd;

import java.io.Serializable;

public class Personne implements Serializable {

    int cle;
    String nom;
    String prenom;

    // Constructeur vide
    public Personne() {

    }

    // Constructeur
    public Personne(int cle, String nom, String prenom) {
        this.cle = cle;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public String toString(){
        return "{ cle : " + cle + ", prenom : " + prenom + ", nom : " + nom + " }";
    }
}
