package ie.teckro.client;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Julio Manso on 14/09/2019
 */
@Component
@Data
public class HotelBookingCheckAvailability  implements Serializable {
    private String date;
    private int rooms_available;
    private int price;

    public HotelBookingCheckAvailability(){

    }

    public int rooms_available(){
        return this.rooms_available;
    }
    public int getPrice(){
        return this.price;
    }
}
