package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner s = new Scanner(System.in);
        System.out.println("Iveskite iraso numeri, kuri norite pasalinti: ");
        int id = s.nextInt();
        AirportDAO.delete(id);

        Airport airport1 = new Airport("Palango soro uostas", "Naujininkų rajonas", "Palanga");
        AirportDAO.create(airport1);

        Airport airport2 = new Airport(5, "LAX", "Air street", "LA");
        AirportDAO.update(airport2);


        Airport airport3 = AirportDAO.search(15);
        if (airport3 == null) {
            System.out.println("Nerasta irasu");
        } else {
            System.out.println(airport3);
        }

        Scanner s = new Scanner(System.in);
        System.out.println("Iveskite oro uosto ID, kuri norite rasti: ");
        int id = s.nextInt();
        ArrayList<Airport> uostai = AirportDAO.read(id);
        if (uostai.isEmpty()) {
            System.out.println("Pagal ivesta ID rezultatu nerasta");
        } else {
            System.out.println(uostai);
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Iveskite miesta, kurio oro uostus norite rasti: ");
        String city = scan.nextLine();
        ArrayList<Airport> oroUostai = AirportDAO.returnAirports(city);
        if (oroUostai.isEmpty()) {
            System.out.println("Pagal ivesta ID rezultatu nerasta");
        } else {
            System.out.print(oroUostai);
        }
    }
}