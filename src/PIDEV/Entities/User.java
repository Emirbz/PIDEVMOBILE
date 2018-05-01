/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIDEV.Entities;
import java.util.Date;
/**
 *
 * @author ons
 */
public class User {
     private int id;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private boolean enabled;
    private String salt;
    private String password;
    private Date last_login;
    private String confirmation_token;
    private Date password_requested_at;
    private String roles;
    private String name;
    private String surname;
    private String phone;
    private String role;
    private String facebook;
    private String address;
    private String devis_name;
    private Date date;

    public User ( String username, String email,  String password, String name, String surname, String phone, String facebook, String address, String devis_name, String role) {
        
        this.username = username;
       
        this.email = email;
        
        this.password = password;
        
        this.name = name;
        this.surname = surname;
        this.phone = phone;
       
        this.facebook = facebook;
        this.address = address;
        this.devis_name = devis_name;
        this.role = role;
    }
    
    public User(){
        
    }
    
    //GETTERS
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getAddress() {
        return address;
    }

    public String getDevis_name() {
        return devis_name;
    }

    public Date getDate() {
        return date;
    }

    
    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDevis_name(String devis_name) {
        this.devis_name = devis_name;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    

    
}
