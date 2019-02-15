package testGen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.security.PublicKey;
import java.util.List;

public class HomePage {
    final WebDriver  driver;
     By calendar = By.xpath("//div[contains(@class, 'xp__dates-inner')]");
    static By monthSwapButton =  By.xpath("//div[contains(@data-bui-ref, 'calendar-next')]");
    By monthName =  By.xpath("//div[contains(@class, 'bui-calendar__month')]");
    By monthDays =  By.xpath("//table[contains(@class, 'bui-calendar__dates')]");
    By inputField =  By.xpath("//*[@id=\"ss\"]");
    By searchButton =  By.xpath("//button[contains(@type, 'submit')]");
    By guests =  By.xpath("//label[contains(@class, 'xp__input')]");
    By roomsAdd =  By.xpath("/html/body/div[3]/div/div/div[2]/form/div[1]/div[3]/div[2]/div/div/div[1]/div/div[2]/button[2]");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }



    public HomePage openCalendar() {
        WebElement element = driver.findElement(calendar);
        element.click();
         return this;
    }

    public String findMonth() {
        WebElement monthElement1 = driver.findElement(monthName);
        String month = monthElement1.getText();
        return month;
    }


    public HomePage selectMonth(String searchedMonth, String firstDate, String secondDate, String year){
        WebElement buttoSwapMonth = driver.findElement(HomePage.monthSwapButton);
        boolean findMonth = false;
        while (findMonth == false) {
            buttoSwapMonth.click();

            String month = findMonth();
            if (month.equals(searchedMonth + " "+ year)) {
                List<WebElement> tableContent = findMonthTableContent();
                for (WebElement tableElement : tableContent) {
                    String date = tableElement.getText();
                    if (date.equals(firstDate)) {
                        tableElement.click();
                    } else if (date.equals(secondDate)) {
                        tableElement.click();
                    }
                }
                findMonth = true;
            }
        }
        return this;
    }



    public List<WebElement> findMonthTableContent() {
        WebElement table = driver.findElement(monthDays) ;
        List <WebElement> tableContent = table.findElements(By.tagName("td"));
        return tableContent;
    }

    public HomePage sendKeysToInput(String str) {
        WebElement input = driver.findElement(inputField);
        input.sendKeys(str);
        return this;
    }
    public HomePage searchButtonClick(String str) {
        WebElement searchButton = driver.findElement(this.searchButton);
        searchButton.click();
        return this;
    }


}
