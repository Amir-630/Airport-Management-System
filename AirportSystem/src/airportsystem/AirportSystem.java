//name: Amir Kelifa Abadura,   id: 1093906
/*
* This System is designed to simulate the core operation of a modern airport.
  It manages flights and passengers efficiently using a structured object-oriented design
*/
package airportsystem;

import java.util.Scanner;

/*
* This AirportSystem class serves as the main driver class for the airport management
  system.It coordinates between the Airport, Flight, and Passenger classes and 
  provides a menu-driven interface for the user to manage flights and passengers.
*/
public class AirportSystem {
    
    // Main method to run the system.
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);  //Create a Scanner object for reading user input
        
        // create an Airport object to manage flights and passengers.
        Airport airport = new Airport();
        
        //Example Flights (pre-defined flights for demonstration purposes.)
        Flight flight1= new Flight("EY100", "Etihad Airways", "Oman", "Monday", "10:30");
        Flight flight2= new Flight("EY101", "Air India", "Mumbai", "Monday", "14:30");
        Flight flight3= new Flight("EY102", "British Airways", "London", "Monday", "8:00");
        Flight flight4= new Flight("EY103", "Egyptair", "Cairo", "Monday", "10:00");
        Flight flight5= new Flight("EY104", "Qatar Airways", "Doha", "Monday", "16:30");
        // Add the above flights to the airport
        airport.addFlight(flight1);
        airport.addFlight(flight2);
        airport.addFlight(flight3);
        airport.addFlight(flight4);
        airport.addFlight(flight5);
        
        //Example Passengers (pre-defined Passengers for demonstration.)
        Passenger passenger1= new Passenger("Mohammed Al Mansoori", "ZK12345", "4LB");
        Passenger passenger2= new Passenger("Omar Al Nuaimi", "Z546545", "12RA");
        Passenger passenger3= new Passenger("Aisha Al Dhaheri", "ZK12089", "15LC");
        Passenger passenger4= new Passenger("Sara Ibrahim", "Z912745", "8RB");
        Passenger passenger5= new Passenger("Amir Kalefa", "ET97605", "24LA");
        // Add passengers to one of the flights
        flight1.addPassenger(passenger1);
        flight1.addPassenger(passenger2);
        flight1.addPassenger(passenger3);
        flight2.addPassenger(passenger4);
        flight2.addPassenger(passenger5);
        
        // Main menu loop - user interaction begins here
        // Repeatedly shows the menu until user chooces to exit.
        while (true) {
            menu();  //Display the list of options
            String choice=sc.nextLine(); //Read the user's choice

            switch (choice) {
                case "1":
                    //Adding new flight to the airport
                    String flightNumber;
                    // Asks the user for flight number until they enter a unique flight number.
                    do {
                        System.out.print("Enter flight number: ");
                        flightNumber= sc.nextLine();
                        if (airport.searchFlight(flightNumber)!=null)  //Checks if the flight already exists
                            System.out.println("A flight with this flight number already exists. Please enter another unique flight number!");
                    } while(airport.searchFlight(flightNumber)!=null);  //Checks if the flight already exists
                    
                    System.out.print("Enter airline name: ");
                    String airline= sc.nextLine();
                    System.out.print("Enter destination: ");
                    String destination= sc.nextLine();
                    String departureDay;
                    //Validate day input until it is valid
                    do {
                        System.out.print("Enter departure day: ");
                        departureDay= sc.nextLine();
                        if (!Flight.isDayValid(departureDay))
                            System.out.println("Invalid day, Please enter a full name of the day (ex. monday)");
                    } while (!Flight.isDayValid(departureDay));
                    
                    String departureTime;
                    //Validate time input based on the given format.
                    do {
                        System.out.print("Enter departure time (HH:MM) in 24-hour format: ");
                        departureTime= sc.nextLine();
                        if (!Flight.isTimeValid(departureTime)){
                            System.out.println("The Entered time is not valid.");
                            System.out.println("Please follow the given format! (ex. 09:30, 14:00)");
                        }
                    } while (!Flight.isTimeValid(departureTime));
                    
                    // Creating and adding a new flight to the airport using the above information
                    airport.addFlight(new Flight(flightNumber, airline, destination, departureDay, departureTime));
                    System.out.println("Flight added successfully!");
                    break;
                case "2":
                    // Adding new passenger to a flight
                    System.out.print("Enter flight number: ");
                    String flightNum= sc.nextLine();
                    Flight flight= airport.searchFlight(flightNum);  // checks if flight with the given flight number exists.
                    if (flight!= null) { 
                        String name;
                        // Validate name until it's correct (only alphabetic characters).
                        do {
                            System.out.print("Enter passenger Name: ");
                            name= sc.nextLine();
                            if (!Passenger.isNameValid(name))
                                System.out.println("Name can only contain letters.");
                        } while (!Passenger.isNameValid(name));
                        
                        String passport;
                        // Validates passport number until a correct passport is entered
                        do {
                            System.out.print("Enter passport number: ");
                            passport= sc.nextLine();  //reads passport number from the user
                            if (!Passenger.isPassportNumberValid(passport))  //Checks if the passport is valid
                                System.out.println("Passport number: \n-Should be atleas 6 characters long and atmost 9 characters long \n-Can only contain letters and numbers(digits).");
                        } while (!Passenger.isPassportNumberValid(passport));  //Checks if the passport is valid
                        
                        String seat;
                        //reaads seat number from the user ensuring it is not occupied twice.
                        do {
                            System.out.print("Enter seat number: ");
                            seat= sc.nextLine();  //reads and stores seat numbers
                            if (flight.isSeatTaken(seat))  //checks if seat number is already occupied or not
                                System.out.println(seat + " is already occupied. Please enter another seat number.");
                        } while (flight.isSeatTaken(seat));  //checks if seat number is already occupied or not
                        
                        // creating and adding new passenger to the selected flight.
                        if (flight.searchPassenger(passport) == null){  //Checks if the passenger already exists
                            flight.addPassenger(new Passenger(name, passport, seat));  //Adds the passenger to the flight
                            System.out.println("Passenger added successfully!");
                        } else  //prints this message if the passenger already exists
                            System.out.println("Passenger with this passport already exists in this flight!");
                    } else {
                        //only excutes if flight is not found.
                        System.out.println("Flight not found!");
                    }
                    break;
                case "3":
                    //Removing flight from the airport
                    System.out.print("Enter flight number to remove: ");
                    String removeFlightNum= sc.nextLine();
                    // Search for the flight and remove it if found
                    if (airport.searchFlight(removeFlightNum)!=null){
                        airport.removeFlight(removeFlightNum);  // Calls a method to remove the flight
                        System.out.println("Flight removed successfully!");
                    } else {
                        //Prints the following if the flight is not found
                        System.out.println("Sorry, Flight with " + removeFlightNum + " number not found.");
                    }
                    break;
                case "4":
                    //Remove passenger from a flight
                    System.out.print("Enter flight number: ");
                    String flighNo= sc.nextLine();
                    Flight f= airport.searchFlight(flighNo); // Search and store the flight.
                    if (f!=null) { // If the flight is found, proceed to remove the passenger
                        System.out.print("Enter passenger passport number: ");
                        String passNum= sc.nextLine();
                        // Search for the passenger and remove it if found.
                        if (f.searchPassenger(passNum)!=null) {
                            f.removePassenger(passNum);  // Calls a method to remove the passenger
                            System.out.println("Passenger removed successfully!");
                        } else
                            //Prints the following if the Passenger is not found
                            System.out.println("Passenger not found!");
                    } else 
                        //Prints the following if the flight is not found
                        System.out.println("Flight not found!");
                    break;
                case "5":
                    //Search and display a flight's details
                    System.out.print("Enter flight number to search: ");
                    String searchFlightNum= sc.nextLine();
                    Flight foundFlight= airport.searchFlight(searchFlightNum);  // Search and store the flight.
                    // Display details if flight exists
                    if (foundFlight!= null)
                        foundFlight.displayFlightDetails();  // calls a method to display flight details
                    else
                        System.out.println("Flight not found");
                    break;
                case "6": // Sort Flights based on airline name or flight departure time.
                    // Show options to the user and read the user's input
                    System.out.println("1. Sort flights by departure time.");
                    System.out.println("2. Sort flights by airline names.");
                    System.out.print("Enter your option (1 or 2): ");
                    char c=sc.nextLine().charAt(0);
                    
                    if (c=='1'){  // Sort by departure time
                        airport.sortFlightByDepartureTime(); //Calls a method to sort the flights
                        airport.displayAllFlights();  // calls a method to display the sorted flights
                    } else if (c=='2'){  // Sort alphabetically by airline names
                        airport.sortFlightByAirlines();  // Calls a method to sort the flights
                        airport.displayAllFlights(); // calls a method to display the sorted flights
                    } else 
                        System.out.println("Invalid input!");
                    break;
                case "7": 
                    //Display all flights
                    airport.displayAllFlights(); // calls a method to display all the flights
                    break;
                case "8":
                    // Exit the system
                    System.out.println("Exiting the system. Thank you for using our airport management system!");
                    sc.close();  //Close the Scanner object
                    return;  // Exit from the main method ending the program
                default:
                    //If the user enters an invalid input
                    System.out.println("Invalid choice! Please select again.");
            }
            // Print a line separator after each complete interaction for better readability.
            System.out.println("*******************************************************************************");
        }
    }
    
    // This method displays a menu of options for the user.
    public static void menu(){
        System.out.println("\nAirport Management System:");
        System.out.println("1. Add Flight");
        System.out.println("2. Add Passenger to Flight");
        System.out.println("3. Remove Flight");
        System.out.println("4. Remove Passenger from Flight");
        System.out.println("5. Search Flight");
        System.out.println("6. Sort Flights");
        System.out.println("7. Display All Flights");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
    }    
}
