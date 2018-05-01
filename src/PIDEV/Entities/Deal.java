/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

import java.util.Date;

/**
 *
 * @author Skan
 */
public class Deal {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nom;
    private double oldprix;
    private double promotion;
    private String description;
    private Date datecreation;
    private Date datefin;
    private Double newprix;
    private String devisName;
    private Date updatedAt;
    private Integer rating;
    private String region;
    private String adresse;
    private int placesdispo;
    private Catdeal cat;
    private User iduser;

    public Deal() {
    }

    public Deal(Integer id) {
        this.id = id;
    }

    public Deal(Integer id, String nom, double oldprix, double promotion, String description, Date datecreation, Date datefin, String region, String adresse, int placesdispo) {
        this.id = id;
        this.nom = nom;
        this.oldprix = oldprix;
        this.promotion = promotion;
        this.description = description;
        this.datecreation = datecreation;
        this.datefin = datefin;
        this.region = region;
        this.adresse = adresse;
        this.placesdispo = placesdispo;
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

    public double getOldprix() {
        return oldprix;
    }

    public void setOldprix(double oldprix) {
        this.oldprix = oldprix;
    }

    public double getPromotion() {
        return promotion;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public Double getNewprix() {
        return newprix;
    }

    public void setNewprix(Double newprix) {
        this.newprix = newprix;
    }

    public String getDevisName() {
        return devisName;
    }

    public void setDevisName(String devisName) {
        this.devisName = devisName;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getPlacesdispo() {
        return placesdispo;
    }

    public void setPlacesdispo(int placesdispo) {
        this.placesdispo = placesdispo;
    }

    public Catdeal getCat() {
        return cat;
    }

    public void setCat(Catdeal cat) {
        this.cat = cat;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deal)) {
            return false;
        }
        Deal other = (Deal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nom;
    }
    
}
