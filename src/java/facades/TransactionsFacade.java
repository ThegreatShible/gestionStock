/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Transactions;
import java.sql.Date;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nabih
 */
@Stateless
public class TransactionsFacade extends AbstractFacade<Transactions> {

    @PersistenceContext(unitName = "StockManagementPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransactionsFacade() {
        super(Transactions.class);
    }
     public Collection<Transactions> geAvecFiltre(String type, String typeTransaction,java.util.Date de, java.util.Date a){
         String query ="";
            
           if(!type.equals("ALL"))
               query += " WHERE nomType = '"+ type+"'";
           if(!typeTransaction.equals("tous")){
               if(!type.equals("ALL"))
                   query += "AND typeTransaction ='"+typeTransaction+"'";
               else 
                   query += " WHERE typeTransaction = '"+ typeTransaction+"'";
           }
           if(de != null){
               Date from = new Date(de.getTime());
               query += "and GestionStock.dbo.Transactions.dateTransaction >= '"+from+"'";
               System.out.println("de "+query);
           }
           if(a !=null){
                Date to = new Date(a.getTime());
               query += "and GestionStock.dbo.Transactions.dateTransaction <= '"+to+"'";
               System.out.println(" a " + query);
           }
       return em.createNativeQuery("Select idTransaction, dateTransaction,typeTransaction,destination,idFournisseur,GestionStock.dbo.Transactions.idType from GestionStock.dbo.Transactions join GestionStock.dbo.Type  on Type.idType = Transactions.idType "+query,Transactions.class).getResultList();
    }
}
