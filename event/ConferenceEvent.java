package event;

public class ConferenceEvent extends Event{
    private String speakerName;

    public ConferenceEvent (int eventID, String eventName, String venue, double baseTicketPrice, int maxCapacity, String speakerName) {
        super(eventID, eventName, venue, baseTicketPrice, maxCapacity);
        this.speakerName = speakerName;
    }

    public String getSpeakerName(){
        return this.speakerName;
    }

    @Override 
    public void displayEventDetails() {

    }
 }   