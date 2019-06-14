/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Decen
 */
public class MyConnection {
    public static Connection getConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=SieuThiDoCu;","sa","123456");
            return con;
        } catch (Exception e) {
        }
        return null;
    }
}
