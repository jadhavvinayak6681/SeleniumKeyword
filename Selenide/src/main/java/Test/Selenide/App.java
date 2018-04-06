package Test.Selenide;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        open("http://google.com");
       // $(By.name("q")).click().setValue("ABC");
    }
}
