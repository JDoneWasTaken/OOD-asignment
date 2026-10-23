package user;

/**
 * Parent class of admin and attendee
 * User
 */
public class User{
    private int userID;
    private String username;
    private String password;

    public User(int userID, String username, String password){
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public User(){
        this.username = "Unknown";
    }

    public int getUserID() {
        return this.userID;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
    
    /**
     * Display menu for each child
     */
    public void displayDashboard(){
        System.out.println("(Dashboard placeholder)");
    }
}