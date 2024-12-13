package com.pluralsight.dealership_api;

public class Program {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println(
                    "Application needs two arguments to run: " +
                            "java com.hca.jdbc.UsingDriverManager <username> " +
                            "<password>");
            System.exit(1);
        }

        // Get the username and password
        String username = args[0];
        String password = args[1];

        VehicleDao vehicleDao = new VehicleDao(username,password);


        // Instance of the UserInterface class and call its display method
        UserInterface userInterface = new UserInterface(vehicleDao);
        userInterface.display();

    }
}