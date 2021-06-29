package fr.miage.fsgbd;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author Galli Gregory, Mopolo Moke Gabriel
 * @param <Type>
 */
public class BTreePlus<Type> implements Serializable {
    private Noeud<Type> racine;

    public BTreePlus(int u, Executable e) {
        racine = new Noeud<Type>(u, e, null);
    }

    public void afficheArbre() {
        racine.afficheNoeud(true, 0);
    }

    /**
     * Méthode récursive permettant de récupérer tous les noeuds
     *
     * @return DefaultMutableTreeNode
     */
    public DefaultMutableTreeNode bArbreToJTree() {
        return bArbreToJTree(racine);
    }

    private DefaultMutableTreeNode bArbreToJTree(Noeud<Type> root) {
        StringBuilder txt = new StringBuilder();
        for (Type key : root.keys)
            txt.append(key.toString()).append(" ");

        DefaultMutableTreeNode racine2 = new DefaultMutableTreeNode(txt.toString(), true);
        for (Noeud<Type> fil : root.fils)
            racine2.add(bArbreToJTree(fil));

        return racine2;
    }


    public boolean addValeur(Type valeur) {
        if (racine.contient(valeur) == null) {
            Noeud<Type> newRacine = racine.addValeur(valeur);
            if (racine != newRacine)
                racine = newRacine;
            return true;
        }
        return false;
    }

    public void removeValeur(Type valeur) {
        System.out.println("Retrait de la valeur : " + valeur.toString());
        if (racine.contient(valeur) != null) {
            Noeud<Type> newRacine = racine.removeValeur(valeur, false);
            if (racine != newRacine)
                racine = newRacine;
        }
    }

    /**
     * Recherche par index - Algorithme de recherche dichotomique.
     * @param id
     * @param noeud
     * @return le fils du noeud correspondant à l'identifiant.
     */
    private Noeud<Type> searchFils(Type id, Noeud<Type> noeud) {
        if (noeud.fils != null) {
            for (int i = 0; i < noeud.keys.size(); i++) {
                if (i == 0 && (int) noeud.keys.get(0) > (int) id)
                    return noeud.fils.get(0);
                else if (i == noeud.keys.size() - 1 && (int) noeud.keys.get(noeud.keys.size() - 1) < (int) id)
                    return noeud.fils.get(noeud.fils.size() - 1);
                else if ((int) noeud.keys.get(i + 1) > (int) id && (int) noeud.keys.get(i) < (int) id)
                    return noeud.fils.get(i + 1);
            }
        }
        return null;
    }

    /**
     * Méthode générique de parcours d'un tableau de personnes.
     * @param id
     * @param personnes
     * @return la personne recherchée.
     */
    private Personne searchPersonne(Type id, ArrayList<Personne> personnes) {
        for(Personne personne : personnes) {
            if(personne.cle == (int) id)
                return personne;
        }
        return null;
    }

    /**
     * Recherche par index
     * @param id
     * @param noeud
     * @return Personne result ; La personne trouvée.
     * Complexité : O(log(n))
     */
    public Personne searchIndex(Type id, Noeud<Type> noeud) {
        Personne result;
        if (noeud.keys.contains(id))
            return searchPersonne(id, noeud.personnes);
        else {
            Noeud<Type> fils = searchFils(id, noeud);
            result = searchIndex(id, fils);
        }
        return result;
    }

    /**
     * Recherche séquentielle
     * @param id
     * @param noeud
     * @return Personne result ; La personne trouvée.
     * Complexité : O(n)
     */
    public Personne searchSeq(Type id, Noeud<Type> noeud) {
        Personne result = new Personne();
        if (noeud.keys.contains(id))
            result = searchPersonne(id, noeud.personnes);
        else if (noeud.fils.size() != 0) {
            for (Noeud<Type> fils : noeud.fils) {
                if (fils.keys.contains(id))
                    result = searchPersonne(id, fils.personnes);
                else
                    result = searchSeq(id, fils);
            }
        }
        return result;
    }

    public Noeud<Type> getRacine() {
        return racine;
    }
}
