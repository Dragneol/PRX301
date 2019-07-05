/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.daos;

import duongpth.jaxbs.Subdomain;
import duongpth.utils.DatabaseUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author dragn
 */
public class RecipeCateDAO implements Serializable {

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

    public boolean insertCategory(Subdomain category) throws SQLException, NamingException {
        String sql = "IF exists (select ID from RecipeCategory where ID = ?)\n"
                + "BEGIN\n"
                + "    update RecipeCategory set Link = ?, Name = ? where ID = ?\n"
                + "END\n"
                + "ELSE\n"
                + "BEGIN\n"
                + "    insert into RecipeCategory(ID, Link, Name) values (?,?,?)\n"
                + "END";
        boolean inserted = false;

        try {
            connection = DatabaseUtil.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, category.getId());
                preparedStatement.setString(2, category.getHref());
                preparedStatement.setString(3, category.getValue());
                preparedStatement.setInt(4, category.getId());
                preparedStatement.setInt(5, category.getId());
                preparedStatement.setString(6, category.getHref());
                preparedStatement.setString(7, category.getValue());

                inserted = preparedStatement.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return inserted;
    }

    public boolean insertCategories(List<Subdomain> categories) throws SQLException, NamingException {
        boolean inserted = false;
        StringBuilder sb = new StringBuilder();
        String sql = "insert into RecipeCategory values ";
        sb.append(sql);
        for (int i = 0; i < categories.size(); i++) {
            sb.append("(?,?,?)");
            if (i < categories.size() - 1) {
                sb.append(',');
            }
        }
        sql = sb.toString();
        try {
            connection = DatabaseUtil.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                for (int i = 0; i < categories.size(); i++) {
                    preparedStatement.setInt(i * 3 + 1, categories.get(i).getId());
                    preparedStatement.setString(i * 3 + 2, categories.get(i).getValue());
                    preparedStatement.setString(i * 3 + 3, categories.get(i).getHref());
                }
                inserted = preparedStatement.executeUpdate() == (categories.size() - 1);
            }
        } finally {
            closeConnection();
        }
        return inserted;
    }

    public boolean insertCateOfRecipe(int recipeId, int categoryId) throws SQLException, NamingException {
        boolean inserted = false;
        String sql = "if not exists (select ID from CateRep where RecipeID = ? and CateID = ?) insert into CateRep(RecipeID, CateID) values(?,?)";
        try {
            connection = DatabaseUtil.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, recipeId);
                preparedStatement.setInt(2, categoryId);
                preparedStatement.setInt(3, recipeId);
                preparedStatement.setInt(4, categoryId);
                inserted = preparedStatement.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return inserted;
    }
}
