package Test.Selenide;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.*;


public class firsttest {
	
	@Test
	public void usercansearch(){
	
		System.out.println( "Hello World!" );
        open("http://google.com");
        $(By.name("q")).setValue("johny").pressEnter();
        //$$(By)
	}

}
