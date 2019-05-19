package org.cafetownsend.atf.ui.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cafetownsend.atf.formatter.RunContext;
import org.cafetownsend.atf.formatter.TestLogHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testmonkeys.maui.pageobjects.elements.AbstractComponent;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.cafetownsend.atf.formatter.TestLogHelper.getCurrentLogName;

@Slf4j
@AllArgsConstructor
public class ScreenshotUtils {

    private String IMAGE_FILE_EXTENSION = ".png";
    private String IMAGE_FORMAT = "PNG";
    private String LOG_BASE_DIR = "target/logs/";

    private WebDriver driver;

    public float getDprScreen(WebDriver driver) {
        Number dprObject = (Number) ((JavascriptExecutor) driver).executeScript("return window.devicePixelRatio");
        return dprObject.floatValue();
    }

    public byte[] makeScreenshot() {

        final Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies
                        .viewportRetina(100, 0, 0, getDprScreen(this.driver)))
                .takeScreenshot(driver);

        final BufferedImage image = screenshot.getImage();
        ByteArrayOutputStream array = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, IMAGE_FORMAT, array);
            array.flush();
            return array.toByteArray();

        } catch (IOException exception) {
            log.error("Unable to Get Screenshot");
            exception.printStackTrace();
            return null;
        }
    }

    public File saveScreenshot(byte[] data) {
        new File(LOG_BASE_DIR + getCurrentLogName() + "/screen").mkdir();

        String screenshotName = LOG_BASE_DIR + getCurrentLogName() + "/screen/" + TestLogHelper.createTimeStamp() + "_" +
                RunContext.getCurrentStepLine() + IMAGE_FILE_EXTENSION;

        try {
            File file = new File(screenshotName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            return file;

        } catch (IOException exception) {
            log.error("Unable to save screenshot");
            exception.printStackTrace();
            return null;
        }
    }

    public void highLightElement(AbstractComponent component) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].style.border='3px solid red'", new WebElement[]{component.find()});
    }

    public void unhighLightElement(AbstractComponent component) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].style.removeProperty('border')", new WebElement[]{component.find()});
    }
}