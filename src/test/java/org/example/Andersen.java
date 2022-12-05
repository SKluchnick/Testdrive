package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class Andersen {

    private WebDriver driver;
    private Select select;
    private static String URL = "https://testdrive.andersenlab.com/";


    @BeforeClass
    public static void init() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void FillingFormIncorrectData() {
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        WebElement btn = driver.findElement(By.xpath("//a[@id='go_to_step_2']"));
        btn.click();
        WebElement listSelect = driver.findElement(By.xpath("//select[@id='engine']"));
        select = new Select(listSelect);
        select.selectByVisibleText("Бензиновый 3.5");
        WebElement btnTwo = driver.findElement(By.xpath("//a[@id='go_to_step_3']"));
        btnTwo.click();
        WebElement formLastName = driver.findElement(By.xpath("//input[@id='form_last_name']"));
        formLastName.sendKeys("@@@@");
        WebElement form_first_name = driver.findElement(By.xpath("//input[@id='form_first_name']"));
        form_first_name.sendKeys("@@@@");
        WebElement form_middle_name = driver.findElement(By.xpath("//input[@id='form_middle_name']"));
        form_middle_name.sendKeys("@@@@");
        WebElement form_age = driver.findElement(By.xpath("//input[@id='form_age']"));
        form_age.sendKeys("@@@@");
        WebElement form_phone = driver.findElement(By.xpath("//input[@id='form_phone']"));
        form_phone.clear();
        form_phone.sendKeys("@@@@");
        WebElement btnThree = driver.findElement(By.xpath("//a[@id='go_to_step_4']"));
        btnThree.click();
        WebElement outPutFio = driver.findElement(By.xpath("//span[@id='step_4_name']"));
        String resultFio = outPutFio.getText();
        Assert.assertEquals("@@@@ @@@@ @@@@", resultFio);
        WebElement outPutAge = driver.findElement(By.xpath("//span[@id='step_4_age']"));
        String resultAge = outPutAge.getText();
        Assert.assertEquals("@@@@", resultAge);
        WebElement outPutPhone = driver.findElement(By.xpath("//span[@id='step_4_phone']"));
        String resultPhone = outPutPhone.getText();
        Assert.assertEquals("@@@@", resultPhone);
        WebElement btnWrite = driver.findElement(By.cssSelector("#finish"));
        btnWrite.click();
        WebElement areaResult = driver.findElement(By.xpath("//div[@id='title']"));
        String result = areaResult.getText();
        Assert.assertEquals("Благодарим за запись на тест драйв!", result);
        System.out.println(result);
    }

    @Test
    public void checkSelect() {
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        WebElement sliderNext = driver.findElement(By.xpath("//span[@class='next']"));
        sliderNext.click();
        sliderNext.click();
        WebElement btn = driver.findElement(By.xpath("//a[@id='go_to_step_2']"));
        btn.click();
        WebElement listSelect = driver.findElement(By.xpath("//select[@id='engine']"));
        select = new Select(listSelect);
        select.selectByVisibleText("OutOfMemoryError");
        Assert.assertEquals("OutOfMemoryError",select.getFirstSelectedOption().getText());

    }

    @Test
    public void goBackToPage(){
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        WebElement btn = driver.findElement(By.xpath("//a[@id='go_to_step_2']"));
        btn.click();
        WebElement btnTwo = driver.findElement(By.xpath("//a[@id='go_to_step_3']"));
        btnTwo.click();
        driver.navigate().refresh();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("https://testdrive.andersenlab.com/",currentUrl);
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }

}
