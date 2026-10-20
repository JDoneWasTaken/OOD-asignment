package menu;

import java.util.Scanner;

import main.Main;
import user.Attendee;
import event.ConcertEvent;
import event.ConferenceEvent;

public class userDashboard {
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
                    System.out.println("(Add event)");
                    addEvent(sc);
                } else if (userInput.equals("2")){
                    System.out.println("(Manage event)");
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
        String venue = null;
        double baseTicketPrice = 0;
        int maxCapacity = 0;
        String speakerName = null;
        String artistName = null;
        double vipSeatPercentage = 0;
        double vipPriceMultiplier = 0;
        boolean validInput1 = false;
    
        while (!validInput1){
            System.out.println("Please choose a type of event: 'Concert' or 'Conference'");
            eventType = sc.nextLine();
            if ((eventType.equalsIgnoreCase("Concert")) || (eventType.equalsIgnoreCase("Conference"))){
                System.out.println("Event type chosen: " + eventType);
                validInput1 = true;
                break;
            } else {
                System.out.println("Please type 'Concert' or 'Conference' only.");
            }
        }
        validInput1 = false;

        System.out.println("Please enter an event name:");
        eventName = sc.nextLine();

        System.out.println("Please enter a venue where the event will be hold:");
        venue = sc.nextLine();

        while (!validInput1){
            System.out.println("Please enter a base ticket price");

            if (sc.hasNextDouble()){
                baseTicketPrice = sc.nextDouble();
                System.out.println("Base ticket price = RM" + baseTicketPrice);
                validInput1 = true;
            } else {
                System.out.println("Please enter a valid price.");

                sc.next();
            }
            sc.nextLine();
        }
        validInput1 = false;

        while (!validInput1){
            System.out.println("Please enter the max capacity (seats)");

            if (sc.hasNextInt()){
                maxCapacity = sc.nextInt();
                System.out.println("Max Capacity = " + maxCapacity);
                validInput1 = true;
            } else {
                System.out.println("Please enter a valid number.");

                sc.next();
            }
            sc.nextLine();
        }
        validInput1 = false;

        if (eventType.equalsIgnoreCase("Concert")){
            //if Concert was chosen as the event
            System.out.println("Event type chosen: " + eventType);
            System.out.println("Please enter an Artist name:");
            artistName = sc.nextLine();

            while (!validInput1){
                System.out.println("Please enter the percentage of VIP seats (in decimals):");

                if (sc.hasNextDouble()){
                    vipSeatPercentage = sc.nextDouble();
                    System.out.println("VIP seats = " + (maxCapacity*vipSeatPercentage));
                    System.out.println("Normal seats = " + (maxCapacity*(1-vipSeatPercentage)));
                    validInput1 = true;
                } else {
                    System.out.println("Please enter a percentage (in decimals).");

                    sc.next();
                }
                sc.nextLine();
            }  
            validInput1 = false;

            while (!validInput1){
                System.out.println("Please enter the VIP ticket price multiplier (in decimals)");

                if (sc.hasNextDouble()){
                    vipPriceMultiplier = sc.nextDouble();
                    System.out.println("VIP ticket price = RM" + (baseTicketPrice*vipPriceMultiplier));
                    validInput1 = true;
                } else {
                    System.out.println("Please enter a valid number (in decimals)");

                    sc.next();
                }
                sc.nextLine();
            }
            validInput1 = false;

            int nextId = Main.eventList.size() + 1;
            ConcertEvent newEvent = new ConcertEvent(nextId, eventName, venue, baseTicketPrice, maxCapacity, artistName, vipSeatPercentage, vipPriceMultiplier);
            Main.eventList.add(newEvent);

        } else {
            //if Conference was chosen as the event
            System.out.println("Please enter a Speaker name:");
            speakerName = sc.nextLine();

            int nextId = Main.eventList.size() + 1;
            ConferenceEvent newEvent = new ConferenceEvent(nextId, eventName, venue, baseTicketPrice, maxCapacity, speakerName);
            Main.eventList.add(newEvent);
        }






    
        
    }
}
