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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nabih
 */
@Embeddable
public class EtatStockPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "moment")
    @Temporal(TemporalType.TIMESTAMP)
    private Date moment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idProduit")
    private int idProduit;

    public EtatStockPK() {
    }

    public EtatStockPK(Date moment, int idProduit) {
        this.moment = moment;
        this.idProduit = idProduit;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moment != null ? moment.hashCode() : 0);
        hash += (int) idProduit;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EtatStockPK)) {
            return false;
        }
        EtatStockPK other = (EtatStockPK) object;
        if ((this.moment == null && other.moment != null) || (this.moment != null && !this.moment.equals(other.moment))) {
            return false;
        }
        if (this.idProduit != other.idProduit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EtatStockPK[ moment=" + moment + ", idProduit=" + idProduit + " ]";
    }
    
}
