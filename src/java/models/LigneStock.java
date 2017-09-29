/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author nabih
 */
public class LigneStock {
    
    private String nomProduit;
    private short Quantite;

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public short getQuantite() {
        return Quantite;
    }

    public void setQuantite(short Quantite) {
        this.Quantite = Quantite;
    }
    
}
