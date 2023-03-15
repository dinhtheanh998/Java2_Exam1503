package DAO;

import Model.Brands;
import Connection.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
public class BrandDAO {
    public List<Brands> getAll(){
        List<Brands> brands = new ArrayList<>();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "SELECT * FROM brands";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brands brand = new Brands();
                brand.setId(resultSet.getInt("id"));
                brand.setBrand_name(resultSet.getString("brand_name"));
                brands.add(brand);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }

    // count product group by brand
    public void countProductByBrand(){
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "SELECT brands.brand_name, COUNT(products.brand_id) AS total FROM brands INNER JOIN products ON brands.id = products.brand_id GROUP BY brands.brand_name";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("brand_name") + " : " + resultSet.getInt("total"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Brands getBrandByID(int id){
        try{
            Connection connection = MyConnection.getConnection();
            String sql = "SELECT * FROM brands WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brands brand = new Brands();
                brand.setId(resultSet.getInt("id"));
                brand.setBrand_name(resultSet.getString("brand_name"));
                return brand;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }



    // insert
    public boolean insertBrand(Brands b){
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "Insert into brands(brand_name,brand_address) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, b.getBrand_name());
            preparedStatement.setString(2, b.getBrand_address());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // delete brand
    public boolean deleteBrand(int id){
        try{
            Connection connection = MyConnection.getConnection();
            String sql = "DELETE FROM brands WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
