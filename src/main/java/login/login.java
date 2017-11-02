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
public class login {

    static private boolean login = false;

    public static boolean isLogin() {

        return login;
    }

    void setLogin(boolean log) {
        login = log;
    }

    public static void setLogout() {
        login=false;
        Authentification.setLogout();
    }

}
