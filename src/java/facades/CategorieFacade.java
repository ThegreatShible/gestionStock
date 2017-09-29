/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Categorie;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nabih
 */
@Stateless
public class CategorieFacade extends AbstractFacade<Categorie> {

    @PersistenceContext(unitName = "StockManagementPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategorieFacade() {
        super(Categorie.class);
    }
    
    public Collection<Categorie> getParNomType(String nomType){
      return em.createNativeQuery("select idCategorie, GestionStock.dbo.Categorie.idType, nomCategorie from GestionStock.dbo.Categorie left join GestionStock.dbo.Type on GestionStock.dbo.Categorie.idType = GestionStock.dbo.Type.idType where nomType = '"+nomType+"';",Categorie.class).getResultList();
    }
    
    public Collection<Categorie> getParID(short idType){
        return em.createNativeQuery("select * from GestionStock.dbo.Categorie where idType = '"+idType+"';",Categorie.class).getResultList();
    }
    
}
