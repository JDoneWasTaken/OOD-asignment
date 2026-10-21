package main;

import java.util.Scanner;
import java.util.ArrayList;
import user.User;
import user.Admin;
import user.Attendee;
import event.Event;
import event.ConcertEvent;
import event.ConferenceEvent;
import menu.userMenu;
import menu.userDashboard;
import reservation.digitalTicket;
import reservation.ticketReservation;


public class Main{
    public static boolean isRunning = true;
    public static boolean firstPage = true;
    public static boolean signUpPage = false;
    public static boolean logInPage = false;
    public static boolean attendeePage = false;
    public static boolean adminPage = false;
    public static User loggedInUser = null;
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Event> eventList = new ArrayList<>();
    public static ArrayList<ticketReservation> reservationList = new ArrayList<>();
    public static ArrayList<digitalTicket> digitalTicketList = new ArrayList<>();


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        Admin user1 = new Admin(1, "Joseph", "123");
        Attendee user2 = new Attendee(2, "Bennett", "321", 0);

        ConcertEvent event1 = new ConcertEvent(1, "MCKL Tour", "2026-10-30", "MCKL", 200, 300, 0, "Jason", 30, 0,  400);
        ConferenceEvent event2 = new ConferenceEvent(2, "JAVA for beginner","2026-11-01", "Lalaport", 10, 20, 0, "Galvin");

        userList.add(user1);
        userList.add(user2);

        eventList.add(event1);
        eventList.add(event2);

        while (isRunning){
            System.out.println("=========================================");
            System.out.println("Welcome to the Event Ticketing System");
            System.out.println("=========================================");
            
            userMenu.firstPage(sc);

            userMenu.signUpPage(sc);

            userMenu.logInPage(sc);

            userDashboard.dashboardPage(sc);
            //dashboardPage(sc, eventList);
            
            //isRunning = false;
        }
        sc.close();
    }
}