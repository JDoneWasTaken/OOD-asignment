package event;

import main.Main;

/**
 * A child class that extend from the parent Event
 * ConferenceEvent
 */
public class ConferenceEvent extends Event{
    private String speakerName;

    public ConferenceEvent (int eventID, String eventName,String eventDate, String venue, double baseTicketPrice, int maxCapacity, int reservedSeats, String speakerName) {
        super(eventID, eventName, eventDate, venue, baseTicketPrice, maxCapacity, reservedSeats);
        this.speakerName = speakerName;
    }

    public String getSpeakerName(){
        return this.speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    @Override 
    public void listingEventDetails(int numOfEvent) {
        System.out.println(numOfEvent + ") (Conference) Title: " + getEventName() + " | Speaker: " + getSpeakerName() + " | Date: " + getEventDate() + " | Venue: " + getVenue() + " | Seats left: " + (getMaxCapacity()-getReservedSeats()) + " | Ticket Price: " + String.format("%,.2f",getBaseTicketPrice()));
    }
    @Override 
    public void displayEventDetails() {
        System.out.println("========================================");
        System.out.println("         ~ Conference Event ~           ");
        System.out.println("----------------------------------------");
        System.out.println("Event name: " + getEventName());
        System.out.println("Speaker :" + speakerName);
        System.out.println("Date: " + getEventDate());
        System.out.println("Venue: " + getVenue());
        System.out.println("Total seats " + getMaxCapacity() + " (" + (getMaxCapacity()-getReservedSeats()) + " left)");
        System.out.println("Ticket Price: RM" + String.format("%,.2f" ,getBaseTicketPrice()));
        System.out.println("========================================");
    }

    @Override 
    public void reservationSummary(Event e, String finalSeatArrange, int ticketQuantity, double finalPrice) {
        System.out.println("========================================================");
        System.out.println("                   Reservation Summary                  ");
        System.out.println("--------------------------------------------------------");
        System.out.println("Event name: " + e.getEventName());
        System.out.println("Reserved by: " + Main.loggedInUser.getUsername());
        System.out.println("Venue: " + e.getVenue());
        System.out.println("Reserved Seats: " + finalSeatArrange);
        System.out.println("Ticket quantity: " + ticketQuantity);
        System.out.println("--------------------------------------------------------");
        System.out.println("Final Price: RM" + String.format("%,.2f",finalPrice));
        System.out.println("(After deducting loyalty points and early bird discount.)");
        System.out.println("========================================================");
    }

    @Override 
    public boolean zeroSeatCheck() {
        int remainSeat = getMaxCapacity() - getReservedSeats();
        if (remainSeat == 0) {
            return true;
        } else {
            return false;
        }
    }
 }   