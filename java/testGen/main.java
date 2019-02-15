package testGen;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.*;


public class main {

    @Test
    public void testSearch(){
        System.setProperty("webdriver.gecko.driver", "src\\driver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("https://www.booking.com/");
        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        homePage.openCalendar();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        homePage.selectMonth("September", "1", "30", "2019");
        homePage.sendKeysToInput("New York");
        driver.findElement(homePage.searchButton).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Search search = new Search(driver);
        WebElement checkInDate = driver.findElement(search.checkIn);
        Assert.assertTrue(checkInDate.getText().contains("September 1, 2019"));
        WebElement checkOutDate = driver.findElement(search.checkOut);
        Assert.assertTrue(checkOutDate.getText().contains("September 30, 2019"));
        List<WebElement> allHotelsContent = search.getHotelsCity();
        for (WebElement elementNewYork : allHotelsContent) {
            Assert.assertTrue(elementNewYork.getText().contains("New York"));
        }
    }

    @Test
    public void lowestToHighest() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "src\\driver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("https://www.booking.com/");
        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        homePage.openCalendar();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        homePage.selectMonth("March", "1", "8", "2019");
        homePage.sendKeysToInput("Lviv");
        driver.findElement(homePage.searchButton).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Search search = new Search(driver);
        WebElement element = driver.findElement(search.sortLowestFirst);
        element.click();
        Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<WebElement> allHotelsContent = search.getHotelsPrices();

            for (int i = 1; i < allHotelsContent.size(); i++) {
                try {
                WebElement webElement = allHotelsContent.get(i -1);
                WebElement webElement1 = allHotelsContent.get(i);
                String text = webElement.getText().replace("UAH", "").replace(",", ".").replace(" ", "");
                String text1 = webElement1.getText().replace("UAH", "").replace(",", ".").replace(" ", "");
                double number1 = Double.parseDouble(text);
                double number2 = Double.parseDouble(text1);
                Assert.assertTrue(number1 <= number2);
            }catch (IndexOutOfBoundsException e){
                break;
            }

            }


    }
    @Test
    public void testSearchAddRoom(){
        System.setProperty("webdriver.gecko.driver", "src\\driver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("https://www.booking.com/");
        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        homePage.openCalendar();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        homePage.selectMonth("September", "1", "30", "2019");
        homePage.sendKeysToInput("New York");
        driver.findElement(homePage.guests).click();
        driver.findElement(homePage.roomsAdd).click();
        driver.findElement(homePage.searchButton).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Search search = new Search(driver);
        WebElement room = search.getSelectedRoomsNumber();
        Assert.assertTrue(room.getText().contains("2 rooms"));

    }

    }




