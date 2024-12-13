/*
UserInterface will be responsible for all output to the screen, reading of user input,
and "dispatching" of the commands to the Dealership as needed. (ex: when the user selects
"List all Vehicles", UserInterface would call the appropriate Dealership method and then
display the vehicles it returns.)
 */

package com.pluralsight.dealership_api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);

    VehicleDao vehicleDao;
    Dealership dealership;
    Vehicle vehicle;


    // Parameterless constructor
    public UserInterface(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    private void init() {
        this.dealership = DealershipFileManager.getDealership();
    }
    public void display() {
        // call init method
        init();


        while(true) {

            // Display the menu
            System.out.print("""
                    🚗 Welcome to the Car Dealership App! 🚗
                    -----------------------------------------
                    Select an option to continue.
                    1 - Find vehicles within a price range
                    2 - Find vehicles by make / model
                    3 - Find vehicles by year range
                    4 - Find vehicles by color
                    5 - Find vehicles by mileage range
                    6 - Find vehicles by type (car, truck, SUV, van)
                    7 - List ALL vehicles
                    8 - Add a vehicle
                    9 - Remove a vehicle
                    10 - Sell/Lease a Vehicle
                    99 - Quit
                    ˚‧｡⋆🌻⋆｡‧˚
                    Enter your option: \
                    """);

            // Read user command
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumes the new line character

            // Switch statement that calls the correct process() method to display menu
            switch (option){
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    processSellLeaseVehicleRequest();
                    break;
                case 99:
                    System.out.println("Thank you, come back soon!");
                    System.out.println("\uD80C\uDD9D \uD80C\uDD9F \uD80C\uDD9E \uD80C\uDD9D 🐈‍");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please enter a valid choice (1,2,3,4,5,6,7,8,9, or 99).");
                    break;
            }
        }
    }

    private void processSellLeaseVehicleRequest() {
        System.out.print("Sale (S) or Lease (L): ");
        String typeOfContract = scanner.nextLine();

        if(typeOfContract.equalsIgnoreCase("S")){
            processSaleContract();

        } else if (typeOfContract.equalsIgnoreCase("L")) {
             processLeaseContract();
        } else {
        }
    }

    public void processSaleContract(){
        SalesContract salesContract;
        System.out.print("Enter date of Contract: ");
        String date = scanner.nextLine().trim();
        System.out.print("Enter the customer's name: ");
        String customerName = scanner.nextLine().trim();
        System.out.print("Enter the customer's email: ");
        String customerEmail = scanner.nextLine().trim();
        // Find the vehicle
        System.out.print("Enter the VIN of the vehicle: ");
        String vin = scanner.nextLine();
        vehicle = dealership.getVehicleByVin(vin);  // get the vehicle if it's available


        boolean isSold = (vehicle == null); // the vehicle is sold if it's null

        System.out.print("Would you like to finance (Y) for yes and (N) for no: ");
        boolean finance = (scanner.nextLine().trim().equalsIgnoreCase("Y"));
        salesContract = new SalesContract(date,customerName,customerEmail, isSold, finance);
        salesContract.setOriginalPrice(vehicle.getPrice());

        System.out.printf("The total price to purchase this vehicle is: %.2f\n", salesContract.getTotalPrice());
        System.out.printf("The monthly payment is: %.2f\n", salesContract.getMonthlyPayment());
        
    }

    public void processLeaseContract(){
        Vehicle vehicle;
        SalesContract leaseContract;

        System.out.print("Enter the VIN of the vehicle: ");
        String vin = scanner.nextLine();
        vehicle = dealership.getVehicleByVin(vin);

        LocalDateTime localDateTime = LocalDateTime.now();
        int yearsOld = localDateTime.getYear() - vehicle.getYear();
        if(yearsOld > 3){
            System.out.println("You cannot lease a vehicle over 3 years old.");
        }else{



        }
        // leaseContract = new LeaseContract();
        //processGetOriginalPrice();
    }

    // Methods
    public void processGetByPriceRequest(){
        System.out.print("Enter the minimum price: ");
        float min = scanner.nextFloat();
        System.out.print("Enter the maximum price: ");
        float max = scanner.nextFloat();
        scanner.nextLine();

        //List<Vehicle> vehicleList = dealership.getVehiclesByPrice(min, max);
        List<Vehicle> vehicleList = vehicleDao.getVehiclesByPrice(min, max);
        displayVehicles(vehicleList);
    }
    public void processGetByMakeModelRequest(){
        System.out.print("Enter the make or model: ");
        String name = scanner.nextLine().trim();
//        System.out.print("Enter the model: ");
//        String model = scanner.nextLine().trim();

        //List<Vehicle> vehicleList = dealership.getVehiclesByMakeModel(make, model);
        List<Vehicle> vehicleList = vehicleDao.getVehiclesByMakeModel(name);
        displayVehicles(vehicleList);
    }

    public void processGetByYearRequest(){
        System.out.print("Enter minimum year: ");
        int yearMin = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int yearMax = scanner.nextInt();
        scanner.nextLine();

        //List<Vehicle> vehicleList = dealership.getVehiclesByYear(yearMin, yearMax);
        List<Vehicle> vehicleList = vehicleDao.getVehiclesByYear(yearMin, yearMax);
        displayVehicles(vehicleList);
    }
    public void processGetByColorRequest(){
        System.out.print("Enter color: ");
        String color = scanner.nextLine().trim();

        //List<Vehicle> vehicleList = dealership.getVehiclesByColor(color);
        List<Vehicle> vehicleList = vehicleDao.getVehiclesByColor(color);
        displayVehicles(vehicleList);
    }
    public void processGetByMileageRequest(){
        System.out.print("Enter minimum mileage: ");
        float minMileage = scanner.nextFloat();
        System.out.print("Enter maximum mileage: ");
        float maxMileage = scanner.nextFloat();
        scanner.nextLine();

        //List<Vehicle> vehicleList = dealership.getVehiclesByMileage(minMileage, maxMileage);
        List<Vehicle> vehicleList = vehicleDao.getVehiclesByMileage(minMileage, maxMileage);
        displayVehicles(vehicleList);
    }
    public void processGetByVehicleTypeRequest(){
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine().trim();

        //List<Vehicle> vehicleList = dealership.getVehiclesByType(vehicleType);
        List<Vehicle> vehicleList = vehicleDao.getVehiclesByType(vehicleType);
        displayVehicles(vehicleList);
    }
    public void processGetAllVehiclesRequest(){
         processAllVehiclesRequest();
    }
    public void processAddVehicleRequest(){
        System.out.print("Enter the vin: ");
        String vin = scanner.nextLine();
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the make: ");
        String make = scanner.nextLine().trim();
        System.out.print("Enter the model: ");
        String model = scanner.nextLine().trim();
        System.out.print("Enter the vehicle type: ");
        String vehicleType = scanner.nextLine().trim();
        System.out.print("Enter the color: ");
        String color = scanner.nextLine().trim();
        System.out.print("Enter the mileage: ");
        float odometer = scanner.nextFloat();
        System.out.print("Enter the price: ");
        float price = scanner.nextFloat();

        dealership.addVehicle(new Vehicle(vin,year,make,model,vehicleType,color,odometer,price));
        DealershipFileManager.saveDealership(dealership);
        System.out.println("Your vehicle has been successfully added.");
    }
    public void processRemoveVehicleRequest(){
        System.out.print("Enter the vin of the vehicle you want to remove: ");
        String vin = scanner.nextLine();

        dealership.removeVehicle(new Vehicle(vin,0,"","","","",0,0));
        DealershipFileManager.saveDealership(dealership);
        System.out.println("Your vehicle has been successfully removed.");
    }

    // Helper methods
    private void displayVehicles(List<Vehicle> vehicleList){
        System.out.printf("%-19s|%-6s|%-15s|%-10s|%-14s|%-8s|%-10s|%-10s\n",
                "VIN","Year","Make","Model","Vehicle_Type","Color","Odometer","Price");
        System.out.println("----------------------------------------------------------------------------------------------");

        for(Vehicle vehicle: vehicleList){
            System.out.println(vehicle);
        }
        System.out.println();
    }

    public void processAllVehiclesRequest(){
        // get all vehicles
        List<Vehicle> vehicleList = dealership.getAllVehicles();
        // call method to display the vehicles
        displayVehicles(vehicleList);
    }


}
