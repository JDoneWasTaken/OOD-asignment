package user;

public class Attendee extends User {
    int loyaltyPoint = 0;


    public Attendee(int userID, String username, String password, int loyaltyPoint){
        super(userID, username, password);
        this.loyaltyPoint = loyaltyPoint;
    }

    public int getLoyaltyPoint() {
        return this.loyaltyPoint;
    }

    public void setLoyaltyPoint(int loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }

    @Override 
    public void displayDashboard(){
        System.out.println("===========================================");
        System.out.println("            ATTENDEE  DASHBOARD            ");
        System.out.println("-------------------------------------------");
        System.out.println("Please choose an option below (1/2/3/4):");
        System.out.println("1. View Upcoming Schedule");
        System.out.println("2. Review past booking");
        System.out.println("3. View digital tickets");
        System.out.println("4. Log out");
        System.out.println("5. Quit system");
        System.out.println("===========================================");


    }

    public void viewSchedule(){

    }

    public void makeReservation(){

    }

    public void reviewBooking(){

    }
}
