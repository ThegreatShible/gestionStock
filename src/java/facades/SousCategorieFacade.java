/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.SousCategorie;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nabih
 */
@Stateless
public class SousCategorieFacade extends AbstractFacade<SousCategorie> {

    @PersistenceContext(unitName = "StockManagementPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SousCategorieFacade() {
        super(SousCategorie.class);
    }
    
        public Collection<SousCategorie> getParCategorieNom(String nomCategorie){
        return em.createNativeQuery("select idSousCategorie,GestionStock.dbo.SousCategorie.idCategorie ,GestionStock.dbo.SousCategorie.idType, nom from GestionStock.dbo.SousCategorie left join GestionStock.dbo.Categorie on GestionStock.dbo.SousCategorie.idCategorie = GestionStock.dbo.Categorie.idCategorie where nomCategorie = '"+nomCategorie+"';",SousCategorie.class).getResultList();
                
    }
    
    public Collection<SousCategorie> getParId(short idType , short idCategorie ){
        return em.createNativeQuery("select * from GestionStock.dbo.SousCategorie where idCategorie ='"+idCategorie+"' and idType = '"+idType+"' ;", SousCategorie.class).getResultList();
    }
    
    public SousCategorie getParNomSousCategorie(String nomSousCategorie){
      return (SousCategorie) em.createNativeQuery("select * from GestionStock.dbo.SousCategorie where nom = '"+nomSousCategorie+"';", SousCategorie.class).getSingleResult();
    }

    
}
