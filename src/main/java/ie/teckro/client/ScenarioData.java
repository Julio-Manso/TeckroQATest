package ie.teckro.client;

import lombok.Data;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
@Data
public class ScenarioData {
    private BookRoom bookRoom;
    private int priceFromCheck;
    private int priceFromBooking;
    private String checkInDate;
    private String date;
}
