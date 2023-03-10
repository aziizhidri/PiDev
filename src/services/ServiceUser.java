/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import modeles.User;
import utils.DataSource;

/**
 *
 * @author Faty
 */
public class ServiceUser implements IService<User> {

    Connection cnx = DataSource.getInstance().getCnx();

    public List<User> SortBy(String Column) {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user` order by " + Column;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getString(7));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public List<User> Search(String Column, String value) {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user` WHERE " + Column + " = " + value;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getString(7));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public void add(User u) {
        try {
            String req = "INSERT INTO `user`(`nom`, `prenom`, `tel`, `email`, `pwd`, `carte_banq`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setInt(3, u.getPhone());
            ps.setString(4, u.getEmail());
            try {
                ps.setString(5, u.crypPassword(u.getPwd()));
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ps.setString(6, u.getCarte_banq());

            ps.executeUpdate();
            System.out.println("User Ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public User getById(int id) {
        User u = new User();
        try {
            String req = "SELECT * FROM `user` where `id` = " + id;
            // Statement st = cnx.createStatement();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                User us = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getString(7));
                u = us;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return u;
        // return null;
    }

    public User getByMail(String mail) {
        User u = new User();
        try {
            // String req = "SELECT * FROM `user` WHERE `email` = " + mail;

            String req = "SELECT * FROM `user` WHERE email = '" + mail + "'";
            Statement st = cnx.createStatement();

            // String req = "SELECT * FROM `user` WHERE email = ? ";
            // PreparedStatement ps = cnx.prepareStatement(req);
            // ps.setString(1, mail);
            System.out.println(req);
            // System.out.println("*******");
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                User us = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getString(7));
                u = us;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return u;
        // return null;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getString(7));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public boolean update(User u) {
        System.out.println(u);
        String req = "update user set nom = ? , prenom = ? , tel =? , email = ? , pwd = ? , carte_banq = ?  where id = ? ";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(7, u.getId());
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setInt(3, u.getPhone());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPwd());
            ps.setString(6, u.getCarte_banq());

            ps.executeUpdate();
            System.out.println("User modifier");
            ps.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(User u) {
        String req = "delete from user where id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, u.getId());
            ps.executeUpdate();
            System.out.println("User supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

}
