/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Destination;
import facades.DestinationFacade;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author nabih
 */
@ManagedBean
@ViewScoped
public class DestinationManagedBean implements Serializable{
    
    @EJB
    private DestinationFacade destinationFacade;
    private Destination destination = new Destination();
    private Collection<Destination> destinations;

    public Collection<Destination> getDestinations() {
        return destinationFacade.findAll();
    }

    public void setDestinations(Collection<Destination> destinations) {
        this.destinations = destinations;
    }

    
    
    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
    
    public void ajouterDestination(){
        System.out.println("i am adding");
        destinationFacade.create(destination);
    }

 
    /**
     * Creates a new instance of DestinationManagedBean
     */
    public DestinationManagedBean() {
    }
    
}
