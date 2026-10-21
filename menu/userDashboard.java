package menu;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import main.Main;
import reservation.ticketReservation;
import user.Attendee;
import event.Event;
import event.ConcertEvent;
import event.ConferenceEvent;

public class userDashboard {
    public static void pressEnterToContinue(Scanner sc) {
        System.out.println("\nPress enter to continue...");
        sc.nextLine();
    }

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
                    //System.out.println("(Browse Upcoming Schedule)");
                    veiwSchedule(sc);
                } else if (userInput.equals("2")){
                    //System.out.println("(View pass reservation)");
                    viewReservations(sc);
                } else if (userInput.equals("3")){  //log out user and back to first page.
                    Main.loggedInUser = null;
                    Main.firstPage = true;
                } else if (userInput.equals("4")){  //log out user and stop the program.
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
        int vipSeats = 0;
        double vipPrice = 0;
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
            System.out.println("Please enter the amount of Normal Seats:");

            if (sc.hasNextInt()){
                maxCapacity = sc.nextInt();
                if (maxCapacity > 10){
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
                System.out.println("Please enter the amount of VIP seats:");

                if (sc.hasNextInt()){
                    vipSeats = sc.nextInt();
                    System.out.println(vipSeats);
                    if ((vipSeats > 0) && (vipSeats < maxCapacity)){
                        
                        System.out.println("VIP seats = " + (vipSeats));
                        System.out.println("Normal seats = " + (maxCapacity));
                        validInput = true;
                    } else {
                        System.out.println("VIP seats cannot be 0 or more than normal seats.");
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    sc.next();
                }
                sc.nextLine();
            }  
            validInput = false;

            while (!validInput){
                System.out.println("Please enter the VIP ticket price:");

                if (sc.hasNextDouble()){
                    vipPrice = sc.nextDouble();
                    if (vipPrice > baseTicketPrice) {
                        System.out.println("VIP ticket price = RM" + (vipPrice));
                        validInput = true;
                    } else {
                        System.out.println("VIP ticket price must be higher than base ticket price");
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    sc.next();
                }
                sc.nextLine();
            }
            validInput = false;

            int nextId = Main.eventList.size() + 1;
            ConcertEvent newEvent = new ConcertEvent(nextId, eventName, eventDate, venue, baseTicketPrice, maxCapacity, 0, artistName, vipSeats, 0,  vipPrice);
            Main.eventList.add(newEvent);
            newEvent.displayEventDetails();

        } else {
            //if Conference was chosen as the event
            System.out.println("Please enter a Speaker name:");
            speakerName = sc.nextLine();

            int nextId = Main.eventList.size() + 1;
            ConferenceEvent newEvent = new ConferenceEvent(nextId, eventName, eventDate, venue, baseTicketPrice, maxCapacity, 0,  speakerName);
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

    public static void veiwSchedule(Scanner sc) {
        boolean viewingSchedule = true;
        String userInput = "empty";
        boolean validInput = false;
        Event eventSelected = null;

        while (viewingSchedule) {
            System.out.println("========================================================");
            System.out.println("                  Upcoming Events");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            int numOfEvent = 1;
            for (Event e: Main.eventList) {
                if (e instanceof ConcertEvent) {
                    ConcertEvent concert = (ConcertEvent) e;
                    System.out.println(numOfEvent + ") (Concert) Title: " + concert.getEventName() + " | Artist: " + concert.getArtistName() + " | Venue: " + concert.getVenue() + " | Seats left: Normal (" + (concert.getMaxCapacity()-concert.getReservedSeats()) + ") VIP (" + (concert.getVipSeats()-concert.getReservedVipSeats())   + ") | Price: Normal Ticket ~ " + concert.getBaseTicketPrice() + " VIP ticket ~ " + concert.getVipPrice());
                } else {
                    ConferenceEvent conference = (ConferenceEvent) e ;
                    System.out.println(numOfEvent + ") (Conference) Title: " + conference.getEventName() + " | Speaker: " + conference.getSpeakerName() + " | Venue: " + conference.getVenue() + " | Seats left: " + (conference.getMaxCapacity()-conference.getReservedSeats()) + " | Ticket Price: " + conference.getBaseTicketPrice() );
                }
                numOfEvent += 1;
            }
        

            while (!validInput) {
                System.out.println("========================================================");
                System.out.println("Choose an option (1/2/3)");
                System.out.println("1. View an event details");
                System.out.println("2. Reserve an event");
                System.out.println("3. Back to menu");
                userInput = sc.nextLine();
                if (userInput.equalsIgnoreCase("1")) {
                    System.out.println("Please enter the title of the event you want to view:");
                    userInput = sc.nextLine();
                    for (Event e: Main.eventList){
                        if (e.getEventName().equalsIgnoreCase(userInput)){
                            eventSelected = e;
                            e.displayEventDetails();
                            pressEnterToContinue(sc);
                            validInput = true;
                            break;
                        }
                    }
                    if (eventSelected == null) {
                        System.out.println("Please enter a valid event name.");
                        }   

                } else if (userInput.equalsIgnoreCase("2")) {
                    userInput = "empty";
                    eventSelected = null;
                    System.out.println("Please enter the title of the event you want to reserve:");
                    userInput = sc.nextLine();
                    for (Event e: Main.eventList){
                        if (e.getEventName().equalsIgnoreCase(userInput)){
                            eventSelected = e;
                            e.displayEventDetails();
                            makeReservation(sc, eventSelected);
                            validInput = true;
                            break;
                        }
                    }
                    if (eventSelected == null) {
                        System.out.println("Please enter a valid event name.");
                        }
                } else if (userInput.equalsIgnoreCase("3")) {
                    validInput = true;
                    viewingSchedule = false;
                } else {
                    System.out.println("Please enter a valid option.");
                }
            }

            validInput = false;
        }
    }

    public static void makeReservation(Scanner sc, Event e) {
        String userInput = null;
        boolean validInput = false;
        String seatsReserved = null;
        String bookingTime = null;
        int ticketQuantity = 0;
        double finalPrice = 0.00;
        int normalSeats = 0;
        int vipSeats = 0;
        String normalSeatArrange = "";
        String vipSeatArrange = "";
        String finalSeatArrange = null;

        if (e instanceof ConcertEvent) {
            ConcertEvent concert = (ConcertEvent) e;
            System.out.println("Please enter the amount of Normal (" + e.getMaxCapacity() + " left) and VIP (" + concert.getVipSeats() + " left) seats you want to reserve." ); 
            while (!validInput){
                System.out.println("Normal: ");

                if (sc.hasNextInt()){
                    normalSeats = sc.nextInt();
                    if (normalSeats > e.getMaxCapacity()){
                        System.out.println("Only " + e.getMaxCapacity() + " Normal seats left, please enter again.");
                    } else if (normalSeats < 0){
                        System.out.println("Please enter a positive number");
                    } else {
                        validInput = true;
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    sc.next();
                }
                sc.nextLine();
            }  
            validInput = false;

            while (!validInput){
                System.out.println("VIP: ");

                if (sc.hasNextInt()){
                    vipSeats = sc.nextInt();
                    if (vipSeats > concert.getVipSeats()){
                        System.out.println("Only " + concert.getVipSeats() + " VIP seats left, please enter again.");
                    } else if (vipSeats < 0){
                        System.out.println("Please enter a positive number");
                    } else {
                        validInput = true;
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    sc.next();
                }
                sc.nextLine();
            }  
            validInput = false;

            if (normalSeats != 0){
                for (int t = e.getReservedSeats(); t < (concert.getReservedSeats()+normalSeats); t++ ){
                    normalSeatArrange = normalSeatArrange + "ST-" + (t + 1) + ", ";
                }
            } else {
                normalSeatArrange = "N/A";
            }

            if (vipSeats != 0){
                for (int t = concert.getReservedVipSeats(); t < (concert.getReservedVipSeats()+vipSeats); t++ ){
                    vipSeatArrange = vipSeatArrange + "VipST-" + (t + 1) + ", ";
                }
            } else {
                vipSeatArrange = "N/A";
            }

            finalSeatArrange = "Normal = " + normalSeatArrange + " | VIP = " + vipSeatArrange;
            ticketQuantity = normalSeats + vipSeats;
            finalPrice = (concert.getBaseTicketPrice() * normalSeats) + (concert.getVipPrice() * vipSeats);

            System.out.println("=============================================");
            System.out.println("             Reservation Summary             ");
            System.out.println("---------------------------------------------");
            System.out.println("Event name: " + e.getEventName());
            System.out.println("Reserved by: " + Main.loggedInUser.getUsername());
            System.out.println("Reserved Seats: " + finalSeatArrange);
            System.out.println("Ticket quantity: " + ticketQuantity);
            System.out.println("---------------------------------------------");
            System.out.println("Final Price: RM" + finalPrice);
            System.out.println("=============================================");

            while (!validInput) {
                System.out.print("Confirm reservation? (y/n)");
                userInput = sc.nextLine();

                if (userInput.equalsIgnoreCase("y")) {
                    while (!validInput) {
                        System.out.println("Please choose a payment method (1/2/3/4):");
                        System.out.println("1. Credit/Debit card");
                        System.out.println("2. E-wallet (TNG,APPLEPAY,..and more");
                        System.out.println("3. Online Banking");
                        System.out.println("4. Cancel (Discard the reservation)");
                        userInput = sc.nextLine();
                        if (userInput.equals("1") || userInput.equals("2") || userInput.equals("3")) {
                            LocalDateTime now = LocalDateTime.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            bookingTime = now.format(formatter);

                            concert.setReservedSeats(concert.getReservedSeats()+normalSeats);
                            concert.setReservedVipSeats(concert.getReservedVipSeats()+vipSeats);

                            int nextId = Main.reservationList.size() + 1;
                            ticketReservation newReservation = new ticketReservation(nextId, Main.loggedInUser, e, finalSeatArrange, ticketQuantity, bookingTime, finalPrice);

                            Main.reservationList.add(newReservation);
                            System.out.println("Thank you for the payment!");
                            System.out.println("Reservation created successfully.");
                            pressEnterToContinue(sc);
                            validInput = true;
                        } else if (userInput.equals("4")) {
                            System.out.println("Reservation cancelled.");
                            pressEnterToContinue(sc);
                            validInput = true;
                        } else {
                            System.out.println("Please choose between 1-4 only.");
                            pressEnterToContinue(sc);
                            System.out.println("=======================================");
                        }
                    }

                } else if (userInput.equalsIgnoreCase("n")) {
                    System.out.println("Reservation cancelled.");
                    pressEnterToContinue(sc);
                    validInput = true;
                } else {
                    System.out.println("Please enter Y or N only.");
                }
            }

        } else {
            ConferenceEvent conference = (ConferenceEvent) e;
            System.out.println("Please enter the amount of Normal (" + e.getMaxCapacity() + " left) seats you want to reserve." ); 
            while (!validInput){
                System.out.println("Normal: ");

                if (sc.hasNextInt()){
                    normalSeats = sc.nextInt();
                    if (normalSeats > e.getMaxCapacity()){
                        System.out.println("Only " + e.getMaxCapacity() + " Normal seats left, please enter again.");
                    } else if (normalSeats < 0){
                        System.out.println("Please enter a positive number");
                    } else {
                        validInput = true;
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    sc.next();
                }
                sc.nextLine();
            }  
            validInput = false;

            if (normalSeats != 0){
                for (int t = e.getReservedSeats(); t < (conference.getReservedSeats()+normalSeats); t++ ){
                    normalSeatArrange = normalSeatArrange + "ST-" + (t + 1) + ", ";
                }
            } else {
                normalSeatArrange = "N/A";
            }

            finalSeatArrange = "Normal = " + normalSeatArrange;
            ticketQuantity = normalSeats;
            finalPrice = conference.getBaseTicketPrice() * normalSeats;

            System.out.println("=============================================");
            System.out.println("             Reservation Summary             ");
            System.out.println("---------------------------------------------");
            System.out.println("Event name: " + e.getEventName());
            System.out.println("Reserved by: " + Main.loggedInUser.getUsername());
            System.out.println("Reserved Seats: " + finalSeatArrange);
            System.out.println("Ticket quantity: " + ticketQuantity);
            System.out.println("---------------------------------------------");
            System.out.println("Final Price: RM" + finalPrice);
            System.out.println("=============================================");

            while (!validInput) {
                System.out.print("Confirm reservation? (y/n)");
                userInput = sc.nextLine();

                if (userInput.equalsIgnoreCase("y")) {
                    while (!validInput) {
                        System.out.println("Please choose a payment method (1/2/3/4):");
                        System.out.println("1. Credit/Debit card");
                        System.out.println("2. E-wallet (TNG,APPLEPAY,..and more");
                        System.out.println("3. Online Banking");
                        System.out.println("4. Cancel (Discard the reservation)");
                        userInput = sc.nextLine();
                        if (userInput.equals("1") || userInput.equals("2") || userInput.equals("3")) {

                            LocalDateTime now = LocalDateTime.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            bookingTime = now.format(formatter);

                            conference.setReservedSeats(conference.getReservedSeats()+normalSeats);

                            int nextId = Main.reservationList.size() + 1;
                            ticketReservation newReservation = new ticketReservation(nextId, Main.loggedInUser, e, finalSeatArrange, ticketQuantity, bookingTime, finalPrice);

                            Main.reservationList.add(newReservation);
                            System.out.println("Thank you for the payment!");
                            System.out.println("Reservation created successfully.");
                            pressEnterToContinue(sc);
                            validInput = true;
                        } else if (userInput.equals("4")) {
                            System.out.println("Reservation cancelled.");
                            pressEnterToContinue(sc);
                            validInput = true;
                        } else {
                            System.out.println("Please choose between 1-4 only.");
                            pressEnterToContinue(sc);
                            System.out.println("=======================================");
                        }
                    }

                } else if (userInput.equalsIgnoreCase("n")) {
                    System.out.println("Reservation cancelled.");
                    pressEnterToContinue(sc);
                    validInput = true;
                } else {
                    System.out.println("Please enter Y or N only.");
                }
            }

        }  
    }   

    public static void viewReservations(Scanner sc) {
        boolean hasReservation = false;
        int index = 1;
        System.out.println("===========================================");
        System.out.println("            EVENT RESERVATIONS             ");
        System.out.println("===========================================");
        ArrayList<ticketReservation> myReservations = new ArrayList<>();

        for (ticketReservation r: Main.reservationList) {
            if (r.getAttendee().equals(Main.loggedInUser)) {
                myReservations.add(r);
                System.out.println("              ~ Event " + index + " ~           ");
                r.displayReservation();
                hasReservation = true;
                index ++;
            }
        }


        if (!hasReservation) {
            System.out.println("You haven't make any reservation.");
            pressEnterToContinue(sc);
        } else {
            pressEnterToContinue(sc);
        }
    }
}
