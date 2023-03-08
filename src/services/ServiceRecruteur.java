package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modeles.Recruteur;
import modeles.User;
import utils.DataSource;

public class ServiceRecruteur implements IService {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void add(Object u) {
        Recruteur e = (Recruteur) u;
        try {
            String req = "INSERT INTO `Recruteur`(`nom`, `prenom`, `tel`, `email`, `pwd`, `carte_banq`,`role` ,`societe`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setInt(3, e.getPhone());
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getPwd());
            ps.setString(6, e.getCarte_banq());
            ps.setString(7, e.getRole().toString());
            ps.setString(8, e.getsociete());

            ps.executeUpdate();
            System.out.println("Recruteur Ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        ServiceUser sU = new ServiceUser();
        sU.add(e);

    }

    @Override
    public Object getById(int id) {
        Recruteur u = new Recruteur();
        try {
            String req = "SELECT * FROM `Recruteur` where id = " + id;
            // Statement st = cnx.createStatement();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Recruteur us = new Recruteur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8));
                u = us;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return u;
    }

    @Override
    public List getAll() {
        List<Recruteur> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `recruteur`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Recruteur e = new Recruteur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(9));
                list.add(e);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public boolean update(Object u) {
        try {
            String req = "update `etudiant` set nom = ?, prenom = ?, phone = ?, email = ?, pwd = ?, carte_banq = ?, universite = ? where   id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            Recruteur e = (Recruteur) u;

            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setInt(3, e.getPhone());
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getPwd());
            ps.setString(6, e.getCarte_banq());
            ps.setString(7, e.getRole().toString());
            ps.setString(8, e.getsociete());
            ps.setInt(9, e.getId());
            ServiceUser sU = new ServiceUser();
            sU.update(e);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Object u) {
        Recruteur e = (Recruteur) u;

        String req = "delete from Recruteur where id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
            System.out.println("Recruteur supprimer");
            ServiceUser sU = new ServiceUser();
            sU.delete(e);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
