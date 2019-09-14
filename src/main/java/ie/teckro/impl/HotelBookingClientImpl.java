package ie.teckro.impl;

import ie.teckro.client.BookRoom;
import ie.teckro.client.HotelBookingCheckAvailability;
import ie.teckro.client.HotelBookingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HotelBookingClientImpl implements HotelBookingClient {

    @Value("${book.room.url}")
    private String bookRoomUrl;

    @Value("${check.availability.url}")
    private String checkAvailabilityUrl;

    @Autowired
    @Qualifier("ServiceRestTemplate")
    private RestTemplate restTemplate;

    @Override
    public HotelBookingCheckAvailability getAvailability(String date) {
        String url = String.format("%s/%s", checkAvailabilityUrl, date);
        return restTemplate.getForObject(url, HotelBookingCheckAvailability.class);
    }

    @Override
    public HotelBookingCheckAvailability getNumberOfAvailableRooms() {
        String url = String.format("%s/%s", checkAvailabilityUrl, "2019-02-22");
        return restTemplate.getForObject(url, HotelBookingCheckAvailability.class);
    }

    @Override
    public HotelBookingCheckAvailability getPrice() {
        String url = String.format("%s/%s", checkAvailabilityUrl, "2019-02-22");
        return restTemplate.getForObject(url, HotelBookingCheckAvailability.class);
    }

    @Override
    public BookRoom postRoomBooking(BookRoom requestBody) {
        String url = String.format(bookRoomUrl);
        return restTemplate.postForObject(url, requestBody, BookRoom.class);
    }

    @Override
    public BookRoom getCheckOutDate() {
        String url = String.format(bookRoomUrl);
        return restTemplate.getForObject(url, BookRoom.class);
    }
}
