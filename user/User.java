package user;

import java.util.Scanner;
import java.util.ArrayList;

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

    public String getFullName() {
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String[] logIn(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your username:");
        String inputUsername = sc.nextLine();

        System.out.println("Please enter your password:");
        String inputPassword = sc.nextLine();

        sc.close();

        String[] logInData = {inputUsername, inputPassword};
        return logInData;

    }
}