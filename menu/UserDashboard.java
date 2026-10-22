package menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import main.Main;
import user.Attendee;


public class UserDashboard {
    public static void pressEnterToContinue(Scanner sc) {
        System.out.println("\nPress enter to continue...");
        sc.nextLine();
    }

    public static boolean checkDate(String dateInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate parsedDate = LocalDate.parse(dateInput, formatter);
            return parsedDate.isAfter(LocalDate.now());
            
        } catch (DateTimeParseException e) {
            return false;
        }

    }

    public static void dashboardPage(Scanner sc) {
        String userInput = null;
        while (Main.loggedInUser != null){
            Main.loggedInUser.displayDashboard();
            userInput = sc.nextLine();

            if (Main.loggedInUser instanceof Attendee) {
                //This is an Attendee
                /*Options for reference:
                1. View Upcoming Schedule
                2. Make Reservation
                3. Review past booking
                4. Log out
                5. Quit system*/

                if (userInput.equals("1")){
                    //System.out.println("(Browse Upcoming Schedule)");
                    ViewSchedule.veiwSchedule(sc);
                } else if (userInput.equals("2")){
                    //System.out.println("(View pass reservation)");
                    ViewReservations.displayReservations(sc);
                } else if (userInput.equals("3")) {
                    ViewReservations.displayTickets(sc);
                } else if (userInput.equals("4")){  //log out user and back to first page.
                    Main.loggedInUser = null;
                    UserMenu.firstPage = true;
                } else if (userInput.equals("5")){  //log out user and stop the program.
                    Main.loggedInUser = null;
                    Main.isRunning = false;
                } else {
                    System.out.println("Please choose between 1-4 only.");
                }

            } else {
                //This is an Admin
                /*Options for reference:
                1. Add event
                2. Manage event
                3. Upadate event
                4. Log out
                5. Quit system */

                if (userInput.equals("1")){
                    //Add event
                    EventManager.addEvent(sc);
                } else if (userInput.equals("2")){
                    //Update event details
                    EventManager.updateEvent(sc);
                } else if (userInput.equals("3")){  //log out user and back to first page.
                    Main.loggedInUser = null;
                    UserMenu.firstPage = true;
                } else if (userInput.equals("4")){  //log out user and stop the program.
                    Main.loggedInUser = null;
                    Main.isRunning = false;
                } else {
                    System.out.println("Please choose between 1-4 only.");
                }
            }
        }
    }
}
