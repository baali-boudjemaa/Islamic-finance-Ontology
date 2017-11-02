/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

/**
 *
 * @author fathi
 */
public class Authentification {

    private static boolean authenti = false;

    public static void getAuthentification(String pass) {
        AuthentificationService au = new AuthentificationService();
        authenti = au.checkPassword(pass);
        login l = new login();
        l.setLogin(authenti);

    }

    public static boolean isAuthentificated() {

        return authenti;
    }

    public static void setLogout() {
        authenti = false;
    }
}
