package event;

public class ConcertEvent extends Event{
    private String artistName;
    private double vipSeatPercentage;
    private double vipPriceMultiplier;

    public ConcertEvent (int eventID, String eventName, String venue, double baseTicketPrice, int maxCapacity, String artistName, double vipSeatPercentage, double vipPriceMultiplier) {
        super(eventID, eventName, venue, baseTicketPrice, maxCapacity);
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

    }
}