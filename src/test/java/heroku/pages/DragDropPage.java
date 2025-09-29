package heroku.pages;

import common.Browser;
import org.openqa.selenium.By;

import static common.Browser.getText;
import static common.Browser.visit;

public class DragDropPage {
    public void open() {
        visit("https://the-internet.herokuapp.com/drag_and_drop");
    }

    public void dragAndDrop() {
        Browser.dragAndDrop(By.id("column-a"), By.id("column-b"));
    }

    public String getColumAContent() {
        return getText(By.id("column-a"));
    }

    public String getColumBContent() {
        return getText(By.id("column-b"));
    }
}