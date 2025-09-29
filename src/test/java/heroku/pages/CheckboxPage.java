package heroku.pages;

import common.Browser;
import common.Checkbox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CheckboxPage{


    public List<Checkbox> getCheckboxList() {
        List<WebElement> checkboxList = Browser.findAll(By.xpath("//form[@id='checkboxes']/input"));
        List<Checkbox> checkboxes = new ArrayList<>();
        for (WebElement webElement : checkboxList) {
            checkboxes.add(new Checkbox(webElement));
        }
        return checkboxes;
    }

    public void open(){
        Browser.visit("https://the-internet.herokuapp.com/checkboxes");
    }
}
