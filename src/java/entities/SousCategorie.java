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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "SousCategorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SousCategorie.findAll", query = "SELECT s FROM SousCategorie s")
    , @NamedQuery(name = "SousCategorie.findByIdSousCategorie", query = "SELECT s FROM SousCategorie s WHERE s.sousCategoriePK.idSousCategorie = :idSousCategorie")
    , @NamedQuery(name = "SousCategorie.findByIdCategorie", query = "SELECT s FROM SousCategorie s WHERE s.sousCategoriePK.idCategorie = :idCategorie")
    , @NamedQuery(name = "SousCategorie.findByIdType", query = "SELECT s FROM SousCategorie s WHERE s.sousCategoriePK.idType = :idType")
    , @NamedQuery(name = "SousCategorie.findByNom", query = "SELECT s FROM SousCategorie s WHERE s.nom = :nom")})
public class SousCategorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SousCategoriePK sousCategoriePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nom")
    private String nom;
    @JoinColumns({
        @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie", insertable = false, updatable = false)
        , @JoinColumn(name = "idType", referencedColumnName = "idType", insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categorie categorie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sousCategorie", fetch = FetchType.LAZY)
    private Collection<Produit> produitCollection;

    public SousCategorie() {
    }

    public SousCategorie(SousCategoriePK sousCategoriePK) {
        this.sousCategoriePK = sousCategoriePK;
    }

    public SousCategorie(SousCategoriePK sousCategoriePK, String nom) {
        this.sousCategoriePK = sousCategoriePK;
        this.nom = nom;
    }

    public SousCategorie(short idSousCategorie, short idCategorie, short idType) {
        this.sousCategoriePK = new SousCategoriePK(idSousCategorie, idCategorie, idType);
    }

    public SousCategoriePK getSousCategoriePK() {
        return sousCategoriePK;
    }

    public void setSousCategoriePK(SousCategoriePK sousCategoriePK) {
        this.sousCategoriePK = sousCategoriePK;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @XmlTransient
    public Collection<Produit> getProduitCollection() {
        return produitCollection;
    }

    public void setProduitCollection(Collection<Produit> produitCollection) {
        this.produitCollection = produitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sousCategoriePK != null ? sousCategoriePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SousCategorie)) {
            return false;
        }
        SousCategorie other = (SousCategorie) object;
        if ((this.sousCategoriePK == null && other.sousCategoriePK != null) || (this.sousCategoriePK != null && !this.sousCategoriePK.equals(other.sousCategoriePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SousCategorie[ sousCategoriePK=" + sousCategoriePK + " ]";
    }
    
}
