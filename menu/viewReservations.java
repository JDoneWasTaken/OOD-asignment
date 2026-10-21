package menu;

import java.util.ArrayList;
import java.util.Scanner;

import main.Main;
import reservation.digitalTicket;
import reservation.ticketReservation;

public class viewReservations {
    public static void displayReservations(Scanner sc) {
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
            userDashboard.pressEnterToContinue(sc);
        } else {
            userDashboard.pressEnterToContinue(sc);
        }
    }

    public static void displayTickets(Scanner sc) {
        boolean hasTickets = false;
        int index = 1;
        System.out.println("=========================================");
        System.out.println("          [#]  Digital Ticket             ");
        System.out.println("=========================================");

        ArrayList<digitalTicket> myTickets = new ArrayList<>();

        for (digitalTicket r: Main.digitalTicketList) {
            if (r.getReservation().getAttendee().equals(Main.loggedInUser)) {
                myTickets.add(r);
                System.out.println("              ~ Ticket " + index + " ~           ");
                r.viewTicket();
                hasTickets = true;
                index ++;
            }
        }


        if (!hasTickets) {
            System.out.println("You don't have any ticket yet.");
            userDashboard.pressEnterToContinue(sc);
        } else {
            userDashboard.pressEnterToContinue(sc);
        }
    }
}
