/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Categorie;

/**
 *
 * @author nabih
 */
public class CategorieModel {
    private String nomCategorie;
    private String nomType;

    public CategorieModel(String nomCategorie, String nomType) {
        this.nomCategorie = nomCategorie;
        this.nomType = nomType;
    }
    
}
