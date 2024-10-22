import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tests {

    @BeforeAll
    public static void antes(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver/chromedriver.exe");
    }

    @Test
    public  void login() {

        // Initialize the webdriver
        WebDriver driver = new ChromeDriver();

        // Open the website
        driver.get("https://www.saucedemo.com");

        // Maximise the browser
        driver.manage().window().maximize();

        WebElement user = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        user.sendKeys("standard_user");

        WebElement pass = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        pass.sendKeys("standard_user");

        WebElement btn = driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        btn.click();

        
    }


}


