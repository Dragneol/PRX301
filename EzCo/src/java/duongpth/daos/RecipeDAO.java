/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.daos;

import duongpth.jaxbs.Ingredientdetail;
import duongpth.jaxbs.Ingredientmenu;
import duongpth.jaxbs.Instructiondetail;
import duongpth.jaxbs.Instructionmenu;
import duongpth.jaxbs.Recipe;
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
public class RecipeDAO implements Serializable {

    private final String insertTblRecipe = "IF NOT EXISTS (SELECT * FROM Recipe WHERE ID = ?) INSERT INTO Recipe([ID],[Title],[Link],[Image],[Description],[Ration],[PrepareTime],[CookingTime]) VALUES(?,?,?,?,?,?,?,?)";
    private final String findByIdTblRecipe = "SELECT [Title],[Link],[Image],[Description],[Ration],[PrepareTime],[CookingTime] FROM [dbo].[Recipe] Where ID = ?";
    private final String findByIngredientTblRecipe = "select Id, Title, Link, Image, Description, Ration, PrepareTime, CookingTime, (PrepareTime + CookingTime) as TotalTime from Recipe Where ID In (select RecipeID from IngredientMenu where [Name] like ?) ORDER BY TotalTime, CookingTime";
    private final String insertTblInstructionMenu = "INSERT INTO InstructionMenu([RecipeID], [NumStep], [Detail]) VALUES(?,?,?)";
    private final String findByIdTblInstructionMenu = "SELECT [NumStep],[Detail] FROM [dbo].[InstructionMenu] where RecipeID = ?";
    private final String insertTblIngredientMenu = "INSERT INTO IngredientMenu([RecipeID], [Name], [Unit], [Quantitive]) VALUES(?,?,?,?)";
    private final String findByIdTblIngredientMenu = "SELECT [Name],[Unit],[Quantitive] FROM [dbo].[IngredientMenu] where RecipeID = ?";

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
                preparedStatement.setInt(2, r.getId());
                preparedStatement.setString(3, r.getTitle().trim());
                preparedStatement.setString(4, r.getLink().trim());
                preparedStatement.setString(5, r.getImage().trim());
                preparedStatement.setString(6, r.getDescription().trim());
                preparedStatement.setInt(7, r.getRation());
                preparedStatement.setInt(8, r.getPreparetime());
                preparedStatement.setInt(9, r.getCookingtime());

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
                        tmp.setString(4, ingredient.getQuantitive());
                        tmp.executeUpdate();
                    }
                    connection.commit();
                    if (tmp != null) {
                        tmp.close();
                    }
                }
            } finally {
                closeConnection();
            }
        }
        return inserted;
    }

    public List<Recipe> getFirst(int n) throws NamingException, SQLException, ClassNotFoundException {
        List<Recipe> list = new ArrayList<>();
        Recipe recipe = null;
        String sql = "SELECT TOP (?) [ID]\n"
                + "      ,[Title]\n"
                + "      ,[Link]\n"
                + "      ,[Image]\n"
                + "      ,[Description]\n"
                + "      ,[Ration]\n"
                + "      ,[PrepareTime]\n"
                + "      ,[CookingTime]\n"
                + "  FROM [dbo].[Recipe] ORDER BY ID DESC";
        try {
            connection = DatabaseUtil.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, n);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    recipe = new Recipe();
                    recipe.setId(resultSet.getInt("ID"));
                    recipe.setTitle(resultSet.getString("Title"));
                    recipe.setLink(resultSet.getString("Link"));
                    recipe.setImage(resultSet.getString("Image"));
                    recipe.setDescription(resultSet.getString("Description"));
                    recipe.setRation(resultSet.getInt("Ration"));
                    recipe.setPreparetime(resultSet.getInt("PrepareTime"));
                    recipe.setCookingtime(resultSet.getInt("CookingTime"));
                    list.add(recipe);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public Recipe getRecipe(int id) throws SQLException, NamingException, ClassNotFoundException {
        Recipe recipe = null;
        Instructionmenu instructions = null;
        Instructiondetail instruction = null;
        Ingredientmenu ingredients = null;
        Ingredientdetail ingredient = null;
        try {
            connection = DatabaseUtil.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(findByIdTblRecipe);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    recipe = new Recipe();
                    recipe.setTitle(resultSet.getString("Title"));
                    recipe.setLink(resultSet.getString("Link"));
                    recipe.setImage(resultSet.getString("Image"));
                    recipe.setDescription(resultSet.getString("Description"));
                    recipe.setRation(resultSet.getInt("Ration"));
                    recipe.setPreparetime(resultSet.getInt("PrepareTime"));
                    recipe.setCookingtime(resultSet.getInt("CookingTime"));
                    recipe.setId(id);
                    
                    ingredients = new Ingredientmenu();
                    preparedStatement = connection.prepareStatement(findByIdTblIngredientMenu);
                    preparedStatement.setInt(1, id);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        ingredient = new Ingredientdetail();
                        ingredient.setName(resultSet.getString("Name"));
                        ingredient.setUnit(resultSet.getString("Unit"));
                        ingredient.setQuantitive(resultSet.getString("Quantitive"));
                        ingredients.getIngredientdetail().add(ingredient);
                    }
                    recipe.setIngredientmenu(ingredients);
                    instructions = new Instructionmenu();
                    preparedStatement = connection.prepareStatement(findByIdTblInstructionMenu);
                    preparedStatement.setInt(1, id);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        instruction = new Instructiondetail();
                        instruction.setNumstep(resultSet.getInt("NumStep"));
                        instruction.setDetail(resultSet.getString("Detail"));
                        instructions.getInstructiondetail().add(instruction);
                    }
                    recipe.setInstructionmenu(instructions);
                }

            }
        } finally {
            closeConnection();
        }
        return recipe;
    }

    public List<Recipe> getRecipeByIngredient(String ingredientName) throws SQLException, NamingException {
        List<Recipe> recipes = new ArrayList<>();
        String sql = findByIngredientTblRecipe;
        // Id, Title, Link, Image, Description, Ration, PrepareTime, CookingTime, (PrepareTime + CookingTime) as TotalTime
        int id, ration, preparetime, cookingtime, totaltime;
        String title, link, image, description;
        Recipe recipe = null;
        try {
            connection = DatabaseUtil.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "%" + ingredientName + "%");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    id = resultSet.getInt("Id");
                    title = resultSet.getString("Title");
                    link = resultSet.getString("Link");
                    image = resultSet.getString("Image");
                    description = resultSet.getString("Description");
                    ration = resultSet.getInt("Ration");
                    preparetime = resultSet.getInt("PrepareTime");
                    cookingtime = resultSet.getInt("CookingTime");
                    totaltime = resultSet.getInt("TotalTime");
                    recipe = new Recipe();
                    recipe.setCookingtime(cookingtime);
                    recipe.setDescription(description);
                    recipe.setId(id);
                    recipe.setImage(image);
                    recipe.setLink(link);
                    recipe.setPreparetime(preparetime);
                    recipe.setRation(ration);
                    recipe.setTitle(title);
                    recipes.add(recipe);
                }
            }
        } finally {
            closeConnection();
        }
        return recipes;
    }

    public List<Recipe> getRecipeLikeName(String text) throws SQLException, NamingException {
        List<Recipe> recipes = new ArrayList<>();
        Recipe r = null;
        String sql = "select Id, Title, Image, Description, Ration, PrepareTime, CookingTime, (PrepareTime + CookingTime) "
                + "as Total from Recipe where Title like ? order by Total";
        try {
            connection = DatabaseUtil.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "%" + text + "%");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    r = new Recipe();
                    r.setCookingtime(resultSet.getInt("CookingTime"));
                    r.setDescription(resultSet.getString("Description"));
                    r.setId(resultSet.getInt("Id"));
                    r.setImage(resultSet.getString("Image"));
                    r.setPreparetime(resultSet.getInt("PrepareTime"));
                    r.setRation(resultSet.getInt("Ration"));
                    r.setTitle(resultSet.getString("Title"));
                    recipes.add(r);
                }
            }
        } finally {
            closeConnection();
        }
        return recipes;
    }

    public List<Recipe> getRecipeByCategories(int[] tagArr) throws SQLException, NamingException {
        List<Recipe> recipes = new ArrayList<>();
        Recipe recipe = null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tagArr.length - 1; i++) {
            sb.append("?,");
        }
        sb.append('?');
        String sql = "Select ID, Title, Image, Description, Ration, PrepareTime, CookingTime, (PrepareTime + CookingTime) as Time FROM Recipe, "
                + "(select COUNT(CateID) as Total, RecipeID from CateRep where CateID in (" + sb.toString() + ") group by RecipeID) cateRep "
                + "where Recipe.ID = cateRep.RecipeID order by cateRep.Total DESC, Time";
        try {
            connection = DatabaseUtil.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                for (int i = 0; i < tagArr.length; i++) {
                    preparedStatement.setInt(i + 1, tagArr[i]);
                }
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    recipe = new Recipe();
                    recipe.setId(resultSet.getInt("ID"));
                    recipe.setTitle(resultSet.getString("Title"));
                    recipe.setImage(resultSet.getString("Image"));
                    recipe.setDescription(resultSet.getString("Description"));
                    recipe.setRation(resultSet.getInt("Ration"));
                    recipe.setPreparetime(resultSet.getInt("PrepareTime"));
                    recipe.setCookingtime(resultSet.getInt("CookingTime"));
                    recipes.add(recipe);
                }
            }
        } finally {
            closeConnection();
        }
        return recipes;
    }
}
