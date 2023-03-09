/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modeles.Livraison;
import utils.DataSource;

/**
 *
 * @author mrram
 */
public class ServiceLivraison {
    Connection cnx = DataSource.getInstance().getCnx();

    
    public void add(Livraison livraison) {

        try {
//            String req0 = "INSERT INTO `livraison`(`nom`, `prenom`, `tel`, `email`, `pwd`, `carte_banq`) VALUES (?,?,?,?,?,?)";

            String req = " INSERT INTO `livraison`(`id`,`id_livraison`,`prixTotal`,`status`,`destination`,`methodePaiment`,`date`) VALUES(?,?,?,?,?,?,?)";


            PreparedStatement ps = cnx.prepareStatement(req);
            // ps.setString(1, livraison.getDestination());
        
            ps.setInt(1,livraison.id);
            ps.setInt( 2,livraison.id_user);
            ps.setInt(3, (int) livraison.prixTotal);
            ps.setBoolean( 4,livraison.status);
            ps.setString( 5,livraison.destination);
            ps.setString( 6,livraison.methodePaiment);
            ps.setString( 7,livraison.date);

         

            ps.executeUpdate();
            System.out.println("livraison Ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public Object getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List getAll() {
        List<Livraison> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `livraison`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Livraison u = new Livraison(
                    
                rs.getInt(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getBoolean(4),
                rs.getString(5),
                rs.getString(6),
                rs.getString(7)
                );
                list.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    
    public boolean update(Object u) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public boolean delete(Object u) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
