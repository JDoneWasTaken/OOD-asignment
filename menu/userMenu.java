package menu;

import java.util.Scanner;


import main.Main;
import user.Admin;
import user.Attendee;
import user.User;

public class userMenu {
    public static boolean firstPage = true;
    public static boolean signUpPage = false;
    public static boolean logInPage = false;
    public static boolean attendeePage = false;
    public static boolean adminPage = false;
    //1. First Page Method
    public static void firstPage(Scanner sc) {
        String firstOption = null;
            
            while (firstPage){
                System.out.println("Please choose an option (1/2/3):");
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
                    Main.isRunning = false;

               } else {
                    System.out.println("Please enter a valid option (1/2/3).");
                    System.out.println("=========================================");
                    userDashboard.pressEnterToContinue(sc);
            }
        }
    }

    //2. Sign Up page method
    public static void signUpPage(Scanner sc) {
        String inputUsername = null;
        boolean usernameValid = false;
        String inputPassword = null;
        String inputRole = null;
        String adminCode = null;

        while (signUpPage) {
            System.out.println("=============================================================");
            System.out.println("Please enter the following information to create your account.");
            System.out.println("==============================================================");

            while (!usernameValid){
                usernameValid = true;
                System.out.println("Please enter a new username: ");
                inputUsername = sc.nextLine();

                for (User u : Main.userList) {
                    if (u.getUsername().equalsIgnoreCase(inputUsername)) {
                        System.out.println("Username is used, please try a different username");
                        System.out.println("=================================================");
                        userDashboard.pressEnterToContinue(sc);
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
                    int nextId = Main.userList.size() + 1;
                    Attendee newAttendee = new Attendee(nextId, inputUsername, inputPassword, 0);
                    Main.userList.add(newAttendee);

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
                        int nextId = Main.userList.size() + 1;
                        Admin newAdmin = new Admin(nextId, inputUsername, inputPassword);
                        Main.userList.add(newAdmin);

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

    public static void logInPage(Scanner sc) {
        String inputUsername = null;
        String inputPassword = null;
        boolean usernameValid = false;
        boolean passwordValid = false;

        while (logInPage) {
            System.out.println("================================================================");
            System.out.println("Please enter the following information to log into your account.");
            System.out.println("================================================================");

            System.out.println("Please enter your username: ");
            inputUsername = sc.nextLine();

            for (User u : Main.userList) {
                    if (u.getUsername().equalsIgnoreCase(inputUsername)) {
                        usernameValid = true;
                    }
                }
            
            if (usernameValid){
                System.out.println("Please enter your password: ");
                inputPassword= sc.nextLine();

                for (User u : Main.userList) {
                    if (u.getPassword().equals(inputPassword)) {
                        Main.loggedInUser = u; //catch exactly who is the logged in user.

                        if (Main.loggedInUser instanceof Attendee) {
                            System.out.println("Logged in as Attendee!");
                        } else {
                            System.out.println("Logged in as Admin!");
                        }
                        passwordValid = true;
                        logInPage = false;
                    }
                }

                if (!passwordValid) {
                    System.out.println("Wrong password. Please try again.");
                    userDashboard.pressEnterToContinue(sc);
                    logInPage = false;
                    firstPage = true;
                }

            } else {
                System.out.println("This username does not exist, please try again.");
                userDashboard.pressEnterToContinue(sc);
                logInPage = false;
                firstPage = true;
            }
        }
    }
}
