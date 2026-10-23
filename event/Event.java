package event;

/**
 * An abstract parent class for events
 * Event
 */
public abstract class Event {
    private int eventID;
    private String eventName;
    private String venue;
    private String eventDate;
    private double baseTicketPrice;
    private int maxCapacity;
    private int reservedSeats;

    public Event (int eventID, String eventName, String eventDate, String venue, double baseTicketPrice, int maxCapacity, int reservedSeats) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venue = venue;
        this.baseTicketPrice = baseTicketPrice;
        this.maxCapacity = maxCapacity;
        this.reservedSeats = reservedSeats;
    }

    public int getEventID() {
        return this.eventID;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate(){
        return this.eventDate;
    }

    public String getVenue() {
        return this.venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public double getBaseTicketPrice() {
        return this.baseTicketPrice;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public int getReservedSeats() {
        return this.reservedSeats;
    }

    public void setReservedSeats(int reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    /**
     * A one line simple display of an event
     * @param numOfEvent to arrange the events is ascending order
     */
    public abstract void listingEventDetails(int numOfEvent);

    /**
     * Full details display of an Event
     */
    public abstract void displayEventDetails();

    /**
     * A summary of Event reservation by attendee, similar to displayEventDetails but with unique seats number
     * @param e Event chosen
     * @param finalSeatArrange A string that include all the unique seats number
     * @param ticketQuantity total ticket the attendee reserved
     * @param finalPrice final price after all calculation like earlybird discount and loyalty point discount
     */
    public abstract void reservationSummary(Event e, String finalSeatArrange, int ticketQuantity, double finalPrice);

    /**
     * Check if the event still have slots or not
     * @return true if no slots left, false if there are still slots
     */
    public abstract boolean zeroSeatCheck();
}