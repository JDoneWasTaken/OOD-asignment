package reservation;

import user.User;
import event.Event;

public class ticketReservation{
    private int reservationID;
    private User attendee;
    private Event bookedEvent;
    private String[] seatNumber;
    private int ticketQuantity;
    private String bookingTime;
    private double finalPrice;

    public ticketReservation (int reservationID, User attendee, Event bookedEvent, String[] seatNumber, int ticketQuantity, String bookingTime, double finalPrice) {
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

    public String[] getSeatNumber() {
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

    public void displayReservation() {
        System.out.println("=========================================");
        System.out.println("           Event Reservation             ");
        System.out.println("-----------------------------------------");
        System.out.println("Event name: " + bookedEvent.getEventName());
        System.out.println("Reserved by: " + attendee.getUsername());
        System.out.println("Seat no: " + getSeatNumber());
        System.out.println("Booking time - " + getBookingTime());
        System.out.println("Ticket quantity: " + getTicketQuantity());
        System.out.println("-----------------------------------------");
        System.out.println("Final Price: " + (getFinalPrice()));
        System.out.println("=========================================");
    }

}