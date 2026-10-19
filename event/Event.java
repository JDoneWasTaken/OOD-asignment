package event;

public abstract class Event {
    private int eventID;
    private String eventName;
    private String venue;
    private double baseTicketPrice;
    private int maxCapacity;

    public Event (int eventID, String eventName, String venue, double baseTicketPrice, int maxCapacity) {
        this.eventID = eventID;
        this.eventName = eventName;
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