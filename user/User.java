package user;

import java.util.Scanner;
import java.util.ArrayList;

public class User{
    private int userID;
    private String fullName;
    private String email;
    private String password;

    public User(int userID, String fullName, String email, String password){
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public User(){
        this.email = "Unknown";
    }

    public int getUserID() {
        return this.userID;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String[] logIn(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your email:");
        String inputEmail = sc.nextLine();

        System.out.println("Please enter your password:");
        String inputPassword = sc.nextLine();

        sc.close();

        String[] logInData = {inputEmail, inputPassword};
        return logInData;

    }
}