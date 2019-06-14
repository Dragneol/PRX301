/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import thang.db.MyConnection;
import thang.genobj.Images;
import thang.genobj.Product;

/**
 *
 * @author Decen
 */
public class ProductDAO {

    private Connection con;
    private PreparedStatement prestmt;
    private ResultSet rs;

    public ProductDAO() {
    }

    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (prestmt != null) {
                prestmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
        }
    }

    public int insert(Product product) {
        int result = -1;
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("INSERT INTO Product"
                    + "([name],price,priceOld,[status],categoryId,[description],rate) "
                    + "VALUES (?,?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            long price = (product.getPrice() != null) ? product.getPrice().longValue() : 1;
            long priceOld = (product.getPriceOld() != null) ? product.getPriceOld().longValue() : 1;
            double status = (product.getStatus() != null) ? product.getStatus().doubleValue() : 90;
            double rate = (product.getRate() != null) ? product.getRate().doubleValue() : 0;
            prestmt.setString(1, product.getName());
            prestmt.setDouble(2, price);
            prestmt.setDouble(3, priceOld);
            prestmt.setDouble(4, status);
            prestmt.setString(5, product.getCategoryId());
            prestmt.setString(6, product.getDescription());
            prestmt.setDouble(7, rate);
            prestmt.execute();
            rs = prestmt.getGeneratedKeys();
            rs.next();
            result = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean update(Product product) {
        boolean result = false;
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("UPDATE Product "
                    + "SET [name] = ?, price = ?, priceOld = ?, [status] = ?, [description] = ? "
                    + "WHERE id = ?",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            long price = (product.getPrice() != null) ? product.getPrice().longValue() : 1;
            long priceOld = (product.getPriceOld() != null) ? product.getPriceOld().longValue() : 1;
            double status = (product.getStatus() != null) ? product.getStatus().doubleValue() : 1;
            prestmt.setString(1, product.getName());
            prestmt.setDouble(2, price);
            prestmt.setDouble(3, priceOld);
            prestmt.setDouble(4, status);
            prestmt.setString(5, product.getDescription());
            prestmt.setInt(6, product.getId().intValue());
            int num = prestmt.executeUpdate();
            result = (num > 0) ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("SELECT * FROM Product ");
            rs = prestmt.executeQuery();
            while (rs.next()) {
                Product product = setProduct(rs);
                list.add(product);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<Product> getProductById(int categoryId) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("SELECT * FROM Product WHERE id = ?");
            prestmt.setInt(1, categoryId);
            rs = prestmt.executeQuery();
            while (rs.next()) {
                Product product = setProduct(rs);
                list.add(product);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<Product> getProducts(int offset, int next) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("SELECT * FROM Product ORDER BY rate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            prestmt.setInt(1, offset);
            prestmt.setInt(2, next);
            rs = prestmt.executeQuery();
            while (rs.next()) {
                Product product = setProduct(rs);
                list.add(product);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }
    public ArrayList<Product> getProductsFilter(int offset, int next, int filter) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            con = MyConnection.getConnection();
            String sql;
            switch (filter) {
                case 2:
                    sql = "SELECT * FROM Product ORDER BY price ASC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                case 3:
                    sql = "SELECT * FROM Product ORDER BY price DESC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                case 4:
                    sql = "SELECT * FROM Product ORDER BY status DESC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                case 5:
                    sql = "SELECT * FROM Product ORDER BY status ASC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                default:
                    sql = "SELECT * FROM Product ORDER BY rate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
            }
            prestmt = con.prepareStatement(sql);
            prestmt.setInt(1, offset);
            prestmt.setInt(2, next);
            rs = prestmt.executeQuery();
            while (rs.next()) {
                Product product = setProduct(rs);
                list.add(product);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<Product> searchProductsByName(String search, int offset, int next, int filter) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            con = MyConnection.getConnection();
            String sql;
            switch (filter) {
                case 2:
                    sql = "SELECT * FROM Product WHERE [name] LIKE ? ORDER BY price ASC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                case 3:
                    sql = "SELECT * FROM Product WHERE [name] LIKE ? ORDER BY price DESC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                case 4:
                    sql = "SELECT * FROM Product WHERE [name] LIKE ? ORDER BY status DESC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                case 5:
                    sql = "SELECT * FROM Product WHERE [name] LIKE ? ORDER BY status ASC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                default:
                    sql = "SELECT * FROM Product WHERE [name] LIKE ? ORDER BY rate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
            }
            prestmt = con.prepareStatement(sql);
            prestmt.setString(1, "%" + search + "%");
            prestmt.setInt(2, offset);
            prestmt.setInt(3, next);
            rs = prestmt.executeQuery();
            while (rs.next()) {
                Product product = setProduct(rs);
                list.add(product);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<Product> searchProductsByNameAndCategory(String search, String categoryId, int offset, int next, int filter) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            con = MyConnection.getConnection();
            String sql;
            switch (filter) {
                case 2:
                    sql = "SELECT * FROM Product WHERE categoryId = ? AND [name] LIKE ? ORDER BY price ASC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                case 3:
                    sql = "SELECT * FROM Product WHERE categoryId = ? AND [name] LIKE ? ORDER BY price DESC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                case 4:
                    sql = "SELECT * FROM Product WHERE categoryId = ? AND [name] LIKE ? ORDER BY status DESC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                case 5:
                    sql = "SELECT * FROM Product WHERE categoryId = ? AND [name] LIKE ? ORDER BY status ASC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                default:
                    sql = "SELECT * FROM Product WHERE categoryId = ? AND [name] LIKE ? ORDER BY rate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
            }
            prestmt = con.prepareStatement(sql);
            prestmt.setString(1, categoryId);
            prestmt.setString(2, "%" + search + "%");
            prestmt.setInt(3, offset);
            prestmt.setInt(4, next);
            rs = prestmt.executeQuery();
            while (rs.next()) {
                Product product = setProduct(rs);
                list.add(product);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<Product> getProductsByCategory(String categoryId, int offset, int next) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("SELECT * FROM Product WHERE categoryId = ? ORDER BY rate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            prestmt.setString(1, categoryId);
            prestmt.setInt(2, offset);
            prestmt.setInt(3, next);
            rs = prestmt.executeQuery();
            while (rs.next()) {
                Product product = setProduct(rs);
                list.add(product);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    public ArrayList<Product> getProductsByCategoryFilter(String categoryId, int offset, int next, int filter) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            con = MyConnection.getConnection();
            String sql;
            switch (filter) {
                case 2:
                    sql = "SELECT * FROM Product WHERE categoryId = ? ORDER BY price ASC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                case 3:
                    sql = "SELECT * FROM Product WHERE categoryId = ? ORDER BY price DESC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                case 4:
                    sql = "SELECT * FROM Product WHERE categoryId = ? ORDER BY status DESC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                case 5:
                    sql = "SELECT * FROM Product WHERE categoryId = ? ORDER BY status ASC, rate DESC  OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
                default:
                    sql = "SELECT * FROM Product WHERE categoryId = ? ORDER BY rate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                    break;
            }
            prestmt = con.prepareStatement(sql);
            prestmt.setString(1, categoryId);
            prestmt.setInt(2, offset);
            prestmt.setInt(3, next);
            rs = prestmt.executeQuery();
            while (rs.next()) {
                Product product = setProduct(rs);
                list.add(product);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return list;
    }

    private Product setProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getBigDecimal("id").toBigInteger());
        product.setName(rs.getNString("name"));
        product.setCategoryId(rs.getNString("categoryId"));
        product.setPrice(rs.getBigDecimal("price").toBigInteger());
        product.setPriceOld(rs.getBigDecimal("priceOld").toBigInteger());
        product.setStatus(rs.getBigDecimal("status"));
        product.setDescription(rs.getNString("description"));
        ImageDAO imageDAO = new ImageDAO();
        Images images = imageDAO.getImagesByProductId(rs.getInt("id"));
        product.setImages(images);
        return product;
    }
}
