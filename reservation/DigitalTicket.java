package reservation;

public class DigitalTicket {
    private int ticketID;
    private TicketReservation reservation;



    public DigitalTicket (int ticketID, TicketReservation reservation){
        this.ticketID = ticketID;
        this.reservation = reservation;
    }

    public int getTicketID(){
        return this.ticketID;
    }

    public TicketReservation getReservation() {
        return this.reservation;
    }

    public void viewTicket() {
        System.out.println("-----------------------------------------");
        System.out.println("Ticket ID: " + getTicketID());
        System.out.println("Event name: " + reservation.getBookedEvent().getEventName());
        System.out.println("Reserved by: " + reservation.getAttendee().getUsername());
        System.out.println("Venue: " + reservation.getBookedEvent().getVenue());
        System.out.println("Reserved Seats: " + reservation.getSeatNumber());
        System.out.println("Booking time : " + reservation.getBookingTime());
        System.out.println("-----------------------------------------");
        System.out.println("Please show this ticket at the entrance");
        System.out.println("=========================================");
    }
}
