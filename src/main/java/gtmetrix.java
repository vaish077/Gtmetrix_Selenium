import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class gtmetrix {

    public static String FindPercentage(String grade){
        String str="Invalid Grade";
        if(grade.equals("A"))
            str="91%-100%";
        else if(grade.equals("B"))
            str="81%-90%";
        else if(grade.equals("C"))
            str="71%-80%";
        else if(grade.equals("D"))
            str="61%-70%";
        else if(grade.equals("E"))
            str="51%-60%";
        else if(grade.equals("F"))
            str="41%-50%";
        else if(grade.equals("G"))
            str="31%-40%";
        else if(grade.equals("H"))
            str="21%-30%";
        else if(grade.equals("I"))
            str="11%-20%";
        else if(grade.equals("J"))
            str="0%-10%";
        return str;
    }
    public static String FindGrade(String InputGrade){
        int input=Integer.parseInt(InputGrade);
        String grade="Wrong Number";
        if(input<=10)
            grade="J";
        else if(input<=20 && input>10)
            grade="I";
        else if(input<=30 && input>20)
            grade="H";
        else if(input<=40 && input>30)
            grade="G";
        else if(input<=50 && input>40)
            grade="F";
        else if(input<=60 && input>50)
            grade="E";
        else if(input<=70 && input>60)
            grade="D";
        else if(input<=80 && input>70)
            grade="C";
        else if(input<=90 && input>80)
            grade="B";
        else if(input<=100 && input>90)
            grade="A";
        return grade;
    }
    public static void main(String[] args) throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebDriverWait wait=new WebDriverWait(driver,60);

        driver.get("https://www.google.com");

        driver.findElement(By.name("q")).sendKeys("gtmetrix");
        Thread.sleep(2000);
        driver.findElement(By.name("btnK")).click();

        driver.findElement(By.xpath("//h3[text()='GTmetrix | Website Performance Testing and Monitoring']")).click();

        driver.findElement(By.name("url")).sendKeys("www.gmail.com");
        driver.findElement(By.xpath("//button[text()='Test your site']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[text()='GTmetrix Grade ']")));

        String attribute=driver.findElement(By.xpath("//div[contains(@class,'report-score-grade-gtmetrix')]/i")).getAttribute("class");
        String grade=attribute.substring(13);
        String PerformanceGrade=driver.findElement(By.xpath("//h4[contains(text(),'Performance')]//..//span[@class='report-score-percent']")).getText();
        String StructureGrade=driver.findElement(By.xpath("//h4[contains(text(),'Structure')]//..//span[@class='report-score-percent']")).getText();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");

        System.out.println("Performance Report");
        System.out.println("www.gmail.com");
        System.out.println("Over-All Grade- "+grade+" & Grade lies Between "+FindPercentage(grade));
        System.out.println("Performance Percentage- "+PerformanceGrade+" & Performance Grade- "+FindGrade(PerformanceGrade.substring(0,PerformanceGrade.length()-1)));
        System.out.println("Structure Percentage- "+StructureGrade+" & Structure Grade- "+FindGrade(StructureGrade.substring(0,StructureGrade.length()-1)));
    }
}
