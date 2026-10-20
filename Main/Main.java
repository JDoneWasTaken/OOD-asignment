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


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        Admin user1 = new Admin(1, "Joseph", "12345678");
        Attendee user2 = new Attendee(2, "Bennett", "87654321");

        ConcertEvent event1 = new ConcertEvent(1, "first event", "mckl", 200, 300, "Jason", 0.4, 1.4);
        ConferenceEvent event2 = new ConferenceEvent(2, "second event", "MCKL", 10, 20, "Galvin");

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