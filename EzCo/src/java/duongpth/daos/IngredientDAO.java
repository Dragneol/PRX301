/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.daos;

import duongpth.jaxbs.Ingredient;
import duongpth.utils.DatabaseUtil;
import java.io.Serializable;
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

    public boolean insert(Ingredient i) throws NamingException, SQLException, ClassNotFoundException {
        boolean inserted = false;
        String sql = "IF NOT EXISTS (SELECT * FROM Ingredient WHERE ID = ?) \n"
                + "Insert Into Ingredient(ID, [Name], Price, Link, [Image], Unit) values(?,?,?,?,?,?)";
        try {

            connection = DatabaseUtil.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, i.getId().trim());
                preparedStatement.setString(2, i.getId().trim());
                preparedStatement.setString(3, i.getName().trim());
                preparedStatement.setInt(4, i.getPrice());
                preparedStatement.setString(5, i.getLink().trim());
                preparedStatement.setString(6, i.getImage().trim());
                preparedStatement.setInt(7, i.getUnit());

                inserted = preparedStatement.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return inserted;
    }

    public List<Ingredient> getFirst(int n) throws NamingException, SQLException, ClassNotFoundException {
        List<Ingredient> list = new ArrayList<Ingredient>();
        String sql = "SELECT TOP (?) [ID]\n"
                + "      ,[Name]\n"
                + "      ,[Price]\n"
                + "      ,[Link]\n"
                + "      ,[Image]\n"
                + "      ,[Unit]\n"
                + "  FROM [Ingredient]";
        try {
            connection = DatabaseUtil.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, n);
                resultSet = preparedStatement.executeQuery();
                int unit, price;
                String id, name, link, image;

                Ingredient ingredient = null;
                while (resultSet.next()) {
                    id = resultSet.getString("ID").trim();
                    name = resultSet.getString("Name").trim();
                    price = resultSet.getInt("Price");
                    link = resultSet.getString("Link").trim();
                    image = resultSet.getString("Image").trim();
                    unit = resultSet.getInt("Unit");

                    ingredient = new Ingredient();
                    ingredient.setId(id);
                    ingredient.setName(name);
                    ingredient.setPrice(price);
                    ingredient.setLink(link);
                    ingredient.setImage(image);
                    ingredient.setUnit(unit);
                    list.add(ingredient);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
