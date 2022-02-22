package testNg;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class testsDynamoCrud {

    int intRandom = ThreadLocalRandom.current().nextInt();
    String nameToSend = "german" + intRandom;
    String nameToSendEdited = "german" + intRandom + "edited";
    WebDriver driver;
    By effectWebLocator = By.xpath("/html/body/app-root/app-list/ngx-spinner/div");
    By usersTableLocator = By.xpath("/html[1]/body[1]/app-root[1]/app-list[1]/table[1]/tbody[1]/tr");
    By buttonAddUserLocator = By.xpath("/html/body/app-root/app-list/button");
    By buttonEditFirstUserLocator = By.xpath("//tbody/tr[1]/td[4]/a[1]/button[1]");
    By inputNameLocator = By.id("name");
    By inputLastNameLocator = By.id("lastName");
    By buttonCreateUserLocator = By.xpath("/html/body/app-root/app-create/div/div/div/form/button[1]");
    //By buttonEditedUserLocator = By.xpath()


    @BeforeClass
    public void beforeClass() {

        System.setProperty("webdriver.chrome.driver", "src/main/java/testNg/resources/chromedriver/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://localhost:4200/");

    }

    @Test
    public void createUserSuccessfully() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(16));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(effectWebLocator));

        WebElement buttonAddUser = driver.findElement(buttonAddUserLocator);

        js.executeScript("arguments[0].scrollIntoView();", buttonAddUser);

        buttonAddUser.click();

        WebElement inputName = driver.findElement(inputNameLocator);
        inputName.click();
        inputName.sendKeys(nameToSend);

        WebElement inputLastName = driver.findElement(inputLastNameLocator);
        inputLastName.click();
        inputLastName.sendKeys("Kohn");


        WebElement buttonCreateUSer = driver.findElement(buttonCreateUserLocator);
        buttonCreateUSer.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(effectWebLocator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usersTableLocator));

        List<WebElement> users = driver.findElements(usersTableLocator);


        //users.stream().filter();table
        long count = users.stream().count();
        Stream<WebElement> stream = users.stream();

        WebElement lastUser = stream.skip(count - 1).findFirst().get();

        WebElement firstUser = users.stream().sorted().findFirst().get();
        //Assert.assertEquals(driver.findElement(usersTableLocator).getAttribute("td").contains(nameToSend), nameToSend, "The user"+ nameToSend +"is not created");
    }

    @Test
    public void editNameUserSuccessfully() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(16));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(effectWebLocator));

        WebElement buttonEditFirstUser = driver.findElement(buttonEditFirstUserLocator);

        js.executeScript("arguments[0].scrollIntoView();", buttonEditFirstUser);

        buttonEditFirstUser.click();

        WebElement inputName = driver.findElement(inputNameLocator);
        inputName.click();
        inputName.clear();
        inputName.sendKeys(nameToSendEdited);


        WebElement buttonCreateUSer = driver.findElement(buttonCreateUserLocator);
        buttonCreateUSer.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(effectWebLocator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usersTableLocator));

        Assert.assertEquals(driver.findElement(usersTableLocator).getAttribute("td").contains(nameToSend), nameToSend, "The user" + nameToSend + "is not created");
    }

    @Test
    public void editLastNameUserSuccessfully() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(16));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(effectWebLocator));

        WebElement buttonEditFirstUser = driver.findElement(buttonEditFirstUserLocator);

        js.executeScript("arguments[0].scrollIntoView();", buttonEditFirstUser);

        buttonEditFirstUser.click();

        WebElement inputName = driver.findElement(inputNameLocator);
        inputName.click();
        inputName.clear();
        inputName.sendKeys(nameToSendEdited);


        WebElement buttonCreateUSer = driver.findElement(buttonCreateUserLocator);
        buttonCreateUSer.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(effectWebLocator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usersTableLocator));

        Assert.assertEquals(driver.findElement(usersTableLocator).getAttribute("td").contains(nameToSend), nameToSend, "The user" + nameToSend + "is not created");
    }

    @Test
    public void deleteUserSuccessfully() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(16));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(effectWebLocator));

        WebElement buttonEditFirstUser = driver.findElement(buttonEditFirstUserLocator);

        js.executeScript("arguments[0].scrollIntoView();", buttonEditFirstUser);

        buttonEditFirstUser.click();

        WebElement inputName = driver.findElement(inputNameLocator);
        inputName.click();
        inputName.clear();
        inputName.sendKeys(nameToSendEdited);


        WebElement buttonCreateUSer = driver.findElement(buttonCreateUserLocator);
        buttonCreateUSer.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(effectWebLocator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usersTableLocator));

        Assert.assertEquals(driver.findElement(usersTableLocator).getAttribute("td").contains(nameToSend), nameToSend, "The user" + nameToSend + "is not created");
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }


}