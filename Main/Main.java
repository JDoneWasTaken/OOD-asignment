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
    static User loggedInUser = null;


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        ArrayList<User> userList = new ArrayList<>();
        //ArrayList<Event> eventList = new ArrayList<>();

        Admin user1 = new Admin(1, "Joseph", "12345678");
        Attendee user2 = new Attendee(2, "Bennett", "87654321");

        userList.add(user1);
        userList.add(user2);

        while (isRunning){
            System.out.println("=========================================");
            System.out.println("Welcome to the Event Ticketing System");
            System.out.println("=========================================");
            
            firstPage(sc);

            signUpPage(sc, userList);

            logInPage(sc, userList);
            
            isRunning = false;
        }
        sc.close();
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
                    firstPage = false;
                    logInPage = true;

               } else if (firstOption.equals("2")) {
                    System.out.println(firstOption);
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

    public static void signUpPage(Scanner sc, ArrayList<User> userList) {
        String inputUsername = "empty";
        boolean usernameValid = false;
        String inputPassword = "empty";
        String inputRole = "empty";
        String adminCode = "0";

        while (signUpPage) {
            System.out.println("=============================================================");
            System.out.println("Please enter the following information to create your account.");
            System.out.println("==============================================================");

            while (!usernameValid){
                usernameValid = true;
                System.out.println("Please enter a new username: ");
                inputUsername = sc.nextLine();

                for (User u : userList) {
                    if (u.getUsername().equalsIgnoreCase(inputUsername)) {
                        System.out.println("Username is used, please try a different username");
                        System.out.println("=================================================");
                        usernameValid = false;
                    }
                }
            }

            System.out.println("Please enter a password: ");
            inputPassword = sc.nextLine();

            while (!(inputRole == "Attendee") && !(inputRole == "Admin")){
                System.out.println("Are you registering as an Attendee or Admin?");
                inputRole = sc.nextLine();
                if (inputRole.equalsIgnoreCase("Attendee")) {
                    //create and reocrd a new attendee
                    int nextId = userList.size() + 1;
                    Attendee newAttendee = new Attendee(nextId, inputUsername, inputPassword);
                    userList.add(newAttendee);

                    System.out.println("Your attendee account is successfully created!");
                    System.out.println("==============================================");
                    signUpPage = false;
                    firstPage = true;
                    break;
                } else if (inputRole.equalsIgnoreCase("Admin")){
                    System.out.println("Please enter your admin code:");
                    adminCode= sc.nextLine();
                    if (adminCode.equals("0067")) {
                        System.out.println("Your admin account is successfully created!");
                        System.out.println("===========================================");
                        //create and record a new admin
                        int nextId = userList.size() + 1;
                        Admin newAdmin = new Admin(nextId, inputUsername, inputPassword);
                        userList.add(newAdmin);

                        signUpPage = false;
                        firstPage = true;
                        break;
                    } else {
                        System.out.println("Invalid admin code.");
                        System.out.println("==================================");
                        inputRole = "empty";
                        adminCode = "0";
                    }
                } else {
                    System.out.println("Please enter 'Attendee' or 'Admin' only.");
                    System.out.println("========================================");
                }
            }
        }
    }

    public static void logInPage(Scanner sc, ArrayList<User> userList) {
        String inputUsername = "empty";
        String inputPassword = "empty";
        boolean usernameValid = false;
        boolean passwordValid = false;

        while (logInPage) {
            System.out.println("================================================================");
            System.out.println("Please enter the following information to log into your account.");
            System.out.println("================================================================");

            System.out.println("Please enter your username: ");
            inputUsername = sc.nextLine();

            for (User u : userList) {
                    if (u.getUsername().equalsIgnoreCase(inputUsername)) {
                        usernameValid = true;
                    }
                }
            
            if (usernameValid){
                System.out.println("Please enter your password: ");
                inputPassword= sc.nextLine();

                for (User u : userList) {
                    if (u.getPassword().equals(inputPassword)) {
                        loggedInUser = u; //catch exactly who is the logged in user.

                        if (loggedInUser instanceof Attendee) {
                            System.out.println("Logged in as Attendee!");
                        } else {
                            System.out.println("Logged in as Admin!");
                        }
                        passwordValid = true;
                        logInPage = false;
                        //User(attendee/admin)page = true;
                    }
                }

                if (!passwordValid) {
                    System.out.println("Wrong password. Please try again.");
                    logInPage = false;
                    firstPage = true;
                }

            } else {
                System.out.println("This username does not exist, please try again.");
                logInPage = false;
                firstPage = true;
            }
        }
    }


}