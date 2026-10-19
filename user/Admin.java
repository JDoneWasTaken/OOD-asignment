package user;

public class Admin extends User {
    public Admin(int userID, String username, String password){
        super(userID, username, password);
    }

    @Override 
    public void displayDashboard(){
        System.out.println("==================================");
        System.out.println("         ADMIN  DASHBOARD         ");
        System.out.println("==================================");
        System.out.println("Please choose an option below:");
        System.out.println("1. Add event");
        System.out.println("2. Manage event");
        System.out.println("3. Upadate event");
        System.out.println("4. Log out");
        System.out.println("5. Quit system");
    }
}