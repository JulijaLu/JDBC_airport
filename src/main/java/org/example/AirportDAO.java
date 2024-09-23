package org.example;

import java.sql.*;
import java.util.ArrayList;

public class AirportDAO {
    public static String url = "jdbc:mysql://localhost:3306/airports";

    public static void create(Airport airport) {
        String query = "INSERT INTO sb_airports (biz_name, address, city) VALUES (?, ?, ?);";
        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, airport.getBizName());
            preparedStatement.setString(2, airport.getAddress());
            preparedStatement.setString(3, airport.getCity());
            preparedStatement.executeUpdate();
            System.out.println("Pavyko sukurti naują įrašą!");
        } catch (SQLException e) {
            System.out.println("Nepavyko sukurti naujo įrašo!" + "Plačiau: " + e.getMessage());
        }
    }

    public static void update(Airport airport2) {
        String query = "UPDATE sb_airports SET biz_name = ?, address = ?, city = ? WHERE biz_id = ?;";
        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, airport2.getBizName());
            preparedStatement.setString(2, airport2.getAddress());
            preparedStatement.setString(3, airport2.getCity());
            preparedStatement.setInt(4, airport2.getBizId());
            preparedStatement.executeUpdate();
            System.out.println("Pavyko atnaujinti įrašą!");
        } catch (SQLException e) {
            System.out.println("Nepavyko atnaujinti įrašo!" + e.getMessage());
        }
    }

    public static void delete(int bizId) {
        String query = "DELETE FROM sb_airports WHERE biz_id = ?;";
        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bizId);
            preparedStatement.executeUpdate();
            System.out.println("Irasa istrinti pavyko");
        } catch (SQLException e) {
            System.out.println("Nepavyko istrinti irsao " + e.getMessage());
        }
    }

    public static Airport search(int bizId) throws SQLException {
        String query = "SELECT * FROM sb_airports WHERE biz_id = ?;";
        ArrayList<Airport> airports = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bizId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                airports.add(new Airport(
                        result.getInt("biz_id"),
                        result.getString("biz_name"),
                        result.getString("address"),
                        result.getString("city")));
            }
            connection.close();
            preparedStatement.close();
            System.out.println("Vykdoma paieska pagal ID: " + bizId);
        } catch (SQLException e) {
            System.out.println("Ivyko klaida vykdant paieska pagal ID " + bizId);
            e.printStackTrace();
        }
        return airports.get(0);
    }

    public static ArrayList<Airport> read(int bizId) {
        String query = "SELECT * FROM sb_airports WHERE biz_id = ?;";
        ArrayList<Airport> airports = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bizId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String bizName = resultSet.getString("biz_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");

                Airport airport = new Airport(bizName, address, city);
                airports.add(airport);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Nuskaityti nepavyko " + e.getMessage());
        }
        return airports;
    }

    public static ArrayList<Airport> returnAirports(String city) {
        String query = "SELECT * FROM `sb_airports` WHERE city = ?;";
        ArrayList<Airport> airports = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int bizId = resultSet.getInt("biz_id");
                String bizName = resultSet.getString("biz_name");
                String address = resultSet.getString("address");

                Airport airport = new Airport(bizId, bizName, address);
                airports.add(airport);
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Nepavyko nieko rasti: " + e.getMessage());
        }
        return airports;
    }
}