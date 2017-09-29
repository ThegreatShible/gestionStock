/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.SousCategorie;
import entities.SousCategoriePK;
import facades.SousCategorieFacade;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author nabih
 */
@ManagedBean
@ViewScoped
public class SousCategorieMangedBean implements Serializable {

    
    @EJB
    SousCategorieFacade sousCategorieFacade;
    
    private SousCategorie sousCategorie = new SousCategorie();
    private short typeId;
    private short categorieId;
    private Collection<SousCategorie> sousCategories;
    private SousCategorie modifSousCategorie = new SousCategorie();
    private String modifNom;

    public String getModifNom() {
        return modifNom;
    }

    public void setModifNom(String modifNom) {
        this.modifNom = modifNom;
    }
    
    

    public SousCategorie getModifSousCategorie() {
        System.out.println("on fait le get");
        return modifSousCategorie;
    }

    public void setModifSousCategorie(SousCategorie modifSousCategorie) {
        System.out.println("on fait le set");
        this.modifSousCategorie = modifSousCategorie;
    }
    
    

    public Collection<SousCategorie> getSousCategories() {
        return sousCategorieFacade.getParId(typeId, categorieId);
    }
     @PostConstruct
    public void  init(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
        .getRequest();
        String idType = request.getParameter("typeId");
         System.out.println("type id :::" + idType);
        this.typeId = (short)(Integer.parseInt(idType));
        String categorieId = request.getParameter("categorieId");
         System.out.println("categorieId ::: "+ categorieId);
        this.categorieId = (short)(Integer.parseInt(categorieId));
         System.out.println("fffffffffiiiinnnnnnnniiiiiiii");
       
    }

    public void setSousCategories(Collection<SousCategorie> sousCategories) {
        this.sousCategories = sousCategories;
    }

    
    public SousCategorie getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(SousCategorie sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public short getTypeId() {
        return typeId;
    }

    public void setTypeId(short typeId) {
        this.typeId = typeId;
    }

    public short getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(short categorieId) {
        this.categorieId = categorieId;
    }
    
    public void modifier(){
        modifSousCategorie.setNom(modifNom);
        System.out.println("dans la fonction modifier de sousCategorie "+modifSousCategorie.getNom());
        sousCategorieFacade.edit(modifSousCategorie);
    }
    
    public void ajouterSousCategorie(){
        
        SousCategoriePK sc = new SousCategoriePK();
        sc.setIdType(typeId);
        sc.setIdCategorie(categorieId);
        sousCategorie.setSousCategoriePK(sc);
        sousCategorieFacade.create(sousCategorie);
    }
    
    
 
}
