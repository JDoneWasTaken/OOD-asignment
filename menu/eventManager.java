package menu;

import java.util.Scanner;

import event.Event;
import event.ConcertEvent;
import event.ConferenceEvent;
import main.Main;

public class eventManager {
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

            if (userDashboard.checkDate(eventDate)) {
                validInput = true;
                break;
            } else {
                System.out.println("Invalid date. Pleaser enter a valid date after today (yyyy-mm-dd).");
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

    public static void manageEvent(Scanner sc) {
        boolean validInput = false;
        String userInput = null;
        Event eventSelected = null;
        System.out.println("========================================================");
        System.out.println("                  Upcoming Events                       ");
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
        System.out.println("========================================================");

        System.out.println("Please enter the title of the event you want to edit");
        userInput = sc.nextLine();
        for (Event e: Main.eventList){
            if (e.getEventName().equalsIgnoreCase(userInput)){
                eventSelected = e;
                while (!validInput) {
                    System.out.println("Please choose what you want to edit from the following:");
                    System.out.println("1. Event name " + e.getEventName());
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
                        System.out.println("Enter a new event name:");
                        userInput = sc.nextLine();
                        e.setEventName(userInput);
                        e.displayEventDetails();
                        System.out.println("Event name successfully changed!");
                        System.out.println("===========================================");
                        userDashboard.pressEnterToContinue(sc);
                    } else if (userInput.equalsIgnoreCase("2")) {
                        if (e instanceof ConcertEvent) {
                            ConcertEvent concert = (ConcertEvent) e;
                            System.out.println("Enter a new Artist name:");
                            userInput = sc.nextLine();
                            concert.setArtistName(userInput);
                            e.displayEventDetails();
                            System.out.println("Artist name successfully changed!");
                            System.out.println("========================================");
                            userDashboard.pressEnterToContinue(sc);
                        } else {
                            ConferenceEvent conference = (ConferenceEvent) e;
                            System.out.println("Enter a new Speaker name:");
                            userInput = sc.nextLine();
                            conference.setSpeakerName(userInput);
                            e.displayEventDetails();
                            System.out.println("Speaker name successfully changed!");
                            System.out.println("========================================");
                            userDashboard.pressEnterToContinue(sc);
                        }
                    } else if (userInput.equalsIgnoreCase("3")) {
                        System.out.println("Enter a new venue:");
                        userInput = sc.nextLine();
                        e.setVenue(userInput);
                        e.displayEventDetails();
                        System.out.println("Venue successfully changed!");
                        System.out.println("========================================");
                        userDashboard.pressEnterToContinue(sc);
                    } else if (userInput.equalsIgnoreCase("4")) {
                        validInput = true;
                    } else {
                        System.out.println("Please enter a valid choice (1/2/3/4)");
                        userDashboard.pressEnterToContinue(sc);
                    }
                }
                validInput = false;
            }
        }
        if (eventSelected == null) {
            System.out.println("Please enter a valid event name.");
            userDashboard.pressEnterToContinue(sc);
        }
    }

}
