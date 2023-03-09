package Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import modeles.User;
import services.ServiceUser;

public class Test {

    public static String crypString(String password) throws NoSuchAlgorithmException {
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

    public static void main(String[] args) throws NoSuchAlgorithmException {
        ServiceUser sU = new ServiceUser();
        System.out.println(sU.getByMail("haja"));
        
        User u = new User();
        System.out.println(u.Login("haja", "seif"));
    }
}
