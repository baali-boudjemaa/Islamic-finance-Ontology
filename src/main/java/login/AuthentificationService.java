/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author fathi
 */
class AuthentificationService {

    boolean checkPassword(String password) {
        boolean verif = false;
        String pass = null;
        try {
            String dir = System.getProperty("user.dir");
            File file = new File(dir + "/user.xml");
            if (file.exists()) {
                pass = initFromXMLfile(file);
                System.out.println("pass :" + password + "/" + pass);
                if (password.equals(pass)) {
                    verif = true;
                    System.out.println("pass ok");
                }
            } else {
                System.out.println("file not found :");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verif;
    }

    private String initFromXMLfile(File file) {
        String pass = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {

            db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            Node users = doc.getElementsByTagName("User").item(1);
            NodeList l = users.getChildNodes();

            pass = l.item(3).getTextContent();

        } catch (ParserConfigurationException | SAXException | IOException | DOMException ex) {
            System.out.println("file Configuration not found" + ex);
        }

        return pass;
    }

}
