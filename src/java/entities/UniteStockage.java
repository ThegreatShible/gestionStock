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
@Table(name = "UniteStockage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UniteStockage.findAll", query = "SELECT u FROM UniteStockage u")
    , @NamedQuery(name = "UniteStockage.findByIdUniteStockage", query = "SELECT u FROM UniteStockage u WHERE u.idUniteStockage = :idUniteStockage")
    , @NamedQuery(name = "UniteStockage.findByNom", query = "SELECT u FROM UniteStockage u WHERE u.nom = :nom")})
public class UniteStockage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUniteStockage")
    private Short idUniteStockage = Short.MIN_VALUE;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nom")
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUniteStockage", fetch = FetchType.LAZY)
    private Collection<Produit> produitCollection;

    public UniteStockage() {
    }

    public UniteStockage(Short idUniteStockage) {
        this.idUniteStockage = idUniteStockage;
    }

    public UniteStockage(Short idUniteStockage, String nom) {
        this.idUniteStockage = idUniteStockage;
        this.nom = nom;
    }

    public Short getIdUniteStockage() {
        return idUniteStockage;
    }

    public void setIdUniteStockage(Short idUniteStockage) {
        this.idUniteStockage = idUniteStockage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        hash += (idUniteStockage != null ? idUniteStockage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UniteStockage)) {
            return false;
        }
        UniteStockage other = (UniteStockage) object;
        if ((this.idUniteStockage == null && other.idUniteStockage != null) || (this.idUniteStockage != null && !this.idUniteStockage.equals(other.idUniteStockage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UniteStockage[ idUniteStockage=" + idUniteStockage + " ]";
    }
    
}
