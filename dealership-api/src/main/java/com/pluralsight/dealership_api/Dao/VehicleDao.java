package com.pluralsight.dealership_api.Dao;

import com.pluralsight.dealership_api.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Component
public class VehicleDao {

    private BasicDataSource dataSource;
    private List<Vehicle> vehicles;

    @Autowired
    public VehicleDao(BasicDataSource dataSource){
        this.dataSource = dataSource;
        this.vehicles = new ArrayList<>();
    }

    // Search for vehicles by price range
    public List<Vehicle> getVehiclesByPrice(float minPrice, float maxPrice){
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicles WHERE price BETWEEN ? AND ?;";
        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setFloat(1,minPrice);
            statement.setFloat(2,maxPrice);

            try(
                    ResultSet results = statement.executeQuery()
            ) {
                if(results.next()) {
                    do {
                        String vin = results.getString("VIN");
                        int year = results.getInt("Year");
                        String make = results.getString("Make");
                        String model = results.getString("Model");
                        String vehicleType = results.getString("Vehicle_Type");
                        String color = results.getString("Color");
                        float odometer = results.getFloat("Odometer");
                        float price = results.getFloat("Price");

                        vehicles.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));

                    } while (results.next());
                }else{
                    System.out.println("No matches!");
                }
            } catch (SQLException e) {
                System.out.println("Failed to execute query.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to create connection or prepared statement.");
        }
        return vehicles;
    }


    // Search for vehicles by make/model
    public List<Vehicle> getVehiclesByMakeModel(String name){
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicles WHERE Make LIKE ? OR Model LIKE ?;";
        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1,"%" + name + "%");
            statement.setString(2,"%" + name + "%");

            try(ResultSet results = statement.executeQuery()) {
                while(results.next()) {
                    String vin = results.getString("VIN");
                    int year = results.getInt("Year");
                    String make = results.getString("Make");
                    String model = results.getString("Model");
                    String vehicleType = results.getString("Vehicle_Type");
                    String color = results.getString("Color");
                    float odometer = results.getFloat("Odometer");
                    float price = results.getFloat("Price");

                    vehicles.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }
            } catch (SQLException e) {
                System.out.println("Failed to execute query.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to create connection or prepared statement.");
        }
        return vehicles;
    }

    // Search for vehicles by year range
    public List<Vehicle> getVehiclesByYear(int minYear, int maxYear){
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicles WHERE year BETWEEN ? AND ?;";
        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setFloat(1,minYear);
            statement.setFloat(2,maxYear);

            try(
                    ResultSet results = statement.executeQuery()
            ) {
                if(results.next()) {
                    do {
                        String vin = results.getString("VIN");
                        int year = results.getInt("Year");
                        String make = results.getString("Make");
                        String model = results.getString("Model");
                        String vehicleType = results.getString("Vehicle_Type");
                        String color = results.getString("Color");
                        float odometer = results.getFloat("Odometer");
                        float price = results.getFloat("Price");

                        vehicles.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));

                    } while (results.next());
                }else{
                    System.out.println("No matches!");
                }
            } catch (SQLException e) {
                System.out.println("Failed to execute query.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to create connection or prepared statement.");
        }
        return vehicles;
    }

    // Search for vehicles by color
    public List<Vehicle> getVehiclesByColor(String colorSelected){
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicles WHERE Color LIKE ?;";
        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1,"%" + colorSelected + "%");

            try(ResultSet results = statement.executeQuery()) {
                while(results.next()) {
                    String vin = results.getString("VIN");
                    int year = results.getInt("Year");
                    String make = results.getString("Make");
                    String model = results.getString("Model");
                    String vehicleType = results.getString("Vehicle_Type");
                    String color = results.getString("Color");
                    float odometer = results.getFloat("Odometer");
                    float price = results.getFloat("Price");

                    vehicles.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }
            } catch (SQLException e) {
                System.out.println("Failed to execute query.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to create connection or prepared statement.");
        }
        return vehicles;
    }

    // Search for vehicles by mileage range
    public List<Vehicle> getVehiclesByMileage(float minMileage, float maxMileage){
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicles WHERE Odometer BETWEEN ? AND ?;";
        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setFloat(1,minMileage);
            statement.setFloat(2,maxMileage);

            try(
                    ResultSet results = statement.executeQuery()
            ) {
                if(results.next()) {
                    do {
                        String vin = results.getString("VIN");
                        int year = results.getInt("Year");
                        String make = results.getString("Make");
                        String model = results.getString("Model");
                        String vehicleType = results.getString("Vehicle_Type");
                        String color = results.getString("Color");
                        float odometer = results.getFloat("Odometer");
                        float price = results.getFloat("Price");

                        vehicles.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));

                    } while (results.next());
                }else{
                    System.out.println("No matches!");
                }
            } catch (SQLException e) {
                System.out.println("Failed to execute query.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to create connection or prepared statement.");
        }
        return vehicles;
    }

    // Search for vehicles by type
    public List<Vehicle> getVehiclesByType(String name){
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicles WHERE Vehicle_Type LIKE ?;";
        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1,"%" + name + "%");

            try(ResultSet results = statement.executeQuery()) {
                while(results.next()) {
                    String vin = results.getString("VIN");
                    int year = results.getInt("Year");
                    String make = results.getString("Make");
                    String model = results.getString("Model");
                    String vehicleType = results.getString("Vehicle_Type");
                    String color = results.getString("Color");
                    float odometer = results.getFloat("Odometer");
                    float price = results.getFloat("Price");

                    vehicles.add(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }
            } catch (SQLException e) {
                System.out.println("Failed to execute query.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to create connection or prepared statement.");
        }
        return vehicles;
    }

    // Add vehicles from database
    public Vehicle insert(Vehicle vehicle){
        Vehicle createdVehicle = null;
        String sql = "INSERT INTO vehicles (vehicleName) VALUES (?)";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, vehicle.getVin());
            statement.setInt(2, vehicle.getYear());
            statement.setString(3, vehicle.getMake());
            statement.setString(4, vehicle.getModel());
            statement.setString(5, vehicle.getVehicleType());
            statement.setString(6, vehicle.getColor());
            statement.setFloat(7, vehicle.getOdometer());
            statement.setFloat(8, vehicle.getPrice());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                createdVehicle = getById(generatedKeys.getInt(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return createdVehicle;
    }

    private Vehicle getById(int id) {
        Vehicle vehicle = null;
        String sql = "SELECT categoryId, categoryName  FROM categories WHERE categoryId = ?;";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rows = statement.executeQuery();
            while(rows.next()){
                Vehicle = new Vehicle(rows.getInt(1), rows.getString(2));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return vehicle;
    }
    // Remove vehicles from database
public boolean

}
