package event;

public class ConcertEvent extends Event{
    private String artistName;
    private double vipSeatPercentage;
    private double vipPriceMultiplier;

    public ConcertEvent (int eventID, String eventName, String eventDate, String venue, double baseTicketPrice, int maxCapacity, String artistName, double vipSeatPercentage, double vipPriceMultiplier) {
        super(eventID, eventName, eventDate, venue, baseTicketPrice, maxCapacity);
        this.artistName = artistName;
        this.vipSeatPercentage = vipSeatPercentage;
        this.vipPriceMultiplier = vipPriceMultiplier;
    }

    public String getArtistName(){
        return this.artistName;
    }

    public double getVipSeatPercentage(){
        return this.vipSeatPercentage;
    }

    public double getVipPriceMultiplier(){
        return this.vipPriceMultiplier;
    }

    @Override 
    public void displayEventDetails() {
        System.out.println("========================================");
        System.out.println("            ~ Concert Event ~           ");
        System.out.println("========================================");
        System.out.println("Event name: " + getEventName());
        System.out.println("Artist :" + artistName);
        System.out.println("Date: " + getEventDate());
        System.out.println("Venue: " + getVenue());
        System.out.println("Total seats left: Normal = " + (getMaxCapacity()*(1-vipSeatPercentage)) + "  VIP = " + (getMaxCapacity()*vipSeatPercentage));
        System.out.println("Ticket Price: Normal = RM" + getBaseTicketPrice() + "  VIP = RM" + (getBaseTicketPrice()*vipPriceMultiplier));
    }
}