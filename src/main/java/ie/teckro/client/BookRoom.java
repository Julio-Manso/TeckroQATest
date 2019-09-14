package ie.teckro.client;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Julio Manso on 14/09/2019
 */
@Data
public class BookRoom implements Serializable {
    private String checkInDate;
    private String checkOutDate;
    private int numOfDays;
    private int totalPrice;

    public BookRoom(){

    }

    public String getCheckInDate(){
        return this.checkInDate;
    }
    public String getCheckOutDate(){
        return this.checkOutDate;
    }
    public int getNumOfDays(){
        return this.numOfDays;
    }
    public int getTotalPirce(){return this.totalPrice;}
}
