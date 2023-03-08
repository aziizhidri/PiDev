package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modeles.Cour;
import modeles.Etudiant;
import modeles.User;
import utils.DataSource;

public class ServiceEtudiant implements IService {

    Connection cnx = DataSource.getInstance().getCnx();

    public List<Cour> addCours(Etudiant e) {
        List<Cour> cours = new ArrayList<>();
        try {
            String req = "SELECT * FROM `cour` where id = " + e.getId();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Cour c = new Cour(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
                cours.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return cours;
    }

    public void add(User u) {
        Etudiant e = (Etudiant) u;
        try {
            String req = "INSERT INTO `etudiant`(`nom`, `prenom`, `tel`, `email`, `pwd`, `carte_banq`,`role`,`section`,`niveau`,`score`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setInt(3, u.getPhone());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPwd());
            ps.setString(6, u.getCarte_banq());
            ps.setString(7, e.getRole().toString());
            ps.setString(8, e.getSection());
            ps.setInt(9, e.getNiveau());
            ps.setInt(10, e.getScore());

            ps.executeUpdate();
            System.out.println("Etudiant Ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        ServiceUser sU = new ServiceUser();
        sU.add(u);

    }

    @Override
    public Object getById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List getAll() {
        List<Etudiant> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `etudiant`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Etudiant e = new Etudiant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(9), rs.getInt(10), rs.getInt(11));
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
            String req = "update `etudiant` set nom = ?, prenom = ?, phone = ?, email = ?, pwd = ?, carte_banq = ?, section = ?, niveau = ?, score = ? where   id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            Etudiant e = (Etudiant) u;

            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setInt(3, e.getPhone());
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getPwd());
            ps.setString(6, e.getCarte_banq());
            ps.setString(7, e.getRole().toString());
            ps.setString(8, e.getSection());
            ps.setInt(9, e.getNiveau());
            ps.setInt(10, e.getScore());

            ps.setInt(11, e.getId());
            ServiceUser sU = new ServiceUser();
            sU.update(e);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Object u) {
        Etudiant e = (Etudiant) u;

        String req = "delete from Etudiant where id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
            System.out.println("Etudiant supprimer");
            ServiceUser sU = new ServiceUser();
            sU.delete(e);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public void add(Object u) {
        // TODO Auto-generated method stub

    }

}
