/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nabih
 */
@Entity
@Table(name = "EtatStock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EtatStock.findAll", query = "SELECT e FROM EtatStock e")
    , @NamedQuery(name = "EtatStock.findByMoment", query = "SELECT e FROM EtatStock e WHERE e.etatStockPK.moment = :moment")
    , @NamedQuery(name = "EtatStock.findByIdProduit", query = "SELECT e FROM EtatStock e WHERE e.etatStockPK.idProduit = :idProduit")
    , @NamedQuery(name = "EtatStock.findByQuantite", query = "SELECT e FROM EtatStock e WHERE e.quantite = :quantite")})
public class EtatStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EtatStockPK etatStockPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite")
    private short quantite;
    @JoinColumn(name = "idProduit", referencedColumnName = "idProduit", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Produit produit;

    public EtatStock() {
    }

    public EtatStock(EtatStockPK etatStockPK) {
        this.etatStockPK = etatStockPK;
    }

    public EtatStock(EtatStockPK etatStockPK, short quantite) {
        this.etatStockPK = etatStockPK;
        this.quantite = quantite;
    }

    public EtatStock(Date moment, int idProduit) {
        this.etatStockPK = new EtatStockPK(moment, idProduit);
    }

    public EtatStockPK getEtatStockPK() {
        return etatStockPK;
    }

    public void setEtatStockPK(EtatStockPK etatStockPK) {
        this.etatStockPK = etatStockPK;
    }

    public short getQuantite() {
        return quantite;
    }

    public void setQuantite(short quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (etatStockPK != null ? etatStockPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtatStock)) {
            return false;
        }
        EtatStock other = (EtatStock) object;
        if ((this.etatStockPK == null && other.etatStockPK != null) || (this.etatStockPK != null && !this.etatStockPK.equals(other.etatStockPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EtatStock[ etatStockPK=" + etatStockPK + " ]";
    }
    
}
