/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.daos;

import duongpth.jaxbs.Ingredientdetail;
import duongpth.jaxbs.Instructiondetail;
import duongpth.jaxbs.Recipe;
import duongpth.utils.DatabaseUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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

    public boolean insert(Recipe r) throws NamingException, SQLException, ClassNotFoundException {
        boolean inserted = false;
        String insertTblRecipe = "INSERT INTO Recipe([ID],[Title], [Description], [Ration], [PrepareTime], [CookingTime]) VALUES(?,?,?,?,?,?)";
        String insertTblInstructionMenu = "INSERT INTO InstructionMenu([RecipeID], [NumStep], [Detail]) VALUES(?,?,?)";
        String insertTblIngredientMenu = "INSERT INTO IngredientMenu([RecipeID], [Name], [Unit], [Quantitive]) VALUES(?,?,?,?)";
        PreparedStatement tmp = null;

        List<Ingredientdetail> ingredients = null;
        List<Instructiondetail> instructions = null;
        int recipeId;

        connection = DatabaseUtil.getConnection();
        if (connection != null) {
            try {
                connection.setAutoCommit(false);

                preparedStatement = connection.prepareStatement(insertTblRecipe);
                preparedStatement.setInt(1, r.getId());
                preparedStatement.setString(2, r.getTitle().trim());
                preparedStatement.setString(3, r.getDescription().trim());
                preparedStatement.setInt(4, r.getRation());
                preparedStatement.setInt(5, r.getPreparetime());
                preparedStatement.setInt(6, r.getCookingtime());

                inserted = preparedStatement.executeUpdate() > 0;
                if (inserted) {

                    recipeId = r.getId();
                    ingredients = r.getIngredientmenu().getIngredientdetail();
                    instructions = r.getInstructionmenu().getInstructiondetail();

                    for (Instructiondetail instruction : instructions) {
                        tmp = connection.prepareStatement(insertTblInstructionMenu);
                        tmp.setInt(1, recipeId);
                        tmp.setInt(2, instruction.getNumstep());
                        tmp.setString(3, instruction.getDetail().trim());
                        tmp.executeUpdate();
                    }

                    for (Ingredientdetail ingredient : ingredients) {
                        tmp = connection.prepareStatement(insertTblIngredientMenu);
                        tmp.setInt(1, recipeId);
                        tmp.setString(2, ingredient.getName().trim());
                        tmp.setString(3, ingredient.getUnit().trim());
                        tmp.setInt(4, ingredient.getQuantitive());
                        tmp.executeUpdate();
                    }
                    connection.commit();

                }
            } finally {
                closeConnection();
            }
        }
        return inserted;
    }
}
