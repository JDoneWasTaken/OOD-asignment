package event;

public class ConcertEvent extends Event{
    private String artistName;
    private int vipSeats;
    private int reservedVipSeats;
    private double vipPrice;

    public ConcertEvent (int eventID, String eventName, String eventDate, String venue, double baseTicketPrice, int maxCapacity, int reservedSeats, String artistName, int vipSeats, int reservedVipSeats, double vipPrice) {
        super(eventID, eventName, eventDate, venue, baseTicketPrice, maxCapacity, reservedSeats);
        this.artistName = artistName;
        this.vipSeats= vipSeats;
        this.reservedVipSeats = reservedVipSeats;
        this.vipPrice = vipPrice;
    }

    public String getArtistName(){
        return this.artistName;
    }

    public int getVipSeats(){
        return this.vipSeats;
    }

    public int getReservedVipSeats() {
        return this.reservedVipSeats;
    }

    public void setReservedVipSeats(int reservedVipSeats) {
        this.reservedVipSeats = reservedVipSeats;
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
        System.out.println("Total seats: Normal = " + (getMaxCapacity()) + " (" + (getMaxCapacity()-getReservedSeats()) + " left) | VIP = " + (getVipSeats()) + " (" + (getVipSeats()-getReservedVipSeats()) + " left)");
        System.out.println("Ticket Price: Normal = RM" + getBaseTicketPrice() + "  VIP = RM" + (getVipPrice()));
        System.out.println("===============================================");
    }
}