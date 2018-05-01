/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

import com.codename1.ui.Calendar;
import java.util.Date;








/**
 *
 * @author ons
 */
public class Reservation {
    private int id;
    private Etablissement etablissement;
    private User user;
    private String aunomde;
    private int nombre;
    private String description;
    private String date;

    public Reservation(User user,Etablissement etablissement,String aunomde, int nombre, String description, String date) {
       this.user=user;
        this.etablissement = etablissement;
        this.aunomde = aunomde;
        this.nombre = nombre;
        this.description = description;
        this.date = date;
    }
    
    public Reservation(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAunomde() {
        return aunomde;
    }

    public void setAunomde(String aunomde) {
        this.aunomde = aunomde;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
