package reservation;

public class ticketReservation{
    private int reservationID;
    private String bookingTime;
    private double finalPrice;

    public ticketReservation (int reservationID, String bookingTime, double finalPrice) {
        this.reservationID = reservationID;
        this.bookingTime = bookingTime;
        this.finalPrice = finalPrice;
    }

    public int getReservationID(){
        return this.reservationID;
    }

    public String getBookingTime(){
        return this.bookingTime;
    }

    public double getFinalPrice(){
        return this.finalPrice;
    }

}