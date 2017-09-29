/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Type;
import facades.TypeFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author nabih
 */
@ManagedBean
public class TypeManagedBean implements Serializable{
    
    @EJB
    private TypeFacade facade;
    
 
    public TypeFacade getFacade() {
        return facade;
    }

    public void setFacade(TypeFacade facade) {
        this.facade = facade;
    }

    
 

    private List<Type> types = new ArrayList();
    private Type type = new Type();
    
    
    public List<Type> getTypes() {
        return facade.findAll();
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

 

    public TypeManagedBean() {
    }
    
    

    public void addType() {
        System.out.println(type.getNomType());
       type.setIdType(Short.MIN_VALUE);
        facade.create(type);
    }

}
