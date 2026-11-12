package event;

import main.Main;

public class ConcertEvent extends Event{
    private String artistName;
    private int vipSeats;
    private int reservedVipSeats;
    private double vipPrice;

    public ConcertEvent (int eventID, String eventName, String eventDate, String venue, double baseTicketPrice, int maxCapacity, int reservedSeats, String artistName, int vipSeats, int reservedVipSeats, double vipPrice) {
        super(eventID, eventName, eventDate, venue, baseTicketPrice, maxCapacity, reservedSeats);
        this.artistName = artistName;
        this.vipSeats= vipSeats;
        this.reservedVipSeats = reservedVipSeats;
        this.vipPrice = vipPrice;
    }

    public String getArtistName(){
        return this.artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getVipSeats(){
        return this.vipSeats;
    }

    public int getReservedVipSeats() {
        return this.reservedVipSeats;
    }

    public void setReservedVipSeats(int reservedVipSeats) {
        this.reservedVipSeats = reservedVipSeats;
    }

    public double getVipPrice(){
        return this.vipPrice;
    }

    @Override 
    public void listingEventDetails(int numOfEvent) {
        System.out.println(numOfEvent + ") (Concert) Title: " + getEventName() + " | Artist: " + getArtistName() + " | Date: " + getEventDate() + " | Venue: " + getVenue() + " | Seats left: Normal (" + (getMaxCapacity()-getReservedSeats()) + ") VIP (" + (getVipSeats()-getReservedVipSeats())   + ") | Price: Normal Ticket ~ " + String.format("%,.2f",getBaseTicketPrice()) + " VIP ticket ~ " + String.format("%,.2f",getVipPrice()));
    }

    @Override 
    public void displayEventDetails() {
        System.out.println("===============================================");
        System.out.println("               ~ Concert Event ~               ");
        System.out.println("-----------------------------------------------");
        System.out.println("Event name: " + getEventName());
        System.out.println("Artist: " + artistName);
        System.out.println("Date: " + getEventDate());
        System.out.println("Venue: " + getVenue());
        System.out.println("Total seats: Normal = " + (getMaxCapacity()) + " (" + (getMaxCapacity()-getReservedSeats()) + " left) | VIP = " + (getVipSeats()) + " (" + (getVipSeats()-getReservedVipSeats()) + " left)");
        System.out.println("Ticket Price: Normal = RM" + String.format("%,.2f", getBaseTicketPrice()) + "  VIP = RM" + String.format("%,.2f", getVipPrice()));
        System.out.println("===============================================");
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
        int remainNormalSeat = getMaxCapacity() - getReservedSeats();
        int remainVipSeat = getVipSeats() - getReservedVipSeats();
        if (remainNormalSeat == 0 && remainVipSeat == 0) {
            return true;
        } else {
            return false;
        }
    }
}