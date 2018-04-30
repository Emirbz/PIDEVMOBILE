/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

import java.util.Date;

/**
 *
 * @author dahem
 */
public class Events {
  private int id;
  private int iduser;
  private String name;
  private String description;
  private Date dateEvenement;
  private String devis_name;
  private int nbrplace;
  private String adresse;
  private String adressemail;
  private int numTel;
  private String adressefacebook;
  private String adressetwitter;
  private boolean parking;
  private boolean fumer;
  private boolean wifi;
  private boolean EspaceEenfant;
  private boolean ascenceur;
  private boolean Cartebancaire;
  private boolean Espacefamilial;
  private String image;
  private String image1;
  private String image2;
  

    public Events() {
    }
  
  public Events(int id, String name, String description, Date dateEvenement, String devis_name, int nbrplace, String adresse, String adressemail, int numTel, String adressefacebook, String adressetwitter, boolean parking, boolean fumer, boolean wifi, boolean EspaceEenfant, boolean ascenceur, boolean Cartebancaire, boolean Espacefamilial, String image, String image1, String image2 ) {
     this.id = id;
     this.name = name;
     this.description = description;
     this.dateEvenement = dateEvenement;
     this.devis_name = devis_name;
     this.nbrplace = nbrplace;
     this.adresse = adresse;
     this.adressemail = adressemail;
     this.numTel = numTel;
     this.adressefacebook = adressefacebook;
     this.adressetwitter = adressetwitter;
     this.parking = parking; 
     this.fumer = fumer;
     this.wifi = wifi;
     this.EspaceEenfant = EspaceEenfant;
     this.ascenceur = ascenceur;
     this.Cartebancaire = Cartebancaire;
     this.Espacefamilial = Espacefamilial;
     this.image = image;
     this.image1 = image1;
     this.image2 = image2;
     
     
     
     
  }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getDevis_name() {
        return devis_name;
    }

    public void setDevis_name(String devis_name) {
        this.devis_name = devis_name;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdressemail() {
        return adressemail;
    }

    public void setAdressemail(String adressemail) {
        this.adressemail = adressemail;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getAdressefacebook() {
        return adressefacebook;
    }

    public void setAdressefacebook(String adressefacebook) {
        this.adressefacebook = adressefacebook;
    }

    public String getAdressetwitter() {
        return adressetwitter;
    }

    public void setAdressetwitter(String adressetwitter) {
        this.adressetwitter = adressetwitter;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isFumer() {
        return fumer;
    }

    public void setFumer(boolean fumer) {
        this.fumer = fumer;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isEspaceEenfant() {
        return EspaceEenfant;
    }

    public void setEspaceEenfant(boolean EspaceEenfant) {
        this.EspaceEenfant = EspaceEenfant;
    }

    public boolean isAscenceur() {
        return ascenceur;
    }

    public void setAscenceur(boolean ascenceur) {
        this.ascenceur = ascenceur;
    }

    public boolean isCartebancaire() {
        return Cartebancaire;
    }

    public void setCartebancaire(boolean Cartebancaire) {
        this.Cartebancaire = Cartebancaire;
    }

    public boolean isEspacefamilial() {
        return Espacefamilial;
    }

    public void setEspacefamilial(boolean Espacefamilial) {
        this.Espacefamilial = Espacefamilial;
    }
  @Override
    public String toString() {
        return name;
  
  
}
    }
  





  
  
     
    
    

