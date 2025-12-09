//name: Amir Kelifa Abadura,   id: 1093906
package airportsystem;

import java.util.ArrayList;

/*
* This class represents a single airport that manages multiple flights.
* It also provides functionality to add, remove, search, sort, and display flights.
*/
class Airport {
    // Instance variables
    private final String AIRPORTName="Abu Dhabi International Airport";  //The name of the airport
    private ArrayList<Flight> flights;  //An Arraylist to store all flights currently managed by the airport.
    
    // Constructor to create an Airport object.
    public Airport() {
        flights= new ArrayList<>();  //Initialize flight list to store all the flights hosted by the airport.
    }
    
    // Getter method that returns the name of the airport.
    public String getAirportName(){
        return AIRPORTName;
    }
    
    // A method to add new flights to the airport's list.
    public void addFlight(Flight f) {
        flights.add(f);
    }
    
    // A method to remove a flight based on its flight number from the airport.
    public void removeFlight(String flightNumber) {
        for (Flight f: flights) {
            if (f.getFlightNumber().equals(flightNumber)) {
                flights.remove(f); // Removes the flight when a match is found
                break;
            }
        }
    }
    
    // A method to search for a flight by its flight number
    // It returns the matching Flight object, or null if not found.
    public Flight searchFlight(String flightNumber) {
        for (Flight f: flights){
            if (f.getFlightNumber().equals(flightNumber))
                return f;
        }
        return null;  //Flight not found
    }
    
    // A method to sort the list of flights based on airline names in alphabetical order.
    public void sortFlightByAirlines(){
        for (int i=0; i<flights.size(); i++){
            for (int j=i+1; j<flights.size(); j++){
                // compareToIgnoreCase() ensures case-insensitive sorting
                if (flights.get(i).getAirline().compareToIgnoreCase(flights.get(j).getAirline())>0){
                    // Swap flights[i] and flights[j]
                    Flight temp=flights.get(i);
                    flights.set(i, flights.get(j));
                    flights.set(j, temp);
                }
            }
        }
    }
    
    // A method sort the list of flights by their departure times in ascending order.
    public void sortFlightByDepartureTime(){
        for (int i=0; i<flights.size(); i++){
            for (int j=i+1; j<flights.size();j++){
                if (flights.get(i).getDepartureTime().compareToIgnoreCase(flights.get(j).getDepartureTime())>0){
                    // Swap flights[i] and flights[j] if they are not already in order.
                    Flight temp=flights.get(i);
                    flights.set(i, flights.get(j));
                    flights.set(j, temp);
                }
            }
        }
    }
    
    // A method that displays detailed information about every flight managed by the airport.
    public void displayAllFlights() {
        for (Flight f: flights){
            f.displayFlightDetails();  //Calls method from Flight class
            System.out.println("----------------------------------------------------------------------------------------------");
        }
    }
}
