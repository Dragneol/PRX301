/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sax.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sax.dtos.StudentDTO;

/**
 *
 * @author dragn
 */
public class StudentDAO implements Serializable {

    public boolean insert(StudentDTO dto) {
        boolean inserted = false;
        try {
            String sql = "Insert Into Registration(Username, Password, Fullname, Role) "
                    + "values(?,?,?,?)";
            String conString = "jdbc:sqlserver://localhost:1433;databaseName=Sinhvien";
            String user = "sa";
            String pass = "P@ssw0rd";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection connection = DriverManager.getConnection(conString, user, pass);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dto.getId());
            preparedStatement.setString(2, dto.getPass());
            preparedStatement.setString(3, dto.getName());
            preparedStatement.setString(4, dto.getStatus());

            inserted = preparedStatement.execute(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserted;
    }
}
