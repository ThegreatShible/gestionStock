/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Fournisseur;
import facades.FournisseurFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author nabih
 */
@ManagedBean
@ViewScoped
public class FournisseurManagedBean implements Serializable {

    @EJB
    private FournisseurFacade fournisseurFacade;

    
    private Pattern regex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
    private Collection<Fournisseur> fournisseurs = new ArrayList();
    private Fournisseur fournisseur = new Fournisseur();
    private Fournisseur modifFournisseur = new Fournisseur();

    public Fournisseur getModifFournisseur() {
        return modifFournisseur;
    }

    public void setModifFournisseur(Fournisseur modifFournisseur) {
        System.out.println("id      :::::::::::::  "+modifFournisseur.getIdFournisseur());
        this.modifFournisseur = modifFournisseur;
        System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
    }
    
    
    public Collection<Fournisseur> getFournisseurs() {
        return fournisseurFacade.findAll();
    }

    public void setFournisseurs(Collection<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public void addFournisseur() throws Exception {
        System.out.println("olaoalaoalaoalao");
        if (!fournisseur.getNom().equals("")) {
            System.out.println(fournisseur);
            fournisseur.setIdFournisseur(Short.MIN_VALUE);
            fournisseurFacade.create(fournisseur);
        } else {
            System.out.println("exception");

            throw new Exception("champ nom obligatoir");
        }
    }
    
    public void modifierFournisseur() throws Exception{
        System.out.println("j'entre");
        System.out.println("nom fournisseur " + fournisseur.getNom());
         if (!modifFournisseur .getNom().equals("") ) {
            
            
            fournisseurFacade.edit(modifFournisseur);
        } else {
            System.out.println("exception");

            throw new Exception("champ nom non present ou email invalide");
        }
    }

    public FournisseurManagedBean() {
    }

}
