package event;

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

    public abstract void displayEventDetails();
}