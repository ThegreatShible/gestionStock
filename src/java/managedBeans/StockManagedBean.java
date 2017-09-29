
package managedBeans;

import entities.Produit;
import entities.Type;
import facades.ProduitFacade;
import facades.TypeFacade;
import java.io.Serializable;
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
import models.LigneStock;

/**
 *
 * @author nabih
 */
@ManagedBean
@ViewScoped
public class StockManagedBean implements Serializable{

    @EJB
    ProduitFacade produitFacade;
    @EJB
    TypeFacade typeFacade;
    
    private String privilege;
    private Collection<Produit> produits;
    private Collection<Produit> filtredValues = new ArrayList();
    private String headerPath;

    public String getHeaderPath() {
        return headerPath;
    }

    public void setHeaderPath(String headerPath) {
        this.headerPath = headerPath;
    }
    
    
    
    public Collection<Produit> getProduits() {
        if (privilege.equals("ADMINISTRATEUR")) {
            return produitFacade.findAll();
        } else {
            return produitFacade.getParType(privilege);
        }
    }

    public void setProduits(Collection<Produit> produits) {
        this.produits = produits;
    }
    
  private List<LigneStock> lignes;
    public StockManagedBean() {
    }

    public List<LigneStock> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneStock> lignes) {
        this.lignes = lignes;
    }
    
      @PostConstruct
    public void init() {

       /* HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        String privilege = request.getParameter("privilege");*/
       

         if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("AdminRole")){
            privilege = "ADMINISTRATEUR";
            setHeaderPath("/template/common/commonAdminLayout.xhtml" );
        }else{
            String name = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            Type t = typeFacade.getParEmailUtilisateur(name);
            privilege  = t.getNomType();
            setHeaderPath("/template/common/commonLayout.xhtml" );
        }

    }

    public Collection<Produit> getFiltredValues() {
        return filtredValues;
    }

    public void setFiltredValues(Collection<Produit> filtredValues) {
        this.filtredValues = filtredValues;
    }
    
}
