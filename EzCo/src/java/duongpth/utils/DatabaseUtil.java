/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author dragn
 */
public class DatabaseUtil implements Serializable {

//    public static Connection getConnection() throws NamingException, SQLException {
//        Context context = new InitialContext();
//        Context tomcatcontext = (Context) context.lookup("java:comp/env");
//        DataSource dataSource = (DataSource) tomcatcontext.lookup("DBSource");
//        return dataSource.getConnection();
//    }
    public static Connection getConnection() throws NamingException, SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=EzCoVer2;","sa","P@ssw0rd");
            return con;
    }
}
