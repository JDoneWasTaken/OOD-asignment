package menu;

import java.util.Scanner;

import main.Main;
import reservation.DigitalTicket;
import reservation.TicketReservation;

public class ViewReservations {
    /**
     * Display the logged in user's all passed reservations
     * @param sc
     */
    public static void displayReservations(Scanner sc) {
        boolean hasReservation = false;
        int index = 1;
        System.out.println("===========================================");
        System.out.println("            EVENT RESERVATIONS             ");
        System.out.println("===========================================");

        //Simple for loop that display all the logged in Attendee's pass reservation invoices
        for (TicketReservation r: Main.reservationList) {
            if (r.getAttendee().equals(Main.loggedInUser)) {
                System.out.println("              ~ Event " + index + " ~           ");
                r.displayReservation();
                hasReservation = true;
                index ++;
            }
        }

        //Simple message if they don't have any reservation yet
        if (!hasReservation) {
            System.out.println("You haven't make any reservation.");
            UserDashboard.pressEnterToContinue(sc);
        } else {
            UserDashboard.pressEnterToContinue(sc);
        }
    }

    /**
     * Display the logged in user's all digital tickets
     * @param sc
     */
    public static void displayTickets(Scanner sc) {
        boolean hasTickets = false;
        int index = 1;
        System.out.println("=========================================");
        System.out.println("          [#]  Digital Ticket             ");
        System.out.println("=========================================");

        //Simple for loop that display all the logged in Attendee's digital tickets
        for (DigitalTicket r: Main.digitalTicketList) {
            if (r.getReservation().getAttendee().equals(Main.loggedInUser)) {
                System.out.println("              ~ Ticket " + index + " ~           ");
                r.viewTicket();
                hasTickets = true;
                index ++;
            }
        }


        //Simple message if they don't have any ticket yet
        if (!hasTickets) {
            System.out.println("You don't have any ticket yet.");
            UserDashboard.pressEnterToContinue(sc);
        } else {
            UserDashboard.pressEnterToContinue(sc);
        }
    }
}
