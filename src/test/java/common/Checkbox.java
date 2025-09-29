package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Checkbox {
    private WebElement checkbox;
    public Checkbox(WebElement element) {
        this.checkbox = element;
    }
    public void check(){
        if (!checkbox.isSelected()) checkbox.click();
    }
    public  void  uncheck(){
        if (checkbox.isSelected()) checkbox.click();
    }
    public boolean isChecked(){
        return checkbox.isSelected();
    }
}
