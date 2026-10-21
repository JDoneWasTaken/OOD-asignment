package event;

public class ConcertEvent extends Event{
    private String artistName;
    private int vipSeats;
    private double vipPrice;

    public ConcertEvent (int eventID, String eventName, String eventDate, String venue, double baseTicketPrice, int maxCapacity, String artistName, int vipSeats, double vipPrice) {
        super(eventID, eventName, eventDate, venue, baseTicketPrice, maxCapacity);
        this.artistName = artistName;
        this.vipSeats= vipSeats;
        this.vipPrice = vipPrice;
    }

    public String getArtistName(){
        return this.artistName;
    }

    public int getVipSeats(){
        return this.vipSeats;
    }

    public double getVipPrice(){
        return this.vipPrice;
    }

    @Override 
    public void displayEventDetails() {
        System.out.println("===============================================");
        System.out.println("               ~ Concert Event ~               ");
        System.out.println("-----------------------------------------------");
        System.out.println("Event name: " + getEventName());
        System.out.println("Artist :" + artistName);
        System.out.println("Date: " + getEventDate());
        System.out.println("Venue: " + getVenue());
        System.out.println("Total seats left: Normal = " + (getMaxCapacity()) + "  VIP = " + (getVipSeats()));
        System.out.println("Ticket Price: Normal = RM" + getBaseTicketPrice() + "  VIP = RM" + (getVipPrice()));
        System.out.println("===============================================");
    }
}