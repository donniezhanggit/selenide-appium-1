package org.selenide.examples.appium;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Copied from https://github.com/appium/sample-code/blob/master/sample-code/examples/java/junit/src/test/java/com/saucelabs/appium/AndroidDragAndDrop.java
 * and modified to use Selenide framework.
 */
public class AndroidDragAndDropTest extends AbstractApiDemosTest {
  @Test
  public void dragAndDrop() {
    $(By.xpath(".//*[@text='Views']")).click();
    $(By.xpath(".//*[@text='Drag and Drop']")).click();

    SelenideElement from = $(By.id("io.appium.android.apis:id/drag_dot_1")).shouldBe(visible);
    SelenideElement to = $(By.id("io.appium.android.apis:id/drag_dot_2")).shouldBe(visible);
    By dragText = By.id("io.appium.android.apis:id/drag_text");

    $(dragText).shouldHave(exactText(""));

    new TouchAction(driver())
        .longPress(ElementOption.element(from.toWebElement())) // TODO Proxy cast to RemoteWebElement
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
        .moveTo(ElementOption.element(to.toWebElement())) // TODO Proxy cast to RemoteWebElement
        .release()
        .perform();

    $(dragText)
        .shouldBe(visible)
        .shouldHave(text("Dot"), text("DraggableDot"));
  }
}