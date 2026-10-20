package menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import main.Main;
import user.Attendee;
import event.ConcertEvent;
import event.ConferenceEvent;

public class userDashboard {

    public static boolean checkDate(String dateInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate.parse(dateInput, formatter);
            return true;
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
                    System.out.println("(View Upcoming Schedule)");
                } else if (userInput.equals("2")){
                    System.out.println("(Make Reservation)");
                } else if (userInput.equals("3")){
                    System.out.println("(Review pass booking)");
                } else if (userInput.equals("4")){  //log out user and back to first page.
                    Main.loggedInUser = null;
                    Main.firstPage = true;
                } else if (userInput.equals("5")){  //log out user and stop the program.
                    Main.loggedInUser = null;
                    Main.isRunning = false;
                } else {
                    System.out.println("Please choose between 1-5 only.");
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
                    //System.out.println("(Add event)");
                    addEvent(sc);
                } else if (userInput.equals("2")){
                    System.out.println("(Manage event)");
                    manageEvent(sc);
                } else if (userInput.equals("3")){
                    System.out.println("(Update event)");
                } else if (userInput.equals("4")){  //log out user and back to first page.
                    Main.loggedInUser = null;
                    Main.firstPage = true;
                } else if (userInput.equals("5")){  //log out user and stop the program.
                    Main.loggedInUser = null;
                    Main.isRunning = false;
                } else {
                    System.out.println("Please choose between 1-5 only.");
                }
            }
        }
    }

    public static void addEvent(Scanner sc){
        String eventType = "empty";
        String eventName = null;
        String eventDate = null;
        String venue = null;
        double baseTicketPrice = 0;
        int maxCapacity = 0;
        String speakerName = null;
        String artistName = null;
        int percentageChange = 0;
        double vipSeatPercentage = 0;
        double vipPriceMultiplier = 0;
        boolean validInput = false;
    
        while (!validInput){
            System.out.println("Please choose a type of event: 'Concert' or 'Conference'");
            eventType = sc.nextLine();
            if ((eventType.equalsIgnoreCase("Concert")) || (eventType.equalsIgnoreCase("Conference"))){
                System.out.println("Event type chosen: " + eventType);
                validInput = true;
                break;
            } else {
                System.out.println("Please type 'Concert' or 'Conference' only.");
            }
        }
        validInput = false;

        System.out.println("Please enter an event name:");
        eventName = sc.nextLine();

        while (!validInput) {
            System.out.println("Please enter an event date (yyyy-mm-dd):");
            eventDate = sc.nextLine();

            if (checkDate(eventDate)) {
                validInput = true;
                break;
            } else {
                System.out.println("Invalid date. Pleaser enter a valid date (yyyy-mm-dd)");
            }
        }
        validInput = false;

        System.out.println("Please enter a venue where the event will be hold:");
        venue = sc.nextLine();

        while (!validInput){
            System.out.println("Please enter a base ticket price");

            if (sc.hasNextDouble()){
                baseTicketPrice = sc.nextDouble();
                if (baseTicketPrice >= 0) {
                    System.out.println("Base ticket price = RM" + baseTicketPrice);
                    validInput = true;
                } else {
                    System.out.println("Please enter a positive amount.");
                }
            } else {
                System.out.println("Please enter a valid price.");

                sc.next();
            }
            sc.nextLine();
        }
        validInput = false;

        while (!validInput){
            System.out.println("Please enter the max capacity (seats)");

            if (sc.hasNextInt()){
                maxCapacity = sc.nextInt();
                if (maxCapacity > 10){
                    System.out.println("Max Capacity = " + maxCapacity);
                    validInput = true;
                } else {
                    System.out.println("Please enter at least 10 seats.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                sc.next();
            }
            sc.nextLine();
        }
        validInput = false;

        if (eventType.equalsIgnoreCase("Concert")){
            //if Concert was chosen as the event
            System.out.println("Event type chosen: " + eventType);
            System.out.println("Please enter an Artist name:");
            artistName = sc.nextLine();

            while (!validInput){
                System.out.println("Please enter the percentage of VIP seats:");

                if (sc.hasNextInt()){
                    percentageChange = sc.nextInt();
                    System.out.println(percentageChange);
                    vipSeatPercentage = percentageChange/100.0;
                    System.out.println(vipSeatPercentage);
                    if ((vipSeatPercentage > 0) && (vipSeatPercentage < 100)){
                        
                        System.out.println("VIP seats = " + (maxCapacity*vipSeatPercentage));
                        System.out.println("Normal seats = " + (maxCapacity*(1-vipSeatPercentage)));
                        validInput = true;
                    } else {
                        System.out.println("Please enter a number between 1-99 only.");
                    }
                } else {
                    System.out.println("Please enter a number between 1-99 only.");
                    sc.next();
                }
                sc.nextLine();
            }  
            validInput = false;

            while (!validInput){
                System.out.println("Please enter the VIP ticket price multiplier:");

                if (sc.hasNextInt()){
                    percentageChange = sc.nextInt();
                    vipPriceMultiplier = percentageChange/100.0;
                    System.out.println(vipPriceMultiplier);
                    if (vipPriceMultiplier >= 1) {
                        System.out.println("VIP ticket price = RM" + (baseTicketPrice*vipPriceMultiplier));
                        validInput = true;
                    } else {
                        System.out.println("Please enter a valid number higher than 100 only.");
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    sc.next();
                }
                sc.nextLine();
            }
            validInput = false;

            int nextId = Main.eventList.size() + 1;
            ConcertEvent newEvent = new ConcertEvent(nextId, eventName, eventDate, venue, baseTicketPrice, maxCapacity, artistName, vipSeatPercentage, vipPriceMultiplier);
            Main.eventList.add(newEvent);
            newEvent.displayEventDetails();

        } else {
            //if Conference was chosen as the event
            System.out.println("Please enter a Speaker name:");
            speakerName = sc.nextLine();

            int nextId = Main.eventList.size() + 1;
            ConferenceEvent newEvent = new ConferenceEvent(nextId, eventName, eventDate, venue, baseTicketPrice, maxCapacity, speakerName);
            Main.eventList.add(newEvent);
            newEvent.displayEventDetails();
        }  

        while (!validInput){
            System.out.println("===============================================================");
            System.out.println("Please 'Confirm' or 'Discard' the creation of the new event.");
            String eventCreate = sc.nextLine();

            if (eventCreate.equalsIgnoreCase("confirm")){
                System.out.println("Event " + eventName + " has successfully created!");
                validInput = true;
                break;
            } else if (eventCreate.equalsIgnoreCase("discard")){
                int listSize = Main.eventList.size() - 1;
                Main.eventList.remove(listSize);
                System.out.println("Event " + eventName + " is cancelled");
                validInput = true;
                break;
            } else {
                System.out.println("Please choose 'Confirm' or 'Discard' only." );
            }
        }
        validInput = false;
    }

    public static void manageEvent(Scanner sc) {
        boolean validInput = false;

        
    }
}
