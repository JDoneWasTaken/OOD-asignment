package user;

/**
 * Child class of User
 * Admin
 */
public class Admin extends User {
    public Admin(int userID, String username, String password){
        super(userID, username, password);
    }

    @Override 
    public void displayDashboard(){
        System.out.println("===========================================");
        System.out.println("             ADMIN  DASHBOARD              ");
        System.out.println("-------------------------------------------");
        System.out.println("Please choose an option below (1/2/3/4):");
        System.out.println("1. Add event");
        System.out.println("2. Update event");
        System.out.println("3. Log out");
        System.out.println("4. Quit system");
        System.out.println("============================================");
    }
}