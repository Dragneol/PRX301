/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.daos;

import duongpth.jaxbs.Ingredient;
import duongpth.jaxbs.Recipe;
import duongpth.utils.DatabaseUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author dragn
 */
public class RecipeDAO implements Serializable {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    private void closeConnection() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public boolean insert(Recipe r) throws NamingException, SQLException {
        boolean inserted = false;
//        String sql = "" 
        return inserted;
    }
}
