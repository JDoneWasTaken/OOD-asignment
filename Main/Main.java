package main;

import java.util.Scanner;
import java.util.ArrayList;
import user.User;
import user.Admin;
import user.Attendee;


public class Main{
    static boolean isRunning = true;
    static boolean firstPage = true;
    static boolean signUpPage = false;
    static boolean logInPage = false;
    static boolean attendeePage = false;
    static boolean adminPage = false;


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        ArrayList<User> userList = new ArrayList<>();
        ArrayList<User> eventList = new ArrayList<>();

        Admin user1 = new Admin(1, "Joseph", "12345678");
        Attendee user2 = new Attendee(2, "Bennett", "87654321");

        userList.add(user1);
        userList.add(user2);

        while (isRunning){
            System.out.println("=========================================");
            System.out.println("Welcome to the Event Ticketing System");
            System.out.println("=========================================");
            
            firstPage(sc);
            
            sc.close();
            isRunning = false;
        }

        /*User u = new User();

        String[] logInData = u.logIn();
        System.out.println(logInData); */
    }

    public static void firstPage(Scanner sc) {
        String firstOption = "0";
            
            while (firstPage){
                System.out.println("Please choose an option (1, 2 or 3):");
                System.out.println("1. Log In");
                System.out.println("2. Sign Up (if you don't have an account yet)");
                System.out.println("3. Quit System.");
                firstOption = sc.nextLine();

               if (firstOption.equals("1")){
                    System.out.println(firstOption);
                    System.out.println("=========================================");
                    firstPage = false;
                    logInPage = true;

               } else if (firstOption.equals("2")) {
                    System.out.println(firstOption);
                    System.out.println("=========================================");
                    firstPage = false;
                    signUpPage = true;

               } else if (firstOption.equals("3")) {
                    System.out.println(firstOption);
                    System.out.println("=========================================");
                    firstPage = false;
                    isRunning = false;

               } else {
                    System.out.println("Please enter a valid option (1, 2 or 3).");
                    System.out.println("=========================================");
            }
        }
    }

    public static void signInPage(Scanner sc) {
        String username = "empty";
        String password = "empty";

        while (signUpPage) {
            System.out.println("=========================================");
            System.out.println("Please enter the following information to create your account.");
            System.out.println("=========================================");
            System.out.println("username:  ");
            username = sc.nextLine();

        }
    }

    public static void logInPage(Scanner sc) {
        while (logInPage) {

        }
        /*User u = new User();

        String[] logInData = u.logIn();
        System.out.println(logInData); */
    }


}