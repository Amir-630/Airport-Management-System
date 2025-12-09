//name: Amir Kelifa Abadura,   id: 1093906
package airportsystem;
/*
The passenger class represents a person traveling on a flight.
It Stores essential information such as name, passport number, and seat number.
This class also provides validation for names and a method to display passenger details.
*/
class Passenger {
    //Instance variables
    private String name;   //full name of the passenger
    private String passportNumber; //the passenger's passport number 
    private String seatNumber;  //The assigned seat number on the flight
    
    // Constructor to initialize a new passenger object
    public Passenger(String name, String passportNumber, String seatNumber) {
        this.name= name;
        this.passportNumber= passportNumber;
        this.seatNumber= seatNumber;
    }
    
    // Getter method for passenger's passport number.
    public String getPassportNumber() {
        return passportNumber;
    }
    
    // Getter method that returns seat number.
    public String getSeatNumber() {
        return seatNumber;
    }
    
    // A method to print the passenger's complete details in a formatted way.
    public void displayPassengerDetails() {
        System.out.println("Passenger: " + name + ", Passport: " + passportNumber + ", Seat: " + seatNumber);
    }
    
    // Validation method for name to ensure it does not contain numbers or special sybols.
    public static boolean isNameValid(String s){
        for(int i=0; i<s.length();i++){
            //Checks each chracter one by one to ensure it's a letter(A-Z or a-z).
            char c=s.charAt(i);
            if(!Character.isLetter(c)&&c!=' ')
                return false;
        }
        return true;
    }
    
    //A method to check if a passport is valid or not
    public static boolean isPassportNumberValid(String passportNum) {
        int length=passportNum.length();
        if (length<6 || length>9)  //The passport length should be between 6 and 9 inclusive.
            return false;
        for (int i=0; i<length;i++){  // Loops through the passport to check if it is a letter or a number.
            char c=passportNum.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c))
                return false;
        }
        return true; // The passport is valid
    }
}

