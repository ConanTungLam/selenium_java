package heroku;

import base.BaseTest;
import heroku.pages.TablePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class TableTest extends BaseTest {
    TablePage tablePage;
    @BeforeMethod
    void getTables(){
        tablePage = new TablePage();
        tablePage.open();
    }


    @Test
    void verifyLargestDuePerson() {
        double maxDue = tablePage
                .table1
                .stream()
                .max(Comparator.comparing(Person::getDue))
                .get()
                .getDue();

        List<String> maxDuePerson = tablePage
                .table1
                .stream()
                .filter(person -> person.getDue() == maxDue).map(Person::getFullName)
                .toList();

        Assert.assertEquals(maxDuePerson, List.of("Jason Doe"));
    }

    @Test
    void verifyMinDuePerson() {
        double minDue = tablePage
                .table1
                .stream()
                .min(Comparator.comparing(Person::getDue))
                .get()
                .getDue();
        List<String> minDuePerson = tablePage
                .table1
                .stream()
                .filter(person -> person.getDue() == minDue).map(Person::getFullName)
                .toList();

        Assert.assertEquals(minDuePerson, List.of("John Smith","Tim Conway"));
    }

}