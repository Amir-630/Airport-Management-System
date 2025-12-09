//name: Amir Kelifa Abadura,   id: 1093906
package airportsystem;

import java.util.ArrayList;

/*
* This class represents a specific flight within the airport system.
* It contains all the essential details of a flight, including its airline,
  destination, departure time/day, and the list of passengers on the flight.
*/
class Flight {
    // Instance variables
    private String flightNumber;  //Identifier for each flight
    private String airline;  //Name of the airline operating the flight
    private String destination;  //Final destination of the flight
    private String departureDay;  //Day of departure
    private String departureTime;  //Time of departure in 24-hour format
    private ArrayList<Passenger> passengers;  //List of passengers associated with this flight
    
    // Constructor to initialize a new flight with all necessary details.
    public Flight(String flightNumber, String airline, String destination, String departureDay, String departureTime){
        this.flightNumber= flightNumber;
        this.airline= airline;
        this.destination= destination;
        this.departureDay= departureDay;
        this.departureTime= departureTime;
        this.passengers= new ArrayList<>();  //Initializes the passenger list to be empty initially
    }
    
    // Getter method for flight number.
    public String getFlightNumber() {
        return flightNumber;
    }
    
    // Getter method that returns airline name.
    public String getAirline(){
        return airline;
    }
    
    // Getter method that returns departure time.
    public String getDepartureTime(){
        return departureTime;
    }
    
    // A method to add a new passenger to the fligh.
    public void addPassenger(Passenger p) {
            passengers.add(p); // Adds the passenger to the flight
    }
    
    // A method to remove a passenger from the flight based on their passport number.
    public void removePassenger(String passportNumber) {
        for (Passenger p: passengers) {
            if (p.getPassportNumber().equals(passportNumber)) {
                passengers.remove(p);  //Removes the passenger when match is found.
                break;
            }
        }
    }
    
    // A method to search for a passenger by their passport number
    // returns the matching passenger object, or null if not found.
    public Passenger searchPassenger(String passportNum) {
        for (Passenger p: passengers){
            if (p.getPassportNumber().equals(passportNum))
                return p;
        }
        return null; // Not found
    }
    
    // A method to display full flight information and lists all passengers on the flight.
    public void displayFlightDetails(){
        System.out.println("Flight: " + flightNumber + "  | Airline: " + airline + "  | Destination: " + 
                destination + "  | Departure: " + departureDay + "  " + departureTime);
        System.out.println("Passengers:");
        for (Passenger p: passengers)
            p.displayPassengerDetails();
    }
    
    // A method to check if seat number is already taken or not.
    public boolean isSeatTaken(String seatNum){
        for (Passenger p: passengers){
            if (p.getSeatNumber().equals(seatNum))
                return true;  //Seat is already taken
        }
        return false;  //Seat is not taken
    }
    
    // A validation method to check if the entered day is a valid day of the week.
    public static boolean isDayValid(String day){
        return day.equalsIgnoreCase("Monday") || day.equalsIgnoreCase("Tuesday") || day.equalsIgnoreCase("Wednesday") || day.equalsIgnoreCase("Thursday") || day.equalsIgnoreCase("Friday") || day.equalsIgnoreCase("Saturday") || day.equalsIgnoreCase("Sunday");
    }
    
    // Validates whether a given time string follows the "HH:MM" 24-hour format.
    // This method ensures correct length, colon placement, digit format, and time range.
    public static boolean isTimeValid(String time){
        if (time.length()!=5)
            return false;  //Invalid length      
        if (time.charAt(2)!=':')
            return false;  //Missing or misplaced colon
        String hour=time.substring(0,2);
        String minute=time.substring(3);
        
        // Ensures all characters in hour and minute are digits.
        for (int i=0; i<2; i++){
            if (!Character.isDigit(hour.charAt(i)) || !Character.isDigit(minute.charAt(i)))
                return false;
        }
        // ensures that hour and minute is in a valid range.
        return Integer.parseInt(hour)>0 && Integer.parseInt(hour)<=24 && Integer.parseInt(minute)>=0 && Integer.parseInt(minute)<60;
    }  
}
