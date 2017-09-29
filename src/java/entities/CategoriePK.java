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
public class CategoriePK implements Serializable {

   @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCategorie")
    private short idCategorie = Short.MIN_VALUE;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idType")
    private short idType;

    public CategoriePK() {
    }

    public CategoriePK(short idCategorie, short idType) {
        this.idCategorie = idCategorie;
        this.idType = idType;
    }

    public short getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(short idCategorie) {
        this.idCategorie = idCategorie;
    }

    public short getIdType() {
        return idType;
    }

    public void setIdType(short idType) {
        this.idType = idType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idCategorie;
        hash += (int) idType;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriePK)) {
            return false;
        }
        CategoriePK other = (CategoriePK) object;
        if (this.idCategorie != other.idCategorie) {
            return false;
        }
        if (this.idType != other.idType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CategoriePK[ idCategorie=" + idCategorie + ", idType=" + idType + " ]";
    }
    
}
