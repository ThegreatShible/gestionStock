/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author nabih
 */
public enum TransactionType {
    ENTREE("ENTREE"),
    SORTIE("SORTIE"),
    REINTEGRATION("REINTEGRATION");
    
    private String type;
    TransactionType(String type){
        this.type = type;
    }
    
    public String toString(){
        return type;
    }
    
}
