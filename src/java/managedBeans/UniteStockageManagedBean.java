/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;


import entities.UniteStockage;
import facades.UniteStockageFacade;
import java.io.Serializable;
import java.util.ArrayList;
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
public class UniteStockageManagedBean implements Serializable {
    
   @EJB
    private UniteStockageFacade facade;
   
   private UniteStockage uniteStockage = new UniteStockage();
   
    private List<UniteStockage> listUnite = new ArrayList();
    
    private UniteStockage modifUniteStockage= new UniteStockage();

    public UniteStockage getModifUniteStockage() {
        return modifUniteStockage;
    }

    public void setModifUniteStockage(UniteStockage modifUniteStockage) {
        this.modifUniteStockage = modifUniteStockage;
    }
    
    

    public List<UniteStockage> getListUnite() {
        listUnite = facade.findAll();
        return listUnite;
    }

    public void setListUnite(List<UniteStockage> listUnite) {
        this.listUnite = listUnite;
    }
    
    /**
     * Creates a new instance of UniteStockageManagedBean
     */
    public UniteStockageManagedBean() {
    }
    
    public void ajouterUnite(){
        
        facade.create(uniteStockage);
    }

    public UniteStockage getUniteStockage() {
        return uniteStockage;
    }
    
    public void modifier(){
        System.out.println("dans unite stockage modifier "+modifUniteStockage.getNom());
        facade.edit(modifUniteStockage);
    }

    public void setUniteStockage(UniteStockage uniteStockage) {
        this.uniteStockage = uniteStockage;
    }
    
}
