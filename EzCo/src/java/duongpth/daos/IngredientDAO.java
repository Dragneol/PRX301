/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.daos;

import duongpth.jaxbs.Ingredient;
import duongpth.utils.DatabaseUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author dragn
 */
public class IngredientDAO implements Serializable {

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

    public boolean insert(Ingredient i) throws NamingException, SQLException {
        boolean inserted = false;
        String sql = "IF EXISTS (SELECT * FROM Ingredient WHERE OldID = ?)\n"
                + "BEGIN\n"
                + "	UPDATE Ingredient\n"
                + "	SET [Name] = ?, Price= ?, Unit = ?\n"
                + "	WHERE OldID = ?;\n"
                + "END\n"
                + "ELSE\n"
                + "BEGIN\n"
                + "   Insert Into Ingredient(OldID, [Name], Price, Link, [Image], Unit) values(?,?,?,?, ?,?)\n"
                + "END";
        try {

            connection = DatabaseUtil.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, i.getOldid());
                preparedStatement.setString(2, i.getName());
                preparedStatement.setInt(3, i.getPrice());
                preparedStatement.setInt(4, i.getUnit());
                preparedStatement.setString(5, i.getOldid());
                preparedStatement.setString(6, i.getOldid());
                preparedStatement.setString(7, i.getName());
                preparedStatement.setInt(8, i.getPrice());
                preparedStatement.setString(9, i.getLink());
                preparedStatement.setString(10, i.getImage());
                preparedStatement.setInt(11, i.getUnit());

                inserted = preparedStatement.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return inserted;
    }

    public List<Ingredient> getFirst(int n) throws NamingException, SQLException {
        List<Ingredient> list = new ArrayList<Ingredient>();
        String sql = "SELECT TOP (?) [ID]\n"
                + "      ,[OldID]\n"
                + "      ,[Name]\n"
                + "      ,[Price]\n"
                + "      ,[Link]\n"
                + "      ,[Image]\n"
                + "      ,[Unit]\n"
                + "  FROM [EzCo].[dbo].[Ingredient]";

        connection = DatabaseUtil.getConnection();
        if (connection != null) {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, n);
            resultSet = preparedStatement.executeQuery();
            BigInteger id, unit, price;
            String oldid, name, link, image;

            Ingredient ingredient = null;
            while (resultSet.next()) {
//                id = resultSet.getInt("ID");
//                oldid = resultSet.getString("OldID");
//                name = resultSet.getString("Name");
//                price = resultSet.getInt("Price");
//                link = resultSet.getString("Link");
//                image = resultSet.getString("Image");
//                unit = resultSet.getInt("Unit");

//                ingredient = new Ingredient(id, oldid, name, link, price, unit, image);
            }
        }
        return list;
    }
}
