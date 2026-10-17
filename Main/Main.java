package main;

import java.util.Scanner;
import java.util.ArrayList;
import user.User;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        ArrayList<User> userList = new ArrayList<>();
        ArrayList<User> eventList = new ArrayList<>();

        boolean loginCheck = false;
        boolean quitSystem = false;

        User user1 = new User(1, "Joseph", "joseph@gmail.com", "12345678");

        userList.add(user1);

        while (!quitSystem){
            System.out.println("=========================================");
            System.out.println(" Welcome to the Event Ticketing System");
            System.out.println("=========================================");

            String firstOption = "signed out";
            
            while ((!firstOption.equals("1")) && (!firstOption.equals("2"))){
                System.out.println("Please choose an option (1 or 2):");
                System.out.println("1. Log In");
                System.out.println("2. Sign Up (if you don't have an account yet)");
                firstOption = sc.nextLine();
                if ((firstOption.equals("1")) || (firstOption.equals("2"))){
                    System.out.println(firstOption);
                    break;
                }

                System.out.println("Please enter a valid option (1 or 2).");
            }
            quitSystem = true;
        }

        User u = new User();

        String[] logInData = u.logIn();
        System.out.println(logInData);
    }
}