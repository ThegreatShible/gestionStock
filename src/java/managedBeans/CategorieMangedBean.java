/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Categorie;
import entities.CategoriePK;
import entities.Type;
import facades.CategorieFacade;
import facades.TypeFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import models.CategorieModel;



@ManagedBean
@ViewScoped
public class CategorieMangedBean implements Serializable {
    
    @EJB
    private CategorieFacade facadeCategorie;
    @EJB
    private TypeFacade facadeType;
    
    
    private Collection<Categorie> categories;
    private Type type;
    private Categorie categorie = new Categorie();

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    
    private short idType;
    
    @PostConstruct
    public void  init(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
        .getRequest();
        String idType = request.getParameter("idType");
        System.out.println( "dans categorie idType " + idType);
        this.idType = (short)(Integer.parseInt(idType) % Short.MAX_VALUE);
        type = facadeType.find(this.idType);
        categorie.setType(type);    
    }

    
    
    public short getIdType() {
        return idType;
    }

    public void setIdType(short idType) {
        this.idType = idType;
    }

    public Collection<Categorie> getCategories() {
       
        Collection<Categorie> cats =  facadeCategorie.getParID(idType);
        for(Categorie c : cats){
            System.out.println(c);
        }
        return cats;
    }

    public void setCategories(Collection<Categorie> categories) {
        this.categories = categories;
    }
    public void addCategorie(){
        CategoriePK c = new CategoriePK();
        c.setIdType(idType);
        categorie.setCategoriePK(c);
        facadeCategorie.create(categorie);
    }
    
    
    
    
    
    

  
    
}
