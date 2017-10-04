/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Categorie;
import entities.Produit;
import entities.SousCategorie;
import entities.Type;
import entities.UniteStockage;
import facades.CategorieFacade;
import facades.ProduitFacade;
import facades.SousCategorieFacade;
import facades.TypeFacade;
import facades.UniteStockageFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author nabih
 */
@ManagedBean
@ViewScoped
public class ProduitManagedBean implements Serializable {
    
    
    @EJB
    private ProduitFacade produitFacade;
    @EJB
    private UniteStockageFacade uniteStockageFacade;
    @EJB
    private TypeFacade typeFacade;
    @EJB
    private CategorieFacade categorieFacade;
    @EJB
    private SousCategorieFacade sousCategorieFacade;
    
    private Collection<Produit> produits = new ArrayList();
    private String nomProduit;
    private Collection<Type> types;
    private Collection<Categorie> categories = new ArrayList();
    private Collection<SousCategorie> sousCategories = new ArrayList();
    private Collection<UniteStockage> unitesStockage;
    private short uniteStockageId;
    private String nomCategorie;
    private String nomSousCategorie;
    private String nomType;
    private short typeId;
    private short categorieId;
    private short sousCategorieId;

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    
    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        System.out.println("type setting here");
        this.nomType = nomType;
    }
    

    public short getUniteStockageId() {
        return uniteStockageId;
    }

    public void setUniteStockageId(short uniteStockageId) {
        if(uniteStockageId != -1){
            System.out.println("unite stockage ajoute");
            
            
        }
        this.uniteStockageId = uniteStockageId;
    }

  
    
    
 



    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
        
    }

    public String getNomSousCategorie() {
        return nomSousCategorie;
    }

    public void setNomSousCategorie(String nomSousCategorie) {
       if(nomSousCategorie != null){
           System.out.println("sous categorie ajoute");
            SousCategorie s = sousCategorieFacade.getParNomSousCategorie(nomSousCategorie);
            this.sousCategorieId = s.getSousCategoriePK().getIdSousCategorie();
            this.categorieId = s.getSousCategoriePK().getIdCategorie();
            this.typeId = s.getSousCategoriePK().getIdType();
       }
        this.nomSousCategorie = nomSousCategorie;
    }

    

    

    
    public Collection<Type> getTypes() {
        return typeFacade.findAll();
    }

    public void setTypes(Collection<Type> types) {
        this.types = types;
    }

    public Collection<Categorie> getCategories() {

       
            if(nomType != null){
           
                System.out.println(nomType + " nomType dans produtiManagedBean -> categorie");
            categories= categorieFacade.getParNomType(nomType);
            
            for(Categorie c : categories){
                System.out.println(c);
            }
            
            }else{
        
            categories = new ArrayList();
           
            }

        return categories;
    }

    public Collection<UniteStockage> getUnitesStockage() {
        List<UniteStockage> l = uniteStockageFacade.findAll();
        return l;
    }

    public void setUnitesStockage(Collection<UniteStockage> unitesStockage) {
        this.unitesStockage = unitesStockage;
    }

    public void setCategories(Collection<Categorie> categories) {
        this.categories = categories;
    }

    public Collection<SousCategorie> getSousCategories() {
        if (nomCategorie != null ) {
            
            sousCategories = sousCategorieFacade.getParCategorieNom(nomCategorie);
            for(SousCategorie sc : sousCategories){
                System.out.println(sc);
            }
        } else {
           
            sousCategories = new ArrayList();
        }
        return sousCategories;
    }

    public void setSousCategories(Collection<SousCategorie> sousCategories) {
        this.sousCategories = sousCategories;
    }

    public Collection<Produit> getProduits() {
        return produitFacade.findAll();
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }

  

    public void ajouterProduit() {
        System.out.println("produit aaaaaaaaaaaaaaaajjjjjjjjjouuuuuuuteeeeeeeeeeeee");
        System.out.println(typeId+ " " + categorieId+" " + sousCategorieId+ " "+ nomProduit);
        produitFacade.createNative(sousCategorieId, nomProduit, uniteStockageId, categorieId, typeId);
        
    }

    public ProduitManagedBean() {

    }

}
