/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

import Test.SendEmail;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ServiceUser;

/**
 *
 * @author Faty
 */
public class User {

    protected int id;
    protected String nom;
    protected String prenom;
    protected int phone;
    protected String email;
    protected String pwd;
    protected String carte_banq;
    protected Role role;

    ServiceUser sU = new ServiceUser();

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public void resetPassword() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();

        SendEmail mail = new SendEmail("sa", "Password reset", "this is your token \n" + token);
        System.out.println("Donner le token");
        Scanner in = new Scanner(System.in);
        String sent = in.nextLine();
        if (sent.equals(token)) {
            System.out.println("Verifier");
            System.out.println("Donner le nouveaux mot de pass");
            String mdp = in.nextLine();
            this.pwd = mdp;
            sU.update(this);
        } else {
            System.out.println("Non Verifier");
        }
    }

    public boolean Login(String mail, String password) throws NoSuchAlgorithmException {

        User u = sU.getByMail(mail);
        // System.out.println(crypPassword(password).length());
        return crypPassword(password).equals(u.getPwd());
    }

    public User(int id, String nom, String prenom, int phone, String email, String pwd, String carte_banq) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.email = email;
        this.pwd = pwd;
        this.carte_banq = carte_banq;
        this.role = Role.empty;
    }

    public User(String nom, String prenom, int phone, String email, String pwd, String carte_banq) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.email = email;
        this.pwd = pwd;
        this.carte_banq = carte_banq;
        this.role = Role.empty;
    }

    @Override
    public String toString() {
        return "{"
                + " id='" + getId() + "'"
                + ", nom='" + getNom() + "'"
                + ", prenom='" + getPrenom() + "'"
                + ", phone='" + getPhone() + "'"
                + ", email='" + getEmail() + "'"
                + ", pwd='" + getPwd() + "'"
                + ", carte_banq='" + getCarte_banq() + "'"
                + ", role='" + getRole() + "'"
                + "}";
    }

    // public hashing()
    public static String crypPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encoded = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        byte[] hash = encoded;
        // System.out.println(encoded);
        // to hex
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        String haja = encoded.toString();
        haja = haja.substring(2, haja.length());
        // return haja;
        return hexString.toString();
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getPhone() {
        return this.phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCarte_banq() {
        return this.carte_banq;
    }

    public void setCarte_banq(String carte_banq) {
        this.carte_banq = carte_banq;
    }

}
