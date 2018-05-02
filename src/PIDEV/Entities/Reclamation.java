/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

/**
 *
 * @author Skan
 */
public class Reclamation  {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String contenu;
    private String typeobj;
    private int idobj;
    private String etat;
    private User iduser;

    public Reclamation() {
    }

    public Reclamation(Integer id) {
        this.id = id;
    }

    public Reclamation(Integer id, String contenu, String typeobj, int idobj, String etat) {
        this.id = id;
        this.contenu = contenu;
        this.typeobj = typeobj;
        this.idobj = idobj;
        this.etat = etat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getTypeobj() {
        return typeobj;
    }

    public void setTypeobj(String typeobj) {
        this.typeobj = typeobj;
    }

    public int getIdobj() {
        return idobj;
    }

    public void setIdobj(int idobj) {
        this.idobj = idobj;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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
        if (!(object instanceof Reclamation)) {
            return false;
        }
        Reclamation other = (Reclamation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PIDEV.Entities.Reclamation[ id=" + id + " ]";
    }
    
}
