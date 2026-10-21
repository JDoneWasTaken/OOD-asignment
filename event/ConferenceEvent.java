package event;

public class ConferenceEvent extends Event{
    private String speakerName;

    public ConferenceEvent (int eventID, String eventName,String eventDate, String venue, double baseTicketPrice, int maxCapacity, int reservedSeats, String speakerName) {
        super(eventID, eventName, eventDate, venue, baseTicketPrice, maxCapacity, reservedSeats);
        this.speakerName = speakerName;
    }

    public String getSpeakerName(){
        return this.speakerName;
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
        System.out.println("Ticket Price: RM" + getBaseTicketPrice());
        System.out.println("========================================");
    }
 }   