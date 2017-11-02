/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fathi
 */
public class Connection {

    private PreparedStatement ps;
    private java.sql.Connection connect = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;

    public Connection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        try {
            String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/fatwa"+unicode, "root", "");

            //getdata("user");
          
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    PreparedStatement update(String sql) {
        try {
            ps = connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        } catch (Exception ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }
}
