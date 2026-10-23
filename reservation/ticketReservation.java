package reservation;

import user.User;
import event.Event;

/**
 * Reservation class that record down every reservation transaction made by Attendee
 * TicketReservation
 */
public class TicketReservation{
    private int reservationID;
    private User attendee;
    private Event bookedEvent;
    private String seatNumber;
    private int ticketQuantity;
    private String bookingTime;
    private double finalPrice;

    public TicketReservation (int reservationID, User attendee, Event bookedEvent, String seatNumber, int ticketQuantity, String bookingTime, double finalPrice) {
        this.reservationID = reservationID;
        this.attendee = attendee;
        this.bookedEvent = bookedEvent;
        this.seatNumber = seatNumber;
        this.ticketQuantity = ticketQuantity;
        this.bookingTime = bookingTime;
        this.finalPrice = finalPrice;
    }

    public int getReservationID(){
        return this.reservationID;
    }

    public User getAttendee() {
        return this.attendee;
    }

    public Event getBookedEvent() {
        return this.bookedEvent;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public int getTicketQuantity() {
        return this.ticketQuantity;
    }

    public String getBookingTime(){
        return this.bookingTime;
    }

    public double getFinalPrice(){
        return this.finalPrice;
    }

    /**
     * Display a summary of reservation
     */
    public void displayReservation() {
        System.out.println("-------------------------------------------");
        System.out.println("Event name: " + bookedEvent.getEventName());
        System.out.println("Reserved by: " + attendee.getUsername());
        System.out.println("Venue: " + bookedEvent.getVenue());
        System.out.println("Reserved Seats: " + getSeatNumber());
        System.out.println("Booking time: " + getBookingTime());
        System.out.println("Ticket quantity: " + getTicketQuantity());
        System.out.println("------------------------------------------");
        System.out.println("Final Price: RM" + (String.format("%,.2f",getFinalPrice())));
        System.out.println("===========================================");
    }

}