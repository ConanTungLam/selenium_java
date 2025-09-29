package heroku;

import base.BaseTest;
import common.Checkbox;
import heroku.pages.CheckboxPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CheckboxTest extends BaseTest {
    List<Checkbox> checkboxList = new ArrayList<>();

    @BeforeMethod
    void open(){
        CheckboxPage checkboxPage = new CheckboxPage();
        checkboxPage.open();
        checkboxList =  checkboxPage.getCheckboxList();
    }

    @Test
    void verifySelectCheckboxesSuccessfully(){
        checkboxList.forEach(checkbox -> {
            checkbox.check();
            Assert.assertTrue(checkbox.isChecked());
        });
    }

    @Test
    void verifyUnselectCheckboxesSuccessfully(){
        checkboxList.forEach(checkbox -> {
            checkbox.uncheck();
            Assert.assertFalse(checkbox.isChecked());
        });
    }

}
