/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;


import java.util.Collection;
import java.util.Date;


/**
 *
 * @author Skan
 */
public class Catdeal {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nom;
    private String devisName;
    private Date updatedAt;
    private Collection<Deal> dealCollection;

    public Catdeal() {
    }

    public Catdeal(Integer id) {
        this.id = id;
    }

    public Catdeal(Integer id, String nom, String devisName, Date updatedAt) {
        this.id = id;
        this.nom = nom;
        this.devisName = devisName;
        this.updatedAt = updatedAt;
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

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catdeal)) {
            return false;
        }
        Catdeal other = (Catdeal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Catdeal[ id=" + id + " ]";
    }
    
}

