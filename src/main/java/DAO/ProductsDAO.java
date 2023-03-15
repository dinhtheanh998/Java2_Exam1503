package DAO;

import Model.Products;
import Connection.MyConnection;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class ProductsDAO {
    public List<Products> getAllProducts(){
        List<Products> products = new ArrayList<>();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "SELECT * FROM products";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Products product = new Products();
                product.setId(resultSet.getInt("id"));
                product.setProduct_name(resultSet.getString("product_name"));
                product.setProduct_price(resultSet.getInt("product_price"));
                product.setProduct_size(resultSet.getString("product_size"));
                product.setProduct_color(resultSet.getString("product_color"));
                product.setBrand_id(resultSet.getInt("brand_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Products getProductById(int id){
        Products product = new Products();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product.setId(resultSet.getInt("id"));
                product.setProduct_name(resultSet.getString("product_name"));
                product.setProduct_price(resultSet.getInt("product_price"));
                product.setProduct_size(resultSet.getString("product_size"));
                product.setProduct_color(resultSet.getString("product_color"));
                product.setBrand_id(resultSet.getInt("brand_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean addProduct(Products product){
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "INSERT INTO products(product_name, product_price, product_size, product_color, brand_id) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProduct_name());
            preparedStatement.setInt(2, product.getProduct_price());
            preparedStatement.setString(3, product.getProduct_size());
            preparedStatement.setString(4, product.getProduct_color());
            preparedStatement.setInt(5, product.getBrand_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // update product
    public boolean updateProduct(Products product){
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "UPDATE products SET product_name = ?, product_price = ?, product_size = ?, product_color = ?, brand_id = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProduct_name());
            preparedStatement.setInt(2, product.getProduct_price());
            preparedStatement.setString(3, product.getProduct_size());
            preparedStatement.setString(4, product.getProduct_color());
            preparedStatement.setInt(5, product.getBrand_id());
            preparedStatement.setInt(6, product.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // delete product
    public boolean deleteProduct(int id){
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "DELETE FROM products WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            // close connection
            connection.close();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //limit 5 product highest price
    public List<Products> getTop5Product(){
        List<Products> products = new ArrayList<>();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "SELECT * FROM products ORDER BY product_price DESC LIMIT 5";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Products product = new Products();
                product.setId(resultSet.getInt("id"));
                product.setProduct_name(resultSet.getString("product_name"));
                product.setProduct_price(resultSet.getInt("product_price"));
                product.setProduct_size(resultSet.getString("product_size"));
                product.setProduct_color(resultSet.getString("product_color"));
                product.setBrand_id(resultSet.getInt("brand_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

}
