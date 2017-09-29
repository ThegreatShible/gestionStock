/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "Destination")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Destination.findAll", query = "SELECT d FROM Destination d")
    , @NamedQuery(name = "Destination.findByIdDestination", query = "SELECT d FROM Destination d WHERE d.idDestination = :idDestination")
    , @NamedQuery(name = "Destination.findByNomDestination", query = "SELECT d FROM Destination d WHERE d.nomDestination = :nomDestination")
    , @NamedQuery(name = "Destination.findByAdresse", query = "SELECT d FROM Destination d WHERE d.adresse = :adresse")})
public class Destination implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idDestination")
    private Short idDestination = Short.MIN_VALUE;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nomDestination")
    private String nomDestination;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "adresse")
    private String adresse;
    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY)
    private Collection<Transactions> transactionsCollection;

    public Destination() {
    }

    public Destination(Short idDestination) {
        this.idDestination = idDestination;
    }

    public Destination(Short idDestination, String nomDestination, String adresse) {
        this.idDestination = idDestination;
        this.nomDestination = nomDestination;
        this.adresse = adresse;
    }

    public Short getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Short idDestination) {
        this.idDestination = idDestination;
    }

    public String getNomDestination() {
        return nomDestination;
    }

    public void setNomDestination(String nomDestination) {
        this.nomDestination = nomDestination;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @XmlTransient
    public Collection<Transactions> getTransactionsCollection() {
        return transactionsCollection;
    }

    public void setTransactionsCollection(Collection<Transactions> transactionsCollection) {
        this.transactionsCollection = transactionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDestination != null ? idDestination.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destination)) {
            return false;
        }
        Destination other = (Destination) object;
        if ((this.idDestination == null && other.idDestination != null) || (this.idDestination != null && !this.idDestination.equals(other.idDestination))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Destination[ idDestination=" + idDestination + " ]";
    }
    
}
