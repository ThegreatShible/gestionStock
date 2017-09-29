/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Fournisseur;
import entities.LigneCommande;
import entities.LigneCommandePK;
import entities.Produit;

import entities.Transactions;
import entities.Type;
import facades.FournisseurFacade;
import facades.LigneCommandeFacade;
import facades.ProduitFacade;
import facades.TransactionsFacade;
import facades.TypeFacade;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;


import javax.servlet.http.HttpServletRequest;
import javax.transaction.UserTransaction;

import models.TransactionType;

/**
 *
 * @author nabih
 */
@ManagedBean
@ViewScoped
public class EntreeManagedBean implements Serializable {

    // privilege defini le type d'utilisateur qui fait l'entree, si privilege == "ADMINISTRATEUR" il est donc administratuer
    // et don c a le droit de consulter toutes les entrees et de rajouter n'importe la quelle
    //si privilege == "nomType" dans la table Type, donc l'utilisateur est le gestionnaire de stock du type specifie
    private String privilege;
    final private TransactionType transactionType = TransactionType.ENTREE;

    @EJB
    private FournisseurFacade fournisseurFacade;
    @EJB
    private ProduitFacade produitFacade;
    @EJB
    private TransactionsFacade transactionFacade;
    @EJB
    private LigneCommandeFacade ligneCommandeFacade;
    @EJB
    private TypeFacade typeFacade;

    private Map<Integer, LigneCommande> lignesCommande = new HashMap();
    private Transactions transaction = new Transactions();
    private Collection<Produit> produits = new ArrayList();
    private Collection<Fournisseur> fournisseurs = new ArrayList();
    private short fournisseurId;
    private int produitId;
    private int quantite;
    private int prixUnitaire;
    private Collection<LigneCommande> lignesCommandeTest = new ArrayList();
    private int modifQuantite;
    private int modifPrixUnitaire;
    private LigneCommande ligneCommande;

    public LigneCommande getLigneCommande() {
        return ligneCommande;
    }

    public void setLigneCommande(LigneCommande ligneCommande) {
        this.ligneCommande = ligneCommande;
    }

    public int getModifQuantite() {
        return modifQuantite;
    }

    public void setModifQuantite(int modifQuantite) {
        this.modifQuantite = modifQuantite;
    }

    public int getModifPrixUnitaire() {
        return modifPrixUnitaire;
    }

    public void setModifPrixUnitaire(int modifPrixUnitaire) {
        this.modifPrixUnitaire = modifPrixUnitaire;
    }

    public Collection<LigneCommande> getLignesCommandeTest() {
        return lignesCommande.values();
    }

    public void setLignesCommandeTest(Collection<LigneCommande> lignesCommandeTest) {
        this.lignesCommandeTest = lignesCommandeTest;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {

        this.quantite = quantite;
    }

    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getProduitId() {
        return produitId;
    }

    public void setProduitId(int produitId) {
        this.produitId = produitId;
    }

    public Collection<Produit> getProduits() {
   
        return produitFacade.getParType(privilege);

    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }

    public Map<Integer, LigneCommande> getLignesCommande() {
        return lignesCommande;
    }

    public void setLignesCommande(Map<Integer, LigneCommande> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }

    public Collection<Fournisseur> getFournisseurs() {
        return fournisseurFacade.findAll();
    }

    public void setFournisseurs(Collection<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public short getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(short fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public void ajouterLigne() throws Exception {

        if (quantite > 0) {
            Produit p = produitFacade.find(produitId);
            LigneCommande l = new LigneCommande();
            l.setIdProduit(p);
            l.setPrixUnitaire(prixUnitaire);
            l.setQuantite(quantite);

            LigneCommande ligne = lignesCommande.get(l.getIdProduit().getIdProduit());
            if (ligne == null) {
                lignesCommande.put(produitId, l);
            } else {
                throw new Exception("produit deja existant la liste");
            }
        } else {
            throw new Exception("la quantite doit etre strictement positive");
        }
        quantite= 0;
        prixUnitaire = 0;

    }

    public void supprimerLigne(int idProduit) throws Exception {
        LigneCommande ligneCommande = lignesCommande.get(idProduit);
        if (ligneCommande != null) {
            lignesCommande.remove(idProduit);
        } else {
            throw new Exception("ligne non trouvee");
        }
    }

    public void modifierLigne() throws Exception {

        if (ligneCommande != null) {

            LigneCommande autreLigneCommande = new LigneCommande();
            autreLigneCommande.setPrixUnitaire(modifPrixUnitaire);
            autreLigneCommande.setQuantite(modifQuantite);
            autreLigneCommande.setIdProduit(ligneCommande.getIdProduit());
            int idProduit = ligneCommande.getIdProduit().getIdProduit();
            lignesCommande.remove(idProduit);
            lignesCommande.put(idProduit, autreLigneCommande);

        } else {
            throw new Exception("ligne non trouvee");
        }

    }

    public void ajouterEntree() throws Exception {

        if (!lignesCommande.values().isEmpty()) {
            transaction.setTypeTransaction(transactionType.name());
            Fournisseur fournisseur = fournisseurFacade.find(fournisseurId);
            Type type = typeFacade.getParNom(privilege);            
            transaction.setIdType(type);
            transaction.setIdFournisseur(fournisseur);
            
            long time = System.currentTimeMillis();
            transaction.setDateTransaction(new Date(time));
            System.out.println("entree avant creation :  ");
            transactionFacade.create(transaction);
            System.out.println("fuck fuck fuck    ");
            System.out.println("this is the transaction :: : : : : " +  transaction);
            for (LigneCommande l : lignesCommande.values()) {
                LigneCommandePK ligneCommandePK = new LigneCommandePK();
                ligneCommandePK.setIdTransaction(transaction.getIdTransaction());
                l.setLigneCommandePK(ligneCommandePK);
                Produit produit = l.getIdProduit();
                produit.setQuantiteEnStock(l.getQuantite() + produit.getQuantiteEnStock());
                produit.setPrixUnitaire(l.getPrixUnitaire());
                produitFacade.edit(produit);
                System.out.println("creation de ligne de commande");
                ligneCommandeFacade.create(l);

            }
            

        } else {
            throw new Exception("aucune ligne");
        }

    }
    
    /*public void ajouterEntree() throws Exception {

        if (!lignesCommande.values().isEmpty()) {
           UserTransaction userTransaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
            userTransaction.begin();
            
            transaction.setTypeTransaction(transactionType.name());

            Fournisseur fournisseur = fournisseurFacade.find(fournisseurId);
            Type type = typeFacade.getParNom(privilege);
            transaction.setIdType(type);
            transaction.setIdFournisseur(fournisseur);
            long time = System.currentTimeMillis();
            transaction.setDateTransaction(new Date(time));
            userTransaction.commit();

            for (LigneCommande l : lignesCommande.values()) {
                LigneCommandePK ligneCommandePK = new LigneCommandePK();
                ligneCommandePK.setIdTransaction(transaction.getIdTransaction());
                l.setLigneCommandePK(ligneCommandePK);
                Produit produit = l.getIdProduit();
                produit.setQuantiteEnStock(l.getQuantite() + produit.getQuantiteEnStock());
                produit.setPrixUnitaire(l.getPrixUnitaire());
                

            }
            userTransaction.commit();
            

        } else {
            throw new Exception("aucune ligne");
        }

    }*/

    public EntreeManagedBean() {

    }

    @PostConstruct
    public void init() {

      /*  HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        String privilege = request.getParameter("privilege");
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa entree manged Bean");

        this.privilege = privilege;*/
      
      if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("AdminRole")){
            privilege = "ADMINISTRATEUR";
            
        }else{
            String name = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            Type t = typeFacade.getParEmailUtilisateur(name);
            privilege  = t.getNomType();
            
        }

    }
}
