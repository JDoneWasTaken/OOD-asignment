package user;

public class Attendee extends User {
    int loyaltyPoint = 0;


    public Attendee(int userID, String username, String password){
        super(userID, username, password);
    }

    @Override 
    public void displayDashboard(){
        System.out.println("=====================================");
        System.out.println("         ATTENDEE  DASHBOARD         ");
        System.out.println("=====================================");
        System.out.println("Please choose an option below:");
        System.out.println("1. View Upcoming Schedule");
        System.out.println("2. Make Reservation");
        System.out.println("3. Review past booking");
        System.out.println("4. Log out");
        System.out.println("5. Quit system");


    }

    public void viewSchedule(){

    }

    public void makeReservation(){

    }

    public void reviewBooking(){

    }
}
