/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import com.sun.istack.Nullable;
import entities.LigneCommande;
import entities.Transactions;
import entities.Type;
import entities.Utilisateurs;
import facades.LigneCommandeFacade;
import facades.TransactionsFacade;
import facades.TypeFacade;
import facades.UtilisateursFacade;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author nabih
 */
@ManagedBean
@ViewScoped
public class TrafficManagedBean implements Serializable {

    @EJB
    UtilisateursFacade utilisateurFacade;
    @EJB
    TransactionsFacade transactionFacade;
    @EJB
    TypeFacade typeFacade;
    @EJB
    LigneCommandeFacade ligneCommandeFacade;

    private Collection<Transactions> transactions = new ArrayList();
    private java.util.Date de;
    private java.util.Date a;
    private Collection<Type> types;
    private String nomType;
    private String typeTransaction;
    private ArrayList<String> typesTransaction = new ArrayList();
    private Transactions transaction;
    private Collection<LigneCommande> lignesCommande;
    private String headerPath;

    public String getHeaderPath() {
        return headerPath;
    }

    public void setHeaderPath(String headerPath) {
        this.headerPath = headerPath;
    }

    public Transactions getTransaction() {
        return transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;

    }

    public Collection<LigneCommande> getLignesCommande() {
        if (transaction != null) {

            return ligneCommandeFacade.getParIdTransaction(transaction.getIdTransaction());
        } else {
            return new ArrayList();
        }
    }

    public void setLignesCommande(Collection<LigneCommande> lignesCommande) {
        this.lignesCommande = lignesCommande;

    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    private String privilege;

    public java.util.Date getDe() {
        return de;
    }

    public void setDe(java.util.Date de) {
        System.out.println("dddddddddddddddddddddddddddd");
        this.de = de;

    }

    public java.util.Date getA() {
        return a;
    }

    public void setA(java.util.Date a) {
        this.a = a;

    }

    public Collection<Type> getTypes() {
        System.out.println("typpppes de transaction");
        if (privilege.equals("ADMINISTRATEUR")) {
            System.out.println("admin transaction");
            return typeFacade.findAll();
        } else {
            System.out.println("not admin");
            Collection<Type> l = new ArrayList();
            l.add(typeFacade.getParNom(nomType));
            return l;
        }
    }

    public void setTypes(Collection<Type> types) {

        this.types = types;
    }

    public Collection<Transactions> getTransactions() {

        return transactionFacade.geAvecFiltre(nomType, typeTransaction, de, a);

    }

    public void setTransactions(Collection<Transactions> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<String> getTypesTransaction() {
        return typesTransaction;
    }

    public void setTypesTransaction(ArrayList<String> typesTransaction) {
        this.typesTransaction = typesTransaction;
    }

    @PostConstruct
    public void init() {

        /* HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        String privilege = request.getParameter("privilege");*/
        typesTransaction.add("tous");
        typesTransaction.add("ENTREE");
        typesTransaction.add("SORTIE");

        if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("AdminRole")) {
            privilege = "ADMINISTRATEUR";
            setHeaderPath("/template/common/commonAdminLayout.xhtml");
        } else {
            String name = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            Type t = typeFacade.getParEmailUtilisateur(name);
            privilege = t.getNomType();
            setHeaderPath("/template/common/commonLayout.xhtml");
        }

        if (!privilege.equals("ADMINISTRATEUR")) {
            nomType = privilege;
        } else {
            nomType = "ALL";
        }
        typeTransaction = "tous";

    }

}
