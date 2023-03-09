/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeles;

/**
 *
 * @author mrram
 */
public class Livraison {
    public int id;
    public int id_user;
    public float prixTotal;
    public boolean status;
    public String destination;
    public String methodePaiment;
    public String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livraison(){
        
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", id_user=" + id_user + ", prixTotal=" + prixTotal + ", status=" + status + ", destination=" + destination + ", methodePaiment=" + methodePaiment + ", date=" + date + '}';
    }
    
    public Livraison(int id, int id_user, float prixTotal, boolean status, String destination, String methodePaiment, String date) {
        this.id = id;
        this.id_user = id_user;
        this.prixTotal = prixTotal;
        this.status = status;
        this.destination = destination;
        this.methodePaiment = methodePaiment;
        this.date = date;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMethodePaiment() {
        return methodePaiment;
    }

    public void setMethodePaiment(String methodePaiment) {
        this.methodePaiment = methodePaiment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    

}
