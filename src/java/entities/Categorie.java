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
@Table(name = "Categorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c")
    , @NamedQuery(name = "Categorie.findByIdCategorie", query = "SELECT c FROM Categorie c WHERE c.categoriePK.idCategorie = :idCategorie")
    , @NamedQuery(name = "Categorie.findByIdType", query = "SELECT c FROM Categorie c WHERE c.categoriePK.idType = :idType")
    , @NamedQuery(name = "Categorie.findByNomCategorie", query = "SELECT c FROM Categorie c WHERE c.nomCategorie = :nomCategorie")})
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CategoriePK categoriePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nomCategorie")
    private String nomCategorie;
    @JoinColumn(name = "idType", referencedColumnName = "idType", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Type type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie", fetch = FetchType.LAZY)
    private Collection<SousCategorie> sousCategorieCollection;

    public Categorie() {
    }

    public Categorie(CategoriePK categoriePK) {
        this.categoriePK = categoriePK;
    }

    public Categorie(CategoriePK categoriePK, String nomCategorie) {
        this.categoriePK = categoriePK;
        this.nomCategorie = nomCategorie;
    }

    public Categorie(short idCategorie, short idType) {
        this.categoriePK = new CategoriePK(idCategorie, idType);
    }

    public CategoriePK getCategoriePK() {
        return categoriePK;
    }

    public void setCategoriePK(CategoriePK categoriePK) {
        this.categoriePK = categoriePK;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<SousCategorie> getSousCategorieCollection() {
        return sousCategorieCollection;
    }

    public void setSousCategorieCollection(Collection<SousCategorie> sousCategorieCollection) {
        this.sousCategorieCollection = sousCategorieCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoriePK != null ? categoriePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.categoriePK == null && other.categoriePK != null) || (this.categoriePK != null && !this.categoriePK.equals(other.categoriePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Categorie[ categoriePK=" + categoriePK + " ]";
    }
    
}
