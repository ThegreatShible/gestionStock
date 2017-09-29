/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Produit;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nabih
 */
@Stateless
public class ProduitFacade extends AbstractFacade<Produit> {

    @PersistenceContext(unitName = "StockManagementPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProduitFacade() {
        super(Produit.class);
    }
    
    public Collection<Produit> getParType(String nomType){
        return em.createNativeQuery("select idProduit,idSousCategorie,nomProduit,idUniteStockage,quantiteEnStock,prixUnitaire, idCategorie ,Produit.idType from GestionStock.dbo.Produit join GestionStock.dbo.Type on Produit.idType = Type.idType where nomType = '"+nomType+"';",Produit.class).getResultList();
    }
    
    public void createNative(short idSousCategorie, String nomProduit, short idUniteStockage, short idCategorie, short idType){
        em.createNativeQuery("insert into GestionStock.dbo.Produit values ("+idSousCategorie
                +",'"+nomProduit
                +"',"+idUniteStockage
                +",0,0,"+idCategorie
                +","+idType
                +");").executeUpdate();
    }

    
}
