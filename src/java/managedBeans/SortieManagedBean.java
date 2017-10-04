/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Destination;
import entities.LigneCommande;
import entities.LigneCommandePK;
import entities.Produit;
import entities.Transactions;
import entities.Type;
import facades.DestinationFacade;
import facades.LigneCommandeFacade;
import facades.ProduitFacade;
import facades.TransactionsFacade;
import facades.TypeFacade;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import models.TransactionType;

/**
 *
 * @author nabih
 */
@ManagedBean
@ViewScoped
public class SortieManagedBean implements Serializable {

    private String privilege;
    final private TransactionType transactionType = TransactionType.SORTIE;

    @EJB
    private ProduitFacade produitFacade;
    @EJB
    private TransactionsFacade transactionFacade;
    @EJB
    private LigneCommandeFacade ligneCommandeFacade;
    @EJB
    private DestinationFacade destinationFacade;

    @EJB
    private TypeFacade typeFacade;

    private Transactions transaction = new Transactions();
    private Collection<Produit> produits = new ArrayList();
    private Produit produit = new Produit();
    private int quantite;
    HashMap<Integer, LigneCommande> lignesCommande = new HashMap();
    private Collection<LigneCommande> ligneCom = new ArrayList();
    private int modifQuantite;
    private short destinationId;
    private Collection<Destination> destinations;
    private int produitId;
    private LigneCommande ligneCommande;

    public LigneCommande getLigneCommande() {
        return ligneCommande;
    }

    public void setLigneCommande(LigneCommande ligneCommande) {
        this.ligneCommande = ligneCommande;
    }

    public int getProduitId() {
        return produitId;
    }

    public void setProduitId(int produitId) {
        this.produitId = produitId;
        this.produit = produitFacade.find(produitId);
    }

    public short getDestinationId() {
        return destinationId;
    }

    public Collection<Destination> getDestinations() {
        return destinationFacade.findAll();
    }

    public void setDestinations(Collection<Destination> destinations) {
        this.destinations = destinations;
    }

    public void setDestinationId(short destinationId) {
        this.destinationId = destinationId;
    }

    public HashMap<Integer, LigneCommande> getLignesCommande() {
        return lignesCommande;
    }

    public void setLignesCommande(HashMap<Integer, LigneCommande> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }

    public Collection<LigneCommande> getLigneCom() {
        return getLignesCommande().values();
    }

    public void setLigneCom(Collection<LigneCommande> ligneCom) {
        this.ligneCom = ligneCom;
    }

    public int getModifQuantite() {
        return modifQuantite;
    }

    public void setModifQuantite(int modifQuantite) {
        this.modifQuantite = modifQuantite;
    }

    public void ajouterLigneCommande() throws Exception {
        LigneCommande l = new LigneCommande();
        Produit p = getProduit();
        if (quantite > p.getQuantiteEnStock() || quantite <= 0) {
            System.out.println("proooooooooooobllleeeeeeeeeeeemeeeeeeeeeeeeeeeee");
            throw new Exception("quantite demandee superieure a la quantite en stock ou non strictement positive");
        } else if (lignesCommande.get(p.getIdProduit()) != null) {
            
            throw new Exception("produit existant");
        } else {
            System.out.println("produit " + p.getNomProduit());
            System.out.println("quantite  ::: "+ quantite);
            System.out.println("quantite en stock :: "+ p.getQuantiteEnStock());
            l.setIdProduit(p);
            l.setQuantite(quantite);
            lignesCommande.put(p.getIdProduit(), l);

        }
    }

    public void supprimerLigne(int idProduit) throws Exception {
        LigneCommande ligneCommande = lignesCommande.get(idProduit);
        if (ligneCommande != null) {
            lignesCommande.remove(idProduit);
        } else {
            throw new Exception("ligne commande inexistante");
        }
    }

    public void modifierLigne() throws Exception {

        if (ligneCommande == null) {
            throw new Exception("produit non trouve");
        } else {
            Produit pr = ligneCommande.getIdProduit();
            System.out.println("le produit est : " + pr.getNomProduit());
            if (modifQuantite > pr.getQuantiteEnStock()) {
                throw new Exception("la quantite demande excede celle du stock");
            } else {

                int idProduit = ligneCommande.getIdProduit().getIdProduit();
                LigneCommande l = new LigneCommande();
                l.setQuantite(modifQuantite);
                l.setIdProduit(pr);
                lignesCommande.remove(idProduit);
                lignesCommande.put(idProduit, l);
            }

        }
    }

    public void ajouterSortie() throws Exception {
        if (!lignesCommande.isEmpty()) {

            transaction.setTypeTransaction(transactionType.name());

            Destination destination = destinationFacade.find(destinationId);
            transaction.setDestination(destination);
            long time = System.currentTimeMillis();
            transaction.setDateTransaction(new Date(time));
            Type type = typeFacade.getParNom(privilege);
            transaction.setIdType(type);
            transactionFacade.create(transaction);

            for (LigneCommande l : lignesCommande.values()) {
                l.getLigneCommandePK().setIdTransaction(transaction.getIdTransaction());
                Produit produit = l.getIdProduit();
                produit.setQuantiteEnStock( produit.getQuantiteEnStock() - l.getQuantite());
                produitFacade.edit(produit);
                ligneCommandeFacade.create(l);

            }
            ExternalContext oContext = FacesContext.getCurrentInstance().getExternalContext();
            oContext.redirect("/templ/faces/bothPages/traffic.xhtml");
        } else {
            throw new Exception("sortie vide");
        }
    }

    public Transactions getTransaction() {
        return transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;
    }

    public Collection<Produit> getProduits() {
        if (privilege.equals("ADMINISTRATEUR")) {
            return produitFacade.findAll();
        } else {
            return produitFacade.getParType(privilege);
        }
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @PostConstruct
    public void init() {
        if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("AdminRole")) {
            privilege = "ADMINISTRATEUR";

        } else {
            String name = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            Type t = typeFacade.getParEmailUtilisateur(name);
            privilege = t.getNomType();

        }
    }

    public SortieManagedBean() {
    }

}
