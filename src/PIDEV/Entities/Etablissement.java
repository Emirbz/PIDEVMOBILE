/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Emir
 */
public class Etablissement {
      @JoinColumn(name = "souscat", referencedColumnName = "id")
    @ManyToOne
    private SousCategorie souscat;

   private Integer id;
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    @ManyToOne
    private User iduser;

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private String etat;

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    private String name;
    private String address;
    private String description;
    private String phone;
    private String email;
    private String website;
    private String facebook;
    private String categorie;
    private String lundisamedio;
    private String lundisamedif;
    private String dimancheo;
    private String dimanchef;
    private boolean parking;
    private boolean cartecredit;
    private boolean chaiseroulante;
     private boolean fumer;
    private boolean terasse;
    private boolean wifi;
    private boolean reservations;
    private int etoile;
    private int nbrchambre;
    private int place;
    private String checkin;
    private String checkout;
    private boolean lpd;
    private boolean dp;
    private boolean pc;
    private boolean allinclusive;
    private boolean livraison;
    private boolean climatisation;
    private boolean animaux;
    private boolean alcool;
    private int prixmoy;
    private String img1;
    private String img2;
    private String img3;
    private String devis_name;
      
    private Double moyservice;
    
    private Double moyqualite;
   
    private Double totalqualite;

    public Double getMoyservice() {
        return moyservice;
    }

    public void setMoyservice(Double moyservice) {
        this.moyservice = moyservice;
    }

    public Double getMoyqualite() {
        return moyqualite;
    }

    public void setMoyqualite(Double moyqualite) {
        this.moyqualite = moyqualite;
    }

    public Double getTotalqualite() {
        return totalqualite;
    }

    public void setTotalqualite(Double totalqualite) {
        this.totalqualite = totalqualite;
    }

    public Double getTotalservice() {
        return totalservice;
    }

    public void setTotalservice(Double totalservice) {
        this.totalservice = totalservice;
    }
   
    private Double totalservice;

    public String getDevis_name() {
        return devis_name;
    }

    public void setDevis_name(String devis_name) {
        this.devis_name = devis_name;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }
    

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public Etablissement() {
    }
     public Etablissement(SousCategorie souscat,String name, String address, String description, String phone, String email, String website, String facebook, String categorie, String lundisamedio, String lundisamedif, String dimancheo, String dimanchef, boolean parking, boolean cartecredit, boolean chaiseroulante, boolean fumer, boolean terasse, boolean wifi, boolean reservations, int place, boolean livraison, boolean climatisation, boolean animaux, boolean alcool) {
      this.souscat= souscat;
        this.name = name;
        this.address = address;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.facebook = facebook;
        this.categorie = categorie;
        this.lundisamedio = lundisamedio;
        this.lundisamedif = lundisamedif;
        this.dimancheo = dimancheo;
        this.dimanchef = dimanchef;
        this.parking = parking;
        this.cartecredit = cartecredit;
        this.chaiseroulante = chaiseroulante;
        this.fumer = fumer;
        this.terasse = terasse;
        this.wifi = wifi;
        this.reservations = reservations;
        this.place = place;
        this.livraison = livraison;
        this.climatisation = climatisation;
        this.animaux = animaux;
        this.alcool = alcool;
     }
        

    public Etablissement(SousCategorie souscat,String name, String address, String description, String phone, String email, String website, String facebook, String categorie, String lundisamedio, String lundisamedif, String dimancheo, String dimanchef, boolean parking, boolean cartecredit, boolean chaiseroulante, boolean fumer, boolean terasse, boolean wifi, boolean reservations, int place, boolean livraison, boolean climatisation, boolean animaux, boolean alcool,String img1,String img2,String img3,String devis_name,Double latitude,Double longitude,User iduser) {
      this.souscat= souscat;
        this.name = name;
        this.address = address;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.facebook = facebook;
        this.categorie = categorie;
        this.lundisamedio = lundisamedio;
        this.lundisamedif = lundisamedif;
        this.dimancheo = dimancheo;
        this.dimanchef = dimanchef;
        this.parking = parking;
        this.cartecredit = cartecredit;
        this.chaiseroulante = chaiseroulante;
        this.fumer = fumer;
        this.terasse = terasse;
        this.wifi = wifi;
        this.reservations = reservations;
        this.place = place;
        this.livraison = livraison;
        this.climatisation = climatisation;
        this.animaux = animaux;
        this.alcool = alcool;
        this.img1= img1;
        this.img2=img2;
        this.img3=img3;
        this.devis_name=devis_name;
        this.latitude=latitude;
        this.longitude=longitude;
        this.iduser=iduser;
        
    }
     public Etablissement(SousCategorie souscat,String name, String address, String description, String phone, String email, String website, String facebook, String categorie, boolean parking, boolean cartecredit, boolean chaiseroulante, boolean fumer, boolean terasse, boolean wifi, boolean reservations,  boolean livraison, boolean climatisation, boolean animaux, boolean alcool,String img1,String img2,String img3,String devis_name,Double latitude,Double longitude,User iduser,boolean lpd,boolean dp,boolean pc,boolean allinclusive,int prixmoy,int nbrchambre,int etoile,String checkin,String checkout) {
      this.souscat= souscat;
        this.name = name;
        this.address = address;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.facebook = facebook;
        this.categorie = categorie;
     
        this.parking = parking;
        this.cartecredit = cartecredit;
        this.chaiseroulante = chaiseroulante;
        this.fumer = fumer;
        this.terasse = terasse;
        this.wifi = wifi;
        this.reservations = reservations;
    
        this.livraison = livraison;
        this.climatisation = climatisation;
        this.animaux = animaux;
        this.alcool = alcool;
        this.img1= img1;
        this.img2=img2;
        this.img3=img3;
        this.devis_name=devis_name;
        this.latitude=latitude;
        this.longitude=longitude;
        this.iduser=iduser;
        this.allinclusive=allinclusive;
        this.dp=dp;
        this.pc=pc;
        this.lpd=lpd;
        this.etoile=etoile;
        this.nbrchambre=nbrchambre;
        this.checkin=checkin;
        this.checkout=checkout;
        this.prixmoy=prixmoy;
        
    }
  
    public Etablissement(String name , String address,String description,String phone,String email)
    {
        this.name=name;
        this.address=address;
        this.description=description;
        this.phone=phone;
        this.email=email;
    }

    @Override
    public String toString() {
        return "etablissement{" + "souscat=" + souscat + ", id=" + id + ", name=" + name + ", address=" + address + ", description=" + description + ", phone=" + phone + ", email=" + email + ", website=" + website + ", facebook=" + facebook + ", categorie=" + categorie + ", lundisamedio=" + lundisamedio + ", lundisamedif=" + lundisamedif + ", dimancheo=" + dimancheo + ", dimanchef=" + dimanchef + ", parking=" + parking + ", cartecredit=" + cartecredit + ", chaiseroulante=" + chaiseroulante + ", fumer=" + fumer + ", terasse=" + terasse + ", wifi=" + wifi + ", reservations=" + reservations + ", etoile=" + etoile + ", nbrchambre=" + nbrchambre + ", place=" + place + ", checkin=" + checkin + ", checkout=" + checkout + ", lpd=" + lpd + ", dp=" + dp + ", pc=" + pc + ", allinclusive=" + allinclusive + ", livraison=" + livraison + ", climatisation=" + climatisation + ", animaux=" + animaux + ", alcool=" + alcool + ", prixmoy=" + prixmoy + '}';
    }
    public SousCategorie getSouscat() {
        return souscat;
    }

    public void setSouscat(SousCategorie souscat) {
        this.souscat = souscat;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getLundisamedio() {
        return lundisamedio;
    }

    public void setLundisamedio(String lundisamedio) {
        this.lundisamedio = lundisamedio;
    }

    public String getLundisamedif() {
        return lundisamedif;
    }

    public void setLundisamedif(String lundisamedif) {
        this.lundisamedif = lundisamedif;
    }

    public String getDimancheo() {
        return dimancheo;
    }

    public void setDimancheo(String dimancheo) {
        this.dimancheo = dimancheo;
    }

    public String getDimanchef() {
        return dimanchef;
    }

    public void setDimanchef(String dimanchef) {
        this.dimanchef = dimanchef;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isCartecredit() {
        return cartecredit;
    }

    public void setCartecredit(boolean cartecredit) {
        this.cartecredit = cartecredit;
    }

    public boolean isChaiseroulante() {
        return chaiseroulante;
    }

    public void setChaiseroulante(boolean chaiseroulante) {
        this.chaiseroulante = chaiseroulante;
    }

    public boolean isFumer() {
        return fumer;
    }

    public void setFumer(boolean fumer) {
        this.fumer = fumer;
    }

    public boolean isTerasse() {
        return terasse;
    }

    public void setTerasse(boolean terasse) {
        this.terasse = terasse;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isReservations() {
        return reservations;
    }

    public void setReservations(boolean reservations) {
        this.reservations = reservations;
    }

    public int getEtoile() {
        return etoile;
    }

    public void setEtoile(int etoile) {
        this.etoile = etoile;
    }

    public int getNbrchambre() {
        return nbrchambre;
    }

    public void setNbrchambre(int nbrchambre) {
        this.nbrchambre = nbrchambre;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public boolean isLpd() {
        return lpd;
    }

    public void setLpd(boolean lpd) {
        this.lpd = lpd;
    }

    public boolean isDp() {
        return dp;
    }

    public void setDp(boolean dp) {
        this.dp = dp;
    }

    public boolean isPc() {
        return pc;
    }

    public void setPc(boolean pc) {
        this.pc = pc;
    }

    public boolean isAllinclusive() {
        return allinclusive;
    }

    public void setAllinclusive(boolean allinclusive) {
        this.allinclusive = allinclusive;
    }

    public boolean isLivraison() {
        return livraison;
    }

    public void setLivraison(boolean livraison) {
        this.livraison = livraison;
    }

    public boolean isClimatisation() {
        return climatisation;
    }

    public void setClimatisation(boolean climatisation) {
        this.climatisation = climatisation;
    }

    public boolean isAnimaux() {
        return animaux;
    }

    public void setAnimaux(boolean animaux) {
        this.animaux = animaux;
    }

    public boolean isAlcool() {
        return alcool;
    }

    public void setAlcool(boolean alcool) {
        this.alcool = alcool;
    }

    public int getPrixmoy() {
        return prixmoy;
    }

    public void setPrixmoy(int prixmoy) {
        this.prixmoy = prixmoy;
    }
   

}
