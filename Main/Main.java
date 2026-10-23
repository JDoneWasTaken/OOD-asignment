package main;

import java.util.Scanner;
import java.util.ArrayList;
import user.User;
import user.Admin;
import user.Attendee;
import event.Event;
import event.ConcertEvent;
import event.ConferenceEvent;
import menu.UserDashboard;
import menu.UserMenu;
import reservation.DigitalTicket;
import reservation.TicketReservation;


public class Main{
    public static boolean isRunning = true;
    public static User loggedInUser = null;

    //ArrayLists that store all User, Event, Reservation and Ticket created
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Event> eventList = new ArrayList<>();
    public static ArrayList<TicketReservation> reservationList = new ArrayList<>();
    public static ArrayList<DigitalTicket> digitalTicketList = new ArrayList<>();


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //Pre-generated Users (1 Admin, 1 Attendee)
        Admin user1 = new Admin(1, "Joseph", "123");
        Attendee user2 = new Attendee(2, "Bennett", "321", 0);

        //Pre-generated Events (1 Concert, 1 Conference)
        ConcertEvent event1 = new ConcertEvent(1, "MCKL Tour", "2026-10-30", "MCKL", 200, 300, 0, "Jason", 30, 0,  400);
        ConferenceEvent event2 = new ConferenceEvent(2, "JAVA for beginner","2026-11-01", "Lalaport", 10, 20, 0, "Galvin");

        userList.add(user1);
        userList.add(user2);

        eventList.add(event1);
        eventList.add(event2);

        //Program will only stop when IsRunning become false
        while (isRunning){
            System.out.println("=========================================");
            System.out.println("Welcome to the Event Ticketing System");
            System.out.println("=========================================");
            
            UserMenu.firstPage(sc);

            UserMenu.signUpPage(sc);

            UserMenu.logInPage(sc);

            UserDashboard.dashboardPage(sc);
        }
        sc.close();
    }
}