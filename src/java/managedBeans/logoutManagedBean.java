/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author nabih
 */
@ManagedBean
public class logoutManagedBean {

    /**
     * Creates a new instance of logoutManagedBean
     */
    public logoutManagedBean() {
    }
    
    public String redirect(){
        logout();
        return "/bothPages/traffic.xhtml?faces-redirect=true";
    }
    
    private void logout(){
         FacesContext context = FacesContext.getCurrentInstance();
         HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            
            Logger.getLogger(logoutManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
    
}
