package heroku;

import base.BaseTest;
import heroku.pages.DragDropPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragDropTest extends BaseTest {

    @Test
    void verifyMoveAToBSuccessfully() {
        DragDropPage dragDropPage = new DragDropPage();
        dragDropPage.open();

        Assert.assertEquals(dragDropPage.getColumAContent(), "A");
        Assert.assertEquals(dragDropPage.getColumBContent(), "B");

        dragDropPage.dragAndDrop();

        Assert.assertEquals(dragDropPage.getColumAContent(), "B");
        Assert.assertEquals(dragDropPage.getColumBContent(), "A");
    }
}
