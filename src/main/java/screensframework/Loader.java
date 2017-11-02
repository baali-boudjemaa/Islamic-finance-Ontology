/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import file.CopyFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fathi
 */
public class Loader {
    public void afterLogin(){
        InputStream in;
        String dir = null;
        dir = System.getProperty("user.dir");
        File file = new File(dir + "/fatwa.db");
        if (!file.exists()) {
            try {
                in = getClass().getResourceAsStream("/database/fatwa.db");
                CopyFile.stream2file(in, dir, "fatwa.db");
            } catch (IOException ex) {
                Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        file = new File(dir + "/islamic_finance.owl");
        if (!file.exists()) {
            try {
                in = getClass().getResourceAsStream("/ontofile/islamic_finance.owl");
                CopyFile.stream2file(in, dir, "islamic_finance.owl");
            } catch (IOException ex) {
                Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        file = new File(dir + "/user.xml");
        if (!file.exists()) {
            try {
                in = getClass().getResourceAsStream("/filemanip/user.xml");
                CopyFile.stream2file(in, dir, "user.xml");
            } catch (IOException ex) {
                Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        file = new File(dir + "/AidFatwa2.owl");
        if (!file.exists()) {
            try {
                in = getClass().getResourceAsStream("/ontofile/AidFatwa2.owl");
                CopyFile.stream2file(in, dir, "AidFatwa2.owl");
            } catch (IOException ex) {
                Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    public static void onStart(){
        SuperClass.getListActionFromOntology();
    }
}
