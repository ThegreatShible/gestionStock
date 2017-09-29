/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Type;
import entities.Utilisateurs;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nabih
 */
@Stateless
public class TypeFacade extends AbstractFacade<Type> {

    @PersistenceContext(unitName = "StockManagementPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeFacade() {
        super(Type.class);
    }
    
     public Type getParNom(String nomType){
       return (Type) em.createNativeQuery("SELECT * FROM GestionStock.dbo.Type WHERE nomType = '"+nomType+"' ; ",Type.class).getSingleResult();
   }
     
      public Type getParEmailUtilisateur(String emailUtilisateur){
        return (Type)em.createNativeQuery("select idType,nomType from GestionStock.dbo.utilisateurs  join GestionStock.dbo.Type on utilisateurs.privilege = Type.idType where utilisateurs.email = '"+emailUtilisateur+"';", Type.class).getSingleResult();
    }
    
}
