package testGen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Search {
    final WebDriver driver;


    By checkIn = By.xpath("//div[contains(@data-placeholder, 'Check-in Date')]");
    By checkOut =  By.xpath("//div[contains(@data-placeholder, 'Check-out Date')]");
    By allHotels = By.xpath("//*[@id=\"hotellist_inner\"]");
    By hotelsContent = By.xpath("//a[contains(@class, 'visited_link')]");
    By hotelsPrices = By.tagName("strong");
    By sortLowestFirst = By.xpath("//a[contains(@data-category, 'price')]");
    By rooms = By.xpath("//select[contains(@id, 'no_rooms')]");
    By chekRoom = By.xpath("//option[contains(@selected, 'selected')]");

    public Search(WebDriver driver) {
        this.driver = driver;
    }


    public List getHotelsCity() {
        WebElement allHotels = driver.findElement(this.allHotels);
        List<WebElement> tableContent = allHotels.findElements(hotelsContent);
        return tableContent;
    }
    public List getHotelsPrices() {
        WebElement allHotels = driver.findElement(this.allHotels);
        List<WebElement> tableContent = allHotels.findElements(hotelsPrices);
        return tableContent;
    }
    public WebElement getSelectedRoomsNumber() {
        WebElement allRooms = driver.findElement(rooms);
        WebElement chekcedRoom = allRooms.findElement(rooms);
        return chekcedRoom;
    }



}
