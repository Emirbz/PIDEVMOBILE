/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

import java.io.Serializable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 *
 * @author Emir
 */

public class SousCategorie implements Serializable {

  
    private Integer id;
 
    private String nom;
    @JoinColumn(name = "idcategorie", referencedColumnName = "id")
    @ManyToOne
    private Categorie idcategorie;
   
    public SousCategorie() {
    }

    public SousCategorie(Integer id) {
        this.id = id;
    }

    public SousCategorie(Integer id, String nom,Categorie idcategorie) {
        this.id = id;
        this.nom = nom;
        this.idcategorie=idcategorie;
       
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categorie getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Categorie idcategorie) {
        this.idcategorie = idcategorie;
    }

   

    

    @Override
    public String toString() {
        return nom;
    }
    
}
