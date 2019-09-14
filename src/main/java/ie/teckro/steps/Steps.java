package ie.teckro.steps;

import ie.teckro.TestConfig;
import ie.teckro.client.BookRoom;
import ie.teckro.client.ScenarioData;
import ie.teckro.impl.HotelBookingClientImpl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@ContextConfiguration(classes = TestConfig.class)
public class Steps {
    @Autowired
    private HotelBookingClientImpl hotelBookingClient;
    @Autowired
    private ScenarioData scenarioData;

    private WebDriver driver;

    @Given("the user that wants to check availability of room and inputs date {string}")
    public void theUserThatWantsToCheckAvailabilityOfRoomAndInputsDate(String date) {
            hotelBookingClient.getAvailability(date);
    }

    @Then("the user asserts that the number of rooms available is {int}")
    public void theUserAssertsThatTheNumberOfRoomsAvailableIs(int numberOfRoomsAvailable) {
        if (hotelBookingClient.getNumberOfAvailableRooms().getRooms_available()<0){
            System.out.println("the result should not go below 0" + hotelBookingClient.getNumberOfAvailableRooms().getRooms_available());
        }
        Assert.assertEquals(numberOfRoomsAvailable, hotelBookingClient.getNumberOfAvailableRooms().rooms_available());
    }

    @Then("the user asserts that the price for the room is {int}")
    public void theUserAssertsThatThePriceForTheRoomIs(int price) {
        Assert.assertEquals(price, hotelBookingClient.getNumberOfAvailableRooms().getPrice());
    }

    @Given("the user that books a room on the {string} for {string} days")
    public void theUserThatBooksARoomOnTheForDays(String checkInDate, String days) {
        int day = Integer.parseInt(days);
        BookRoom bookRoom = new BookRoom();
        bookRoom.setNumOfDays(day);
        bookRoom.setCheckInDate(checkInDate);
        BookRoom bookingRoom = hotelBookingClient.postRoomBooking(bookRoom);
        scenarioData.setBookRoom(bookingRoom);
    }

    @Then("the user asserts that the checkout date is on {string}")
    public void theUserAssertsThatTheCheckoutDateIsOn(String checkOutDate) {
        Assert.assertEquals(checkOutDate, scenarioData.getBookRoom().getCheckOutDate());
    }

    private void startWeb() {
        System.setProperty("webdriver.chrome.driver", "chromedriver//chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("http://localhost:4200/");
    }

    @Given("the user that checks availability for a room for date {string}")
    public void theUserThatBooksAHotelForDate(String date) throws Throwable {
        startWeb();
        WebElement pageinput = driver.findElement(By.id("mat-input-0"));
        pageinput.click();
        pageinput.sendKeys(date);
    }

    @Then("the user clicks check")
    public void theUserClicksCheck() {
        WebElement checkButton = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/div[3]/button"));
        checkButton.click();
    }

    @Then("the user receives response")
    public void theUserReceivesResponse() {
        WebElement responseFromChecking = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/pre"));
        Assert.assertTrue(responseFromChecking.isDisplayed());
    }

    @Then("the user asserts that they are blocked by this")
    public void theUserAssertsThatTheyAreBlockedByThis() {
        //if user receives response from this, then we know that that this is a bug
        //Calendar has no format
        //this will return a fail if the message doesnt display
        WebElement badRequestMessage = driver.findElement(By.xpath("/html/body/app-root/div/div[1]/pre"));
        Assert.assertTrue(badRequestMessage.isDisplayed());
    }

    @Then("the user asserts that they don't receive the same price as when they checked availability")
    public void theUserAssertsThatTheyDonTReceiveTheSamePriceAsWhenTheyCheckedAvailability() {
        Assert.assertEquals(scenarioData.getPriceFromCheck(),scenarioData.getPriceFromBooking());
    }

    @Given("the user that checks and books room on {string}")
    public void theUserThatChecksAndBooksRoomOn(String date) {
        hotelBookingClient.getAvailability(date);
        scenarioData.setPriceFromCheck(hotelBookingClient.getNumberOfAvailableRooms().getPrice());
        int day=1;
        BookRoom bookRoom = new BookRoom();
        bookRoom.setNumOfDays(day);
        bookRoom.setCheckInDate(date);
        BookRoom bookingRoom = hotelBookingClient.postRoomBooking(bookRoom);
        scenarioData.setBookRoom(bookingRoom);
        scenarioData.setPriceFromBooking(bookingRoom.getTotalPrice());
        hotelBookingClient.getAvailability(date);
    }
}
