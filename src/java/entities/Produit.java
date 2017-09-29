/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nabih
 */
@Entity
@Table(name = "Produit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
    , @NamedQuery(name = "Produit.findByIdProduit", query = "SELECT p FROM Produit p WHERE p.idProduit = :idProduit")
    , @NamedQuery(name = "Produit.findByNomProduit", query = "SELECT p FROM Produit p WHERE p.nomProduit = :nomProduit")
    , @NamedQuery(name = "Produit.findByQuantiteEnStock", query = "SELECT p FROM Produit p WHERE p.quantiteEnStock = :quantiteEnStock")
    , @NamedQuery(name = "Produit.findByPrixUnitaire", query = "SELECT p FROM Produit p WHERE p.prixUnitaire = :prixUnitaire")})
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProduit")
    private Integer idProduit= Integer.MIN_VALUE;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nomProduit")
    private String nomProduit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantiteEnStock")
    private int quantiteEnStock;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prixUnitaire")
    private int prixUnitaire;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produit", fetch = FetchType.LAZY)
    private Collection<EtatStock> etatStockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProduit", fetch = FetchType.LAZY)
    private Collection<LigneCommande> ligneCommandeCollection;
    @JoinColumns({
        @JoinColumn(name = "idSousCategorie", referencedColumnName = "idSousCategorie")
        , @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
        , @JoinColumn(name = "idType", referencedColumnName = "idType")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SousCategorie sousCategorie;
    @JoinColumn(name = "idUniteStockage", referencedColumnName = "idUniteStockage")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UniteStockage idUniteStockage;

    public Produit() {
    }

    public Produit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Produit(Integer idProduit, String nomProduit, int quantiteEnStock, int prixUnitaire) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.quantiteEnStock = quantiteEnStock;
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    @XmlTransient
    public Collection<EtatStock> getEtatStockCollection() {
        return etatStockCollection;
    }

    public void setEtatStockCollection(Collection<EtatStock> etatStockCollection) {
        this.etatStockCollection = etatStockCollection;
    }

    @XmlTransient
    public Collection<LigneCommande> getLigneCommandeCollection() {
        return ligneCommandeCollection;
    }

    public void setLigneCommandeCollection(Collection<LigneCommande> ligneCommandeCollection) {
        this.ligneCommandeCollection = ligneCommandeCollection;
    }

    public SousCategorie getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(SousCategorie sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public UniteStockage getIdUniteStockage() {
        return idUniteStockage;
    }

    public void setIdUniteStockage(UniteStockage idUniteStockage) {
        this.idUniteStockage = idUniteStockage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduit != null ? idProduit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idProduit == null && other.idProduit != null) || (this.idProduit != null && !this.idProduit.equals(other.idProduit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Produit[ idProduit=" + idProduit + " ]";
    }
    
}
