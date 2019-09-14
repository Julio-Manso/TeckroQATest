package ie.teckro.client;

public interface HotelBookingClient {
    HotelBookingCheckAvailability getAvailability (String date);

    HotelBookingCheckAvailability getNumberOfAvailableRooms();

    HotelBookingCheckAvailability getPrice ();

    BookRoom postRoomBooking (BookRoom requestBody);

    BookRoom getCheckOutDate ();
}
