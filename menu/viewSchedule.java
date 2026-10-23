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
import reservation.DigitalTicket;
import reservation.TicketReservation;
import user.Attendee;

public class ViewSchedule {
    /**
     * Calculate how many days until the event from now 
     * @param e Event chosen
     * @return daysUntilEvent
     */
    public static long daysUntilEvent(Event e) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate eventDate = LocalDate.parse(e.getEventDate(), formatter);
        long daysUntilEvent = ChronoUnit.DAYS.between(today, eventDate);
        return daysUntilEvent;
    }
    /**
     * Display all payment methods
     */
    public static void paymentMethods() {
        System.out.println("Please choose a payment method (1/2/3/4):");
        System.out.println("1. Credit/Debit card");
        System.out.println("2. E-wallet (TNG,APPLEPAY,..and more");
        System.out.println("3. Online Banking");
        System.out.println("4. Cancel (Discard the reservation)");
    }

    /**
     * Let attendee view all the upcoming events
     * @param sc
     */
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
                e.listingEventDetails(numOfEvent);
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
                            e.displayEventDetails();  // Option 1: Display the details of the Event chosen
                            UserDashboard.pressEnterToContinue(sc);
                            eventSelected = null;
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
                            if (e.zeroSeatCheck() == true) {
                                System.out.println("Ticket sold out.");
                            } else {
                                e.displayEventDetails();
                                makeReservation(sc, eventSelected); //Option 2: Let user make reservation based on the event chosen
                            }
                            eventSelected = null;
                            validInput = true;
                            UserDashboard.pressEnterToContinue(sc);
                            break;
                        }
                    }
                    if (eventSelected == null) {
                        System.out.println("Please enter a valid event name.");
                        }
                } else if (userInput.equalsIgnoreCase("3")) { //Option 3: Back to Attendee menu
                    validInput = true;
                    viewingSchedule = false;
                } else {
                    System.out.println("Please enter a valid option.");
                }
            }

            validInput = false;
        }
    }

    /**
     * Let attendee reserve an event they selected
     * @param sc
     * @param e Event chosen
     */
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
            System.out.println("Please enter the amount of Normal (" + (e.getMaxCapacity()-e.getReservedSeats()) + " left) and VIP (" + (concert.getVipSeats()-concert.getReservedVipSeats()) + " left) seats you want to reserve." ); 
            while (ticketQuantity == 0){
                System.out.println("Normal: ");

                while (!validInput) {
                    if (sc.hasNextInt()){
                        normalSeats = sc.nextInt();
                        if (normalSeats > (e.getMaxCapacity()-e.getReservedSeats())){
                            System.out.println("Only " + (e.getMaxCapacity()-e.getReservedSeats()) + " Normal seats left, please enter again.");
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
                        if (vipSeats > (concert.getVipSeats()-concert.getReservedVipSeats())){
                            System.out.println("Only " + (concert.getVipSeats()-concert.getReservedVipSeats()) + " VIP seats left, please enter again.");
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

                ticketQuantity = normalSeats + vipSeats;
                if (ticketQuantity == 0) {
                    System.out.println("Please enter at least one ticket.");
                    validInput = false;
                } 
            }
            validInput = false;

            //Create unique seats number for normal seats; return N/A if 0 seats are chosen
            if (normalSeats != 0){
                for (int t = e.getReservedSeats(); t < (concert.getReservedSeats()+normalSeats); t++ ){
                    normalSeatArrange = normalSeatArrange + "ST-" + (t + 1) + ", ";
                }
            } else {
                normalSeatArrange = "N/A";
            }

            //Create unique seats number for vip seats; return N/A if 0 seats are chosen
            if (vipSeats != 0){
                for (int t = concert.getReservedVipSeats(); t < (concert.getReservedVipSeats()+vipSeats); t++ ){
                    vipSeatArrange = vipSeatArrange + "VipST-" + (t + 1) + ", ";
                }
            } else {
                vipSeatArrange = "N/A";
            }

            //Combine all the unique normal seats number and vip seats number in one single String
            finalSeatArrange = "Normal = " + normalSeatArrange + " | VIP = " + vipSeatArrange;

        finalPrice = ((concert.getBaseTicketPrice() * normalSeats) + (concert.getVipPrice() * vipSeats));

            //If the reservation is made 5 days before the Event start, Attendee will get a 10& discount
            if (daysUntilEvent(e) >= 5) {
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

            //Display a summary of reservation before Attendee confirm it
            e.reservationSummary(e, finalSeatArrange, ticketQuantity, finalPrice);

            while (!validInput) {
                System.out.print("Confirm reservation? You will get [" + loyaltyPoint + "] loyalty points after payment. (y/n)");
                userInput = sc.nextLine();

                if (userInput.equalsIgnoreCase("y")) {
                    while (!validInput) {
                        paymentMethods();  //Display all payment methods
                        userInput = sc.nextLine();
                        if (userInput.equals("1") || userInput.equals("2") || userInput.equals("3")) {
                            LocalDateTime now = LocalDateTime.now();
                            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            bookingTime = now.format(formatter1);

                            attendee.setLoyaltyPoint(loyaltyPointSetter+loyaltyPoint);
                            concert.setReservedSeats(concert.getReservedSeats()+normalSeats);
                            concert.setReservedVipSeats(concert.getReservedVipSeats()+vipSeats);

                            //Create new Reservation
                            int nextId = Main.reservationList.size() + 1;
                            TicketReservation newReservation = new TicketReservation(nextId, Main.loggedInUser, e, finalSeatArrange, ticketQuantity, bookingTime, finalPrice);
                            //Create new Ticket
                            nextId = Main.digitalTicketList.size() + 1;
                            DigitalTicket newTicket = new DigitalTicket(nextId, newReservation);

                            Main.reservationList.add(newReservation);
                            Main.digitalTicketList.add(newTicket);
                            System.out.println("Thank you for the payment!");
                            System.out.println("Reservation created successfully.");
                            validInput = true;
                        } else if (userInput.equals("4")) {
                            System.out.println("Reservation cancelled.");
                            UserDashboard.pressEnterToContinue(sc);
                            validInput = true;
                        } else {
                            System.out.println("Please choose between 1-4 only.");
                            UserDashboard.pressEnterToContinue(sc);
                            System.out.println("=======================================");
                        }
                    }

                } else if (userInput.equalsIgnoreCase("n")) {
                    System.out.println("Reservation cancelled.");
                    UserDashboard.pressEnterToContinue(sc);
                    validInput = true;
                } else {
                    System.out.println("Please enter Y or N only.");
                }
            }

        } else {
            ConferenceEvent conference = (ConferenceEvent) e;
            System.out.println("Please enter the amount of Normal (" + (e.getMaxCapacity()-e.getReservedSeats()) + " left) seats you want to reserve." ); 
            while (!validInput){
                System.out.println("Normal: ");

                if (sc.hasNextInt()){
                    normalSeats = sc.nextInt();
                    ticketQuantity = normalSeats;
                    if (normalSeats > (e.getMaxCapacity()-e.getReservedSeats())){
                        System.out.println("Only " + (e.getMaxCapacity()-e.getReservedSeats()) + " Normal seats left, please enter again.");
                    } else if (normalSeats <= 0){
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

            //Create unique seats number for normal seats; return N/A if 0 seats are chosen
            if (normalSeats != 0){
                for (int t = e.getReservedSeats(); t < (conference.getReservedSeats()+normalSeats); t++ ){
                    normalSeatArrange = normalSeatArrange + "ST-" + (t + 1) + ", ";
                }
            } else {
                normalSeatArrange = "N/A";
            }

            //A final String for all normal seats number
            finalSeatArrange = "Normal = " + normalSeatArrange;

            finalPrice = (conference.getBaseTicketPrice() * normalSeats);

            //If the reservation is made 5 days before the Event start, Attendee will get a 10& discount
            if (daysUntilEvent(e) >= 5) {
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

            //Display a summary of reservation before Attendee confirm it
            e.reservationSummary(e, finalSeatArrange, ticketQuantity, finalPrice);

            while (!validInput) {
                System.out.print("Confirm reservation? You will get [" + loyaltyPoint + "] loyalty points after payment. (y/n)");
                userInput = sc.nextLine();

                if (userInput.equalsIgnoreCase("y")) {
                    while (!validInput) {
                        paymentMethods();  //Display all payment methods
                        userInput = sc.nextLine();
                        if (userInput.equals("1") || userInput.equals("2") || userInput.equals("3")) {  //If attendee choose any payment methods provided

                            LocalDateTime now = LocalDateTime.now();
                            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            bookingTime = now.format(formatter1);

                            attendee.setLoyaltyPoint(loyaltyPointSetter+loyaltyPoint);
                            conference.setReservedSeats(conference.getReservedSeats()+normalSeats);

                            //Create new Reservation
                            int nextId = Main.reservationList.size() + 1;
                            TicketReservation newReservation = new TicketReservation(nextId, Main.loggedInUser, e, finalSeatArrange, ticketQuantity, bookingTime, finalPrice);
                            //Create new Ticekt
                            nextId = Main.digitalTicketList.size() + 1;
                            DigitalTicket newTicket = new DigitalTicket(nextId, newReservation);

                            Main.reservationList.add(newReservation);
                            Main.digitalTicketList.add(newTicket);
                            System.out.println("Thank you for the payment!");
                            System.out.println("Reservation created successfully.");
                            validInput = true;
                        } else if (userInput.equals("4")) {  //If Attendee cancel the reservation
                            System.out.println("Reservation cancelled.");
                            UserDashboard.pressEnterToContinue(sc);
                            validInput = true;
                        } else {
                            System.out.println("Please choose between 1-4 only.");
                            UserDashboard.pressEnterToContinue(sc);
                            System.out.println("=======================================");
                        }
                    }

                } else if (userInput.equalsIgnoreCase("n")) {
                    System.out.println("Reservation cancelled.");
                    UserDashboard.pressEnterToContinue(sc);
                    validInput = true;
                } else {
                    System.out.println("Please enter Y or N only.");
                }
            }

        }  
    }   

}
