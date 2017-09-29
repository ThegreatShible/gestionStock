/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nabih
 */
@Embeddable
public class LigneCommandePK implements Serializable {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idLigneCommande")
    private short idLigneCommande = Short.MIN_VALUE;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTransaction")
    private int idTransaction;

    public LigneCommandePK() {
    }

    public LigneCommandePK(short idLigneCommande, int idTransaction) {
        this.idLigneCommande = idLigneCommande;
        this.idTransaction = idTransaction;
    }

    public short getIdLigneCommande() {
        return idLigneCommande;
    }

    public void setIdLigneCommande(short idLigneCommande) {
        this.idLigneCommande = idLigneCommande;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idLigneCommande;
        hash += (int) idTransaction;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LigneCommandePK)) {
            return false;
        }
        LigneCommandePK other = (LigneCommandePK) object;
        if (this.idLigneCommande != other.idLigneCommande) {
            return false;
        }
        if (this.idTransaction != other.idTransaction) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LigneCommandePK[ idLigneCommande=" + idLigneCommande + ", idTransaction=" + idTransaction + " ]";
    }
    
}
