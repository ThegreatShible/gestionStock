/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
@Table(name = "LigneCommande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneCommande.findAll", query = "SELECT l FROM LigneCommande l")
    , @NamedQuery(name = "LigneCommande.findByIdLigneCommande", query = "SELECT l FROM LigneCommande l WHERE l.ligneCommandePK.idLigneCommande = :idLigneCommande")
    , @NamedQuery(name = "LigneCommande.findByIdTransaction", query = "SELECT l FROM LigneCommande l WHERE l.ligneCommandePK.idTransaction = :idTransaction")
    , @NamedQuery(name = "LigneCommande.findByQuantite", query = "SELECT l FROM LigneCommande l WHERE l.quantite = :quantite")
    , @NamedQuery(name = "LigneCommande.findByPrixUnitaire", query = "SELECT l FROM LigneCommande l WHERE l.prixUnitaire = :prixUnitaire")})
public class LigneCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LigneCommandePK ligneCommandePK = new LigneCommandePK();
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantite")
    private int quantite;
    @Column(name = "prixUnitaire")
    private Integer prixUnitaire;
    @JoinColumn(name = "idProduit", referencedColumnName = "idProduit")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Produit idProduit;
    @JoinColumn(name = "idTransaction", referencedColumnName = "idTransaction", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Transactions transactions;

    public LigneCommande() {
    }

    public LigneCommande(LigneCommandePK ligneCommandePK) {
        this.ligneCommandePK = ligneCommandePK;
    }

    public LigneCommande(LigneCommandePK ligneCommandePK, int quantite) {
        this.ligneCommandePK = ligneCommandePK;
        this.quantite = quantite;
    }

    public LigneCommande(short idLigneCommande, int idTransaction) {
        this.ligneCommandePK = new LigneCommandePK(idLigneCommande, idTransaction);
    }

    public LigneCommandePK getLigneCommandePK() {
        return ligneCommandePK;
    }

    public void setLigneCommandePK(LigneCommandePK ligneCommandePK) {
        this.ligneCommandePK = ligneCommandePK;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Integer getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Integer prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Produit idProduit) {
        this.idProduit = idProduit;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ligneCommandePK != null ? ligneCommandePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LigneCommande)) {
            return false;
        }
        LigneCommande other = (LigneCommande) object;
        if ((this.ligneCommandePK == null && other.ligneCommandePK != null) || (this.ligneCommandePK != null && !this.ligneCommandePK.equals(other.ligneCommandePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LigneCommande[ ligneCommandePK=" + ligneCommandePK + " ]";
    }
    
}
