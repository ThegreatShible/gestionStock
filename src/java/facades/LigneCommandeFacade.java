/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.LigneCommande;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nabih
 */
@Stateless
public class LigneCommandeFacade extends AbstractFacade<LigneCommande> {

    @PersistenceContext(unitName = "StockManagementPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneCommandeFacade() {
        super(LigneCommande.class);
    }
    
      public Collection<LigneCommande> getParIdTransaction(int idTransaction){
        List<LigneCommande> list =  em.createNativeQuery("select * from GestionStock.dbo.LigneCommande where LigneCommande.idTransaction = '"+idTransaction+"';",LigneCommande.class).getResultList();
        System.out.println("id :::: "+idTransaction);
        for(LigneCommande l : list){
            System.out.println(l);
        }
        return list;
    }
}
