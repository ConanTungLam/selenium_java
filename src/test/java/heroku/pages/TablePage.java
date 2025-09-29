package heroku.pages;

import heroku.Person;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static common.Browser.findAll;
import static common.Browser.visit;

public class TablePage {
    public List<Person> table1 = new ArrayList<>();

    public void open(){
        visit("https://the-internet.herokuapp.com/tables");
        findAll(By.xpath("//table[@id='table1']/tbody/tr"))
                .forEach(row -> {
                    String firstName = row.findElement(By.xpath("./td[2]")).getText();
                    String lastName = row.findElement(By.xpath("./td[1]")).getText();
                    String due = row.findElement(By.xpath("./td[4]")).getText();
                    table1.add(new Person(firstName, lastName, due));
                });
    }
}
