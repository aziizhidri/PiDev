package Test;

import java.util.Scanner;

import modeles.Enseignant;
import modeles.Etudiant;
import modeles.Role;
import modeles.User;
import services.ServiceEns;
import services.ServiceEtudiant;
import services.ServiceUser;

public class Menu {

    ServiceUser sU = new ServiceUser();
    ServiceEtudiant sEt = new ServiceEtudiant();
    ServiceEns sEn = new ServiceEns();

    public Menu() {
    }

    Scanner in = new Scanner(System.in);

    public String start() {
        System.out.println(
                " ========================"
                        + "\n 1. list all users"
                        + "\n 2. create user"
                        + "\n 3. update user"
                        + "\n 4. delete user"
                        + "\n 5. Send Email"
                        + "\n 6. addCours"
                        + "\n 7. reset Password"
                        + "\n 8. login "
                        + "\n 0. Quit");

        String s = in.nextLine();
        return s;
    }

    public String Add() {
        System.out.println(" ========================"
                + "\n 1. create simple user (test)"
                + "\n 2. create Etudiant"
                + "\n 3. create Recruteur"
                + "\n 3. create Admin"
                + "\n 4. create Enseignant");

        String s = in.nextLine();
        return s;
    }

    public User AddUser() {
        User U = new User();

        System.out.println("Nom = ");
        U.setNom(in.nextLine());
        System.out.println("prenom = ");
        U.setPrenom(in.nextLine());
        System.out.println("phone = ");
        U.setPhone(Integer.valueOf(in.nextLine()));
        System.out.println("email = ");
        U.setEmail(in.nextLine());
        System.out.println("pwd = ");
        U.setPwd(in.nextLine());
        System.out.println("carte_banq = ");
        U.setCarte_banq(in.nextLine());
        U.setRole(Role.empty);

        return U;
    }

    public Etudiant AddEtudiant() {
        Etudiant e = new Etudiant();

        // e = (Etudiant) AddUser();
        System.out.println("Nom = ");
        e.setNom(in.nextLine());
        System.out.println("prenom = ");
        e.setPrenom(in.nextLine());
        System.out.println("phone = ");
        e.setPhone(Integer.valueOf(in.nextLine()));
        System.out.println("email = ");
        e.setEmail(in.nextLine());
        System.out.println("pwd = ");
        e.setPwd(in.nextLine());
        System.out.println("carte_banq = ");
        e.setCarte_banq(in.nextLine());
        e.setRole(Role.etudiant);

        System.out.println("section  = ");
        e.setSection(in.nextLine());
        System.out.println("niveau  = ");
        e.setNiveau(Integer.valueOf(in.nextLine()));
        System.out.println("score  = ");
        e.setScore(Integer.valueOf(in.nextLine()));

        return e;
    }

    public Enseignant AddEnseignant() {
        Enseignant e = new Enseignant();

        // e = (Etudiant) AddUser();
        System.out.println("Nom = ");
        e.setNom(in.nextLine());
        System.out.println("prenom = ");
        e.setPrenom(in.nextLine());
        System.out.println("phone = ");
        e.setPhone(Integer.valueOf(in.nextLine()));
        System.out.println("email = ");
        e.setEmail(in.nextLine());
        System.out.println("pwd = ");
        e.setPwd(in.nextLine());
        System.out.println("carte_banq = ");
        e.setCarte_banq(in.nextLine());
        e.setRole(Role.etudiant);

        System.out.println("section  = ");
        e.setSection(in.nextLine());
        System.out.println("universite  = ");
        e.setUniversite(in.nextLine());

        return e;
    }

    public boolean updateUser() {
        System.out.println("Donner l'ID");
        String id = in.nextLine();

        User us = new User();
        ServiceUser sU = new ServiceUser();
        us = sU.getById(Integer.valueOf(id));

        int choix = 1;
        while (choix != 0) {
            System.out.println("que voulez-vous changer ");
            System.out.println("1. nom ");
            System.out.println("2. prenom ");
            System.out.println("3. phone ");
            System.out.println("4. email ");
            System.out.println("5. pwd ");
            System.out.println("6. carte_banq ");
            System.out.println("0. quit ");

            choix = Integer.valueOf(in.nextLine());
            System.out.println("donner la nouvelle valeur  ");
            switch (choix) {
                case 1:
                    System.out.println("Nom = ");
                    us.setNom(in.nextLine());
                    break;
                case 2:
                    System.out.println("prenom = ");
                    us.setPrenom(in.nextLine());
                    break;
                case 3:
                    System.out.println("phone = ");
                    us.setPhone(Integer.valueOf(in.nextLine()));
                    break;
                case 4:
                    System.out.println("email = ");
                    us.setEmail(in.nextLine());
                    break;
                case 5:
                    System.out.println("pwd = ");
                    us.setPwd(in.nextLine());
                    break;
                case 6:
                    System.out.println("carte_banq = ");
                    us.setCarte_banq(in.nextLine());
                    break;

                default:
                    break;
            }
        }
        System.out.println("===*****===");
        sU.update(us);
        System.out.println("===*****===");

        return false;
    }

    public boolean deleteUser() {
        System.out.println("Donner l'ID");
        int id = Integer.valueOf(in.nextLine());
        User us = new User(id);
        ServiceUser sU = new ServiceUser();
        sU.delete(us);
        return true;
    }

    public boolean login() {
        System.out.println("Donner le mail");
        String mail = in.nextLine();
        System.out.println("Donner le mot de pass");
        String mdp = in.nextLine();
        User u = sU.getByMail(mail);
        System.out.println(u);
        if (u.getPwd().equals(mdp)) {
            System.out.println("Welcome " + u.getNom());
            return true;
        } else {
            System.out.println("mod de pass incorect");
            return false;
        }

    }
}
