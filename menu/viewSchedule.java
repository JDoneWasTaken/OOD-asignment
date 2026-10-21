package menu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import event.Event;
import event.ConcertEvent;
import event.ConferenceEvent;
import main.Main;
import reservation.digitalTicket;
import reservation.ticketReservation;
import user.Attendee;

public class viewSchedule {
    public static void veiwSchedule(Scanner sc) {
        boolean viewingSchedule = true;
        String userInput = "empty";
        boolean validInput = false;
        Event eventSelected = null;
        Attendee attendee = (Attendee) Main.loggedInUser;

        while (viewingSchedule) {
            System.out.println("========================================================");
            System.out.println("                  Upcoming Events");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            int numOfEvent = 1;
            for (Event e: Main.eventList) {
                if (e instanceof ConcertEvent) {
                    ConcertEvent concert = (ConcertEvent) e;
                    System.out.println(numOfEvent + ") (Concert) Title: " + concert.getEventName() + " | Artist: " + concert.getArtistName() + " | Date: " + concert.getEventDate() + " | Venue: " + concert.getVenue() + " | Seats left: Normal (" + (concert.getMaxCapacity()-concert.getReservedSeats()) + ") VIP (" + (concert.getVipSeats()-concert.getReservedVipSeats())   + ") | Price: Normal Ticket ~ " + concert.getBaseTicketPrice() + " VIP ticket ~ " + concert.getVipPrice());
                } else {
                    ConferenceEvent conference = (ConferenceEvent) e ;
                    System.out.println(numOfEvent + ") (Conference) Title: " + conference.getEventName() + " | Speaker: " + conference.getSpeakerName() + " | Date: " + conference.getEventDate() + " | Venue: " + conference.getVenue() + " | Seats left: " + (conference.getMaxCapacity()-conference.getReservedSeats()) + " | Ticket Price: " + conference.getBaseTicketPrice() );
                }
                numOfEvent += 1;
            }
        

            while (!validInput) {
                System.out.println("========================================================");
                System.out.println("(Early bird (5 days before event) will get a 10% discount.");
                System.out.println("Choose an option (1/2/3)");
                System.out.println("1. View an event details");
                System.out.println("2. Reserve an event (You have " + attendee.getLoyaltyPoint() + " loyalty points left)");
                System.out.println("3. Back to menu");
                userInput = sc.nextLine();
                if (userInput.equalsIgnoreCase("1")) {
                    System.out.println("Please enter the title of the event you want to view:");
                    userInput = sc.nextLine();
                    for (Event e: Main.eventList){
                        if (e.getEventName().equalsIgnoreCase(userInput)){
                            eventSelected = e;
                            e.displayEventDetails();
                            userDashboard.pressEnterToContinue(sc);
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
        double earlyBirdDc = 0.0;
        int loyaltyPoint = 0;
        int loyaltyPointSetter = 0;
        String bookingTime = null;
        int ticketQuantity = 0;
        double finalPrice = 0.00;
        int normalSeats = 0;
        int vipSeats = 0;
        String normalSeatArrange = "";
        String vipSeatArrange = "";
        String finalSeatArrange = null;
        Attendee attendee = (Attendee) Main.loggedInUser;

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

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate eventDate = LocalDate.parse(e.getEventDate(), formatter);
            long daysUntilEvent = ChronoUnit.DAYS.between(today, eventDate);

        finalPrice = ((concert.getBaseTicketPrice() * normalSeats) + (concert.getVipPrice() * vipSeats));

            if (daysUntilEvent >= 5) {
                earlyBirdDc = 0.9;
                finalPrice = (((concert.getBaseTicketPrice() * normalSeats) + (concert.getVipPrice() * vipSeats))*earlyBirdDc);
            }

            if (attendee.getLoyaltyPoint() <= finalPrice) {
                finalPrice = finalPrice-attendee.getLoyaltyPoint();
                loyaltyPointSetter = 0;
            } else {
                loyaltyPointSetter = (int) (attendee.getLoyaltyPoint()-Math.round(finalPrice));
                finalPrice = 0;
            }
            loyaltyPoint = (int) Math.round(finalPrice)/100;

            System.out.println("============================================================");
            System.out.println("                   Reservation Summary                     ");
            System.out.println("------------------------------------------------------------");
            System.out.println("Event name: " + e.getEventName());
            System.out.println("Reserved by: " + Main.loggedInUser.getUsername());
            System.out.println("Reserved Seats: " + finalSeatArrange);
            System.out.println("Ticket quantity: " + ticketQuantity);
            System.out.println("------------------------------------------------------------");
            System.out.println("Final Price: RM" + finalPrice);
            System.out.println("(After deducting loyalty points and early bird discount.)");
            System.out.println("============================================================");

            while (!validInput) {
                System.out.print("Confirm reservation? You will get [" + loyaltyPoint + "] loyalty points after payment. (y/n)");
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
                            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            bookingTime = now.format(formatter1);

                            attendee.setLoyaltyPoint(loyaltyPointSetter+loyaltyPoint);
                            concert.setReservedSeats(concert.getReservedSeats()+normalSeats);
                            concert.setReservedVipSeats(concert.getReservedVipSeats()+vipSeats);

                            int nextId = Main.reservationList.size() + 1;
                            ticketReservation newReservation = new ticketReservation(nextId, Main.loggedInUser, e, finalSeatArrange, ticketQuantity, bookingTime, finalPrice);
                            nextId = Main.digitalTicketList.size() + 1;
                            digitalTicket newTicket = new digitalTicket(nextId, newReservation);

                            Main.reservationList.add(newReservation);
                            Main.digitalTicketList.add(newTicket);
                            System.out.println("Thank you for the payment!");
                            System.out.println("Reservation created successfully.");
                            userDashboard.pressEnterToContinue(sc);
                            validInput = true;
                        } else if (userInput.equals("4")) {
                            System.out.println("Reservation cancelled.");
                            userDashboard.pressEnterToContinue(sc);
                            validInput = true;
                        } else {
                            System.out.println("Please choose between 1-4 only.");
                            userDashboard.pressEnterToContinue(sc);
                            System.out.println("=======================================");
                        }
                    }

                } else if (userInput.equalsIgnoreCase("n")) {
                    System.out.println("Reservation cancelled.");
                    userDashboard.pressEnterToContinue(sc);
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

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate eventDate = LocalDate.parse(e.getEventDate(), formatter);
            long daysUntilEvent = ChronoUnit.DAYS.between(today, eventDate);

            finalPrice = (conference.getBaseTicketPrice() * normalSeats);

            if (daysUntilEvent >= 5) {
                earlyBirdDc = 0.9;
                finalPrice = (conference.getBaseTicketPrice() * normalSeats) * earlyBirdDc;
            }

            if (attendee.getLoyaltyPoint() <= finalPrice) {
                finalPrice = finalPrice-attendee.getLoyaltyPoint();
                loyaltyPointSetter = 0;
            } else {
                loyaltyPointSetter = (int) (attendee.getLoyaltyPoint()-Math.round(finalPrice));
                finalPrice = 0;
            }
            loyaltyPoint = (int) Math.round(finalPrice)/100;

            System.out.println("========================================================");
            System.out.println("                   Reservation Summary                  ");
            System.out.println("--------------------------------------------------------");
            System.out.println("Event name: " + e.getEventName());
            System.out.println("Reserved by: " + Main.loggedInUser.getUsername());
            System.out.println("Reserved Seats: " + finalSeatArrange);
            System.out.println("Ticket quantity: " + ticketQuantity);
            System.out.println("--------------------------------------------------------");
            System.out.println("Final Price: RM" + finalPrice);
            System.out.println("(After deducting loyalty points and early bird discount.)");
            System.out.println("========================================================");

            while (!validInput) {
                System.out.print("Confirm reservation? You will get [" + loyaltyPoint + "] loyalty points after payment. (y/n)");
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
                            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            bookingTime = now.format(formatter1);

                            attendee.setLoyaltyPoint(loyaltyPointSetter+loyaltyPoint);
                            conference.setReservedSeats(conference.getReservedSeats()+normalSeats);

                            int nextId = Main.reservationList.size() + 1;
                            ticketReservation newReservation = new ticketReservation(nextId, Main.loggedInUser, e, finalSeatArrange, ticketQuantity, bookingTime, finalPrice);
                            nextId = Main.digitalTicketList.size() + 1;
                            digitalTicket newTicket = new digitalTicket(nextId, newReservation);

                            Main.reservationList.add(newReservation);
                            Main.digitalTicketList.add(newTicket);
                            System.out.println("Thank you for the payment!");
                            System.out.println("Reservation created successfully.");
                            userDashboard.pressEnterToContinue(sc);
                            validInput = true;
                        } else if (userInput.equals("4")) {
                            System.out.println("Reservation cancelled.");
                            userDashboard.pressEnterToContinue(sc);
                            validInput = true;
                        } else {
                            System.out.println("Please choose between 1-4 only.");
                            userDashboard.pressEnterToContinue(sc);
                            System.out.println("=======================================");
                        }
                    }

                } else if (userInput.equalsIgnoreCase("n")) {
                    System.out.println("Reservation cancelled.");
                    userDashboard.pressEnterToContinue(sc);
                    validInput = true;
                } else {
                    System.out.println("Please enter Y or N only.");
                }
            }

        }  
    }   

}
