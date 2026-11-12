package menu;

import java.util.Scanner;

import event.Event;
import event.ConcertEvent;
import event.ConferenceEvent;
import main.Main;

public class EventManager {
    /**
     * Let any admin add new events 
     * @param sc
     */
    public static void addEvent(Scanner sc){
        String eventType = "empty";
        String eventName = "";
        String eventDate = "";
        String venue = "";
        double baseTicketPrice = 0;
        int maxCapacity = 0;
        String speakerName = "";
        String artistName = "";
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

        while (!validInput) {
            validInput = true;
            System.out.println("Please enter an event name:");
            eventName = sc.nextLine();
            if (eventName.equals("")){
                System.out.println("Please enter a non-empty event name.");
                validInput = false;
            }
        }
        validInput = false;

        while (!validInput) {
            System.out.println("Please enter an event date (yyyy-mm-dd):");
            eventDate = sc.nextLine();

            if (UserDashboard.checkDate(eventDate)) {
                validInput = true;
                break;
            } else {
                System.out.println("Invalid date. Pleaser enter a valid date after today (yyyy-mm-dd).");
            }
        }
        validInput = false;

        while (!validInput) {
            validInput = true;
            System.out.println("Please enter a venue where the event will be hold:");
            venue = sc.nextLine();
            if (venue.equals("")){
                System.out.println("Please enter a non-empty venue.");
                validInput = false;
            }
        }
        validInput = false;

        while (!validInput){
            System.out.println("Please enter a base ticket price");

            if (sc.hasNextDouble()){
                baseTicketPrice = sc.nextDouble();
                if (baseTicketPrice >= 0) {
                    System.out.println("Base ticket price = RM" + String.format("%,.2f", baseTicketPrice));
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
                if (maxCapacity >= 10){
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
        System.out.println("Event type chosen: " + eventType);

        if (eventType.equalsIgnoreCase("Concert")){
            //if Concert was chosen as the event

            while (!validInput) {
                validInput = true;
                System.out.println("Please enter an Artist name:");
                artistName = sc.nextLine();
                if (artistName.equals("")) {
                    System.out.println("Please enter a non-empty artist name.");
                    validInput = false;
                }
            }
            validInput = false;

            while (!validInput){
                System.out.println("Please enter the amount of VIP seats:");

                if (sc.hasNextInt()){
                    vipSeats = sc.nextInt();
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
                        System.out.println("VIP ticket price = RM" + String.format("%,.2f", vipPrice));
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
            //if Conference was chosen as the eventw

            while (!validInput){
                validInput = true;
                System.out.println("Please enter a Speaker name:");
                speakerName = sc.nextLine();
                if (speakerName.equals("")) {
                    System.out.println("Please enter a non-empty speaker name.");
                    validInput = false;
                }
            }
            validInput = false;

            int nextId = Main.eventList.size() + 1;
            ConferenceEvent newEvent = new ConferenceEvent(nextId, eventName, eventDate, venue, baseTicketPrice, maxCapacity, 0,  speakerName);
            Main.eventList.add(newEvent);
            newEvent.displayEventDetails();
        }  

        while (!validInput){
            System.out.println("===============================================================");
            System.out.println("Please choose 'Confirm' or 'Discard' the creation of the new event.");
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

    /**
     * Let any admin update some event details like event name, artist/speaker name and venue.
     * @param sc
     */
    public static void updateEvent(Scanner sc) {
        boolean validInput = false;
        String userInput = null;
        Event eventSelected = null;
        System.out.println("========================================================");
        System.out.println("                  Upcoming Events                       ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int numOfEvent = 1;
        for (Event e: Main.eventList) {
            e.listingEventDetails(numOfEvent);
            numOfEvent += 1;
        }
        System.out.println("========================================================");

        System.out.println("Please enter the title of the event you want to update");
        userInput = sc.nextLine();
        for (Event e: Main.eventList){
            if (e.getEventName().equalsIgnoreCase(userInput)){
                eventSelected = e;
                while (!validInput) {
                    System.out.println("Please choose what you want to update from the following (1/2/3/4):");
                    System.out.println("1. Event name: " + e.getEventName());
                    if (e instanceof ConcertEvent){
                        ConcertEvent concert = (ConcertEvent) e;
                        System.out.println("2. Artist name: " + concert.getArtistName());
                    } else {
                        ConferenceEvent conference = (ConferenceEvent) e;
                        System.out.println("2. Speaker name: " + conference.getSpeakerName());
                    }
                    System.out.println("3. Venue: " + e.getVenue());
                    System.out.println("4. Cancel");
                    userInput = sc.nextLine();
                    if (userInput.equalsIgnoreCase("1")) {
                        while (!validInput) {
                            System.out.println("Enter a new event name:");
                            userInput = sc.nextLine();
                            if (userInput.equals("")) {
                                System.out.println("Please enter a non-empty event name.");
                            } else {
                                e.setEventName(userInput);
                                validInput = true;
                            }
                        }
                        validInput = false;
                        e.displayEventDetails();
                        System.out.println("Event name successfully changed!");
                        System.out.println("===========================================");
                        UserDashboard.pressEnterToContinue(sc);
                    } else if (userInput.equalsIgnoreCase("2")) {
                        if (e instanceof ConcertEvent) {
                            ConcertEvent concert = (ConcertEvent) e;
                            while (!validInput) {
                                System.out.println("Enter a new Artist name:");
                            userInput = sc.nextLine();
                                if (userInput.equals("")) {
                                    System.out.println("Please enter a non-empty artsit name.");
                                } else {
                                    concert.setArtistName(userInput);
                                    validInput = true;
                                }
                            }   
                            validInput = false;
                            e.displayEventDetails();
                            System.out.println("Artist name successfully changed!");
                            System.out.println("========================================");
                            UserDashboard.pressEnterToContinue(sc);
                        } else {
                            ConferenceEvent conference = (ConferenceEvent) e;
                            while (!validInput) {
                                System.out.println("Enter a new Speaker name:");
                            userInput = sc.nextLine();
                                if (userInput.equals("")) {
                                    System.out.println("Please enter a non-empty speaker name.");
                                } else {
                                    conference.setSpeakerName(userInput);
                                    validInput = true;
                                }
                            }
                            validInput = false;
                            e.displayEventDetails();
                            System.out.println("Speaker name successfully changed!");
                            System.out.println("========================================");
                            UserDashboard.pressEnterToContinue(sc);
                        }
                    } else if (userInput.equalsIgnoreCase("3")) {
                        while (!validInput) {
                            System.out.println("Enter a new venue:");
                            userInput = sc.nextLine();
                            if (userInput.equals("")) {
                                System.out.println("Please enter a non-empty venue.");
                            } else {
                                e.setVenue(userInput);
                                validInput = true;
                            }
                        }
                        validInput = false;
                        e.displayEventDetails();
                        System.out.println("Venue successfully changed!");
                        System.out.println("========================================");
                        UserDashboard.pressEnterToContinue(sc);
                    } else if (userInput.equalsIgnoreCase("4")) {
                        validInput = true;
                    } else {
                        System.out.println("Please enter a valid choice (1/2/3/4)");
                        UserDashboard.pressEnterToContinue(sc);
                    }
                }
                validInput = false;
                break;
            }
        }
        if (eventSelected == null) {
            System.out.println("Please enter a valid event name.");
            UserDashboard.pressEnterToContinue(sc);
        }
    }

}
