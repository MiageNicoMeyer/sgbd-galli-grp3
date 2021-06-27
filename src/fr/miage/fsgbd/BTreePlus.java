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

    public Personne searchIndex(Type index,Noeud<Type> arbre) {

        if(arbre.fils.size()==0) {
            if(arbre.keys.contains(index)) {
                for (Type key : arbre.keys) {
                    if ((int) key == (int) index) {
                        Personne result = cherchePersonne(arbre.p, index);
                        if(result != null) return result;
                    }
                }
            }
            return null;
        }
        else {
            for (Noeud<Type> n : arbre.fils) {
                Personne result = searchIndex(index, n);
                if(result != null) return result;
            }
            return null;
        }
    }

    private Personne cherchePersonne(ArrayList<Personne> p, Type index) {
        for(Personne pers : p){
            if(pers.key==(int)index){
                return pers;
            }
        }
        return null;
    }


    public Personne searchSeq(Type index,Noeud<Type> arbre) {

        if(arbre.fils.size() != 0) {
            for (Noeud<Type> n : arbre.fils) {
                 Personne result = searchSeq(index, n);
                 if(result != null) return result;
            }
            return null;
        }
        else{
            if(arbre.keys.contains(index)) {
                for(Personne p : arbre.p) {
                    if(p.key == (int) index) return p;
                }
            }
            return null;
        }
    }

    public Noeud<Type> getRacine() {
        return racine;
    }
}
