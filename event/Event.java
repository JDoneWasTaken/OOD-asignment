package event;

public abstract class Event {
    private int eventID;
    private String eventName;
    private String venue;
    private String eventDate;
    private double baseTicketPrice;
    private int maxCapacity;

    public Event (int eventID, String eventName, String eventDate, String venue, double baseTicketPrice, int maxCapacity) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venue = venue;
        this.baseTicketPrice = baseTicketPrice;
        this.maxCapacity = maxCapacity;
    }

    public int getEventID() {
        return this.eventID;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getEventDate(){
        return this.eventDate;
    }

    public String getVenue() {
        return this.venue;
    }

    public double getBaseTicketPrice() {
        return this.baseTicketPrice;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public abstract void displayEventDetails();
}