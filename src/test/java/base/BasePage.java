package base;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {

    private final WebDriver driver;
    private final WebDriverWait w;
    private final JavascriptExecutor js;
    private static final int pageLoadTimeout = 10;
    private static final int elementFindTimeout = 10;
    private static final int elementFindShortTimeout = 3;
    private static final int delayDefaultTime = 2;
    private static final String elementNotFoundMessage = "No se encontro el elemento %s en %s segundos";
    public Logger log = LoggerFactory.getLogger(BasePage.class);

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.w = new WebDriverWait(this.driver, Duration.ofSeconds(elementFindTimeout));
        this.js = (JavascriptExecutor) this.driver;
    }

    protected void print(String message) {
        log.info(message);
    }

    protected JavascriptExecutor getJsExecutor() {
        return this.js;
    }

    protected void takeScreenshot() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String datetime = sdf.format(timestamp);
        String fileName = "screenshot_" + datetime + ".jpg";
        String fileDir = "src/test/resources/screenshots/";
        String filePath = fileDir + fileName;
        try {
            TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
            File src = screenshotTaker.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, Paths.get(filePath).toFile());
            log.info("Screenshot tomada: " + fileName);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar el archivo en" + filePath);
        }
    }

    // BROWSER

    protected void navigateTo(String url) {
        checkIfBroken(url);
        if (getUrl().equals("data:,")) {
            driver.get(url);
        } else {
            driver.navigate().to(url);
        }
        waitUntilPageIsReady();
    }

    protected void checkIfBroken(String url) {
        int statusCode = this.getLinkStatusCode(url);
        if (statusCode >= 400) {
            throw new RuntimeException("Error " + statusCode + ": No se encontro la pagina " + url);
        }
    }

    protected boolean isLinkBroken(String url) {
        return this.getLinkStatusCode(url) >= 400;
    }

    private int getLinkStatusCode(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            return conn.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    protected String getUrl() {
        return driver.getCurrentUrl();
    }

    protected String getBrowserTitle() {
        return driver.getTitle();
    }

    protected void goBack() {
        driver.navigate().back();
    }

    protected void refresh() {
        driver.navigate().refresh();
        this.delay(4);
    }

    protected int getBrowserTabsCount() {
        List<String> tabCount = new ArrayList<>(driver.getWindowHandles());
        return tabCount.size();
    }

    // TABS

    protected void switchToWindow(String windowTitle) {
        boolean switched = false;
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            log.info("Window title: " + this.getBrowserTitle());
            if (this.getBrowserTitle().contains(windowTitle)) {
                switched = true;
                break;
            }
        }

        if (!switched) throw new RuntimeException(String.format("No hay una ventana con el nombre '%s'", windowTitle));
    }

    // IFRAMES

    protected void switchToFrame(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    // ALERTS

    protected String getTextFromAlert() {
        return driver.switchTo().alert().getText();
    }

    protected void acceptAlert() {
        this.delay(1);
        driver.switchTo().alert().accept();
    }

    protected void dismissAlert() {
        this.delay(1);
        driver.switchTo().alert().dismiss();
    }

    // DELAY

    protected void delay() {
        this.delay(delayDefaultTime);
    }

    protected void delay(int segundos) {
        try {
            Thread.sleep(1000L * segundos);
        } catch (InterruptedException e) {
            e.getLocalizedMessage();
        }
    }

    // ACTIONS

    protected void clearData(By locator) {
        this.clearData(this.findByVisibility(locator));
    }

    protected void clearData(WebElement element) {
        String s = Keys.chord(Keys.CONTROL, "a");
        element.sendKeys(s, Keys.BACK_SPACE);
    }

    protected void inputData(By locator, String data) {
        this.inputData(this.findByVisibility(locator), data);
    }

    protected void inputData(WebElement element, String data) {
        this.clearData(element);
        element.sendKeys(data);
    }

    protected void inputDataAndEnter(By locator, String data) {
        WebElement element = this.findByVisibility(locator);
        this.inputData(element, data);
        element.sendKeys(Keys.ENTER);
    }

    protected void inputDataAndTab(By locator, String data) {
        WebElement element = this.findByVisibility(locator);
        this.inputData(element, data);
        element.sendKeys(Keys.TAB);
    }

    protected void inputDataAndSelectFirstOptionFromTypeahead(By locator, String data, By typeahead) {
        this.inputDataAndSelectOptionFromTypeahead(locator, data, typeahead, 0);
    }

    protected void inputDataAndSelectOptionFromTypeahead(By locator, String data, By typeahead, int index) {
        WebElement element = this.findByVisibility(locator);
        // Clear current data from textfield
        this.clearData(element);
        // Click on textfield
        this.js.executeScript("arguments[0].click();", element);
        // Write into textfield
        element.sendKeys(data);
        this.waitUntilVisible(typeahead);
        // Go to first option
        element.sendKeys(Keys.DOWN);
        // Go to specified option by index
        int timesDown = 0;
        while (timesDown != index) {
            element.sendKeys(Keys.DOWN);
            timesDown++;
        }
        // Select option
        element.sendKeys(Keys.ENTER);
    }

    protected void inputDataAndSelectOptionFromTypeahead(By locator, String data, By typeaheadOptions, String contains) {
        WebElement element = this.findByVisibility(locator);
        // Clear current data from textfield
        this.clearData(element);
        // Click on textfield
        this.js.executeScript("arguments[0].click();", element);
        // Write into textfield
        element.sendKeys(data);
        this.waitUntilVisible(typeaheadOptions);
        List<WebElement> list = this.findElements(typeaheadOptions);
        Actions act = new Actions(driver);
        for (WebElement listItem : list) {
            if (listItem.getText().contains(contains)) {
                act.moveToElement(listItem).click().build().perform();
                break;
            }
        }
    }

    protected void selectFromComboboxByText(By locator, String text) {
        Select select = new Select(this.findByVisibility(locator));
        select.selectByVisibleText(text);
    }

    protected void clickElement(By locator) {
        this.clickElement(this.findByClickable(locator));
    }

    protected void clickElement(WebElement element) {
        this.clickElement(element, 0);
    }

    private void clickElement(WebElement element, int attempt) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            this.clickWithJavascript(element);
        } catch (Exception e) {
            if (attempt < 2) {
                this.clickElement(element, attempt + 1);
            } else {
                throw e;
            }
        }
    }

    protected void clickWithJavascript(By locator) {
        this.clickWithJavascript(this.findByPresence(locator));
    }

    protected void clickWithJavascript(WebElement element) {
        this.js.executeScript("arguments[0].click();", element);
    }

    protected void hoverOver(By locator) {
        this.hoverOver(this.findByVisibility(locator));
    }

    protected void hoverOver(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    protected String getAttribute(By locator, String attribute) {
        return this.findByPresence(locator).getAttribute(attribute);
    }

    protected String getTextFromElement(By locator) {
        return this.findByVisibility(locator).getText();
    }

    protected String getTextWithJavascript(By locator) {
        List<String> list = this.getTextListWithJavascript(locator);
        if (list.isEmpty()) throw new NoSuchElementException("No se encontro el elemento con " + locator);
        return list.get(0);
    }

    protected List<String> getTextListWithJavascript(By locator) {
        this.findByPresence(locator);
        String byType = null;
        if (locator.toString().startsWith("By.xpath: ")) byType = "xpath";
        else if (locator.toString().startsWith("By.cssSelector: ")) byType = "cssSelector";
        if (byType == null) throw new RuntimeException("Tipo de locator By desconocido: " + locator);
        String xpath = locator.toString().replaceFirst("By." + byType + ": ", "");

        String jsScript = "return getTextList(arguments[0], arguments[1]); " + getTextListScript();
        @SuppressWarnings("unchecked")
        List<String> arrTexts = (List<String>) this.js.executeScript(jsScript, byType, xpath);

        return arrTexts.stream().map(String::trim).collect(Collectors.toList());
    }

    protected String getSelectedTextFromCombobox(By locator) {
        return this.getSelectedTextFromCombobox(this.findByVisibility(locator));
    }

    protected String getSelectedTextFromCombobox(WebElement element) {
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    // IS

    protected Boolean isPageObjectVisible(By locator) {
        return this.isPageObjectVisible(locator, elementFindShortTimeout);
    }

    protected Boolean isPageObjectVisible(By locator, int timeout) {
        try {
            this.findByVisibility(locator, timeout);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    protected Boolean isPageObjectPresent(By locator) {
        return this.isPageObjectPresent(locator, elementFindShortTimeout);
    }

    protected Boolean isPageObjectPresent(By locator, int timeout) {
        try {
            this.findByPresence(locator, timeout);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // FINDS

    protected WebElement findElement(By locator) {
        return this.findByVisibility(locator);
    }

    protected WebElement findElement(By locator, By inside) {
        WebElement parentElement = this.findByPresence(inside);
        return parentElement.findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        try {
            this.findByPresence(locator);
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return driver.findElements(locator);
    }

    protected List<WebElement> findElements(By locator, By inside) {
        try {
            WebElement parentElement = this.findByPresence(inside);
            return parentElement.findElements(locator);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    protected WebElement findByVisibility(By locator) {
        return findByVisibility(locator, elementFindTimeout);
    }

    protected WebElement findByVisibility(By locator, int timeout) {
        Duration timeoutDuration = Duration.ofSeconds(timeout);
        String msg = String.format(elementNotFoundMessage, locator.toString(), timeout);
        this.w.withTimeout(timeoutDuration).withMessage(msg).ignoring(StaleElementReferenceException.class);
        return this.w.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement findByPresence(By locator) {
        return this.findByPresence(locator, elementFindTimeout);
    }

    protected WebElement findByPresence(By locator, int timeout) {
        Duration timeoutDuration = Duration.ofSeconds(timeout);
        String msg = String.format(elementNotFoundMessage, locator.toString(), timeout);
        this.w.withTimeout(timeoutDuration).withMessage(msg).ignoring(StaleElementReferenceException.class);
        return this.w.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement findByClickable(By locator) {
        return this.findByClickable(locator, elementFindTimeout);
    }

    protected WebElement findByClickable(By locator, int timeout) {
        Duration timeoutDuration = Duration.ofSeconds(timeout);
        String msg = String.format(elementNotFoundMessage, locator.toString(), timeout);
        this.w.withTimeout(timeoutDuration).withMessage(msg).ignoring(StaleElementReferenceException.class);
        return this.w.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
    }

    // WAITS

    protected void waitUntilInvisible(By locator) {
        this.waitUntilInvisible(locator, elementFindTimeout);
    }

    protected void waitUntilInvisible(By locator, int timeout) {
        Duration timeoutDuration = Duration.ofSeconds(timeout);
        String msg = String.format(elementNotFoundMessage, locator.toString(), timeout);
        this.w.withTimeout(timeoutDuration)
            .withMessage(msg)
            .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitUntilVisible(By locator) {
        this.waitUntilVisible(locator, elementFindTimeout);
    }

    protected void waitUntilVisible(By locator, int timeout) {
        this.findByVisibility(locator, timeout);
    }

    protected void waitUntilPresent(By locator) {
        this.waitUntilPresent(locator, elementFindTimeout);
    }

    protected void waitUntilPresent(By locator, int timeout) {
        this.findByPresence(locator, timeout);
    }

    protected void waitUntilClickable(By locator) {
        this.waitUntilClickable(locator, elementFindTimeout);
    }

    protected void waitUntilClickable(By locator, int timeout) {
        this.findByClickable(locator, timeout);
    }

    protected void waitUntilPageIsReady() {
        Duration timeoutDuration = Duration.ofSeconds(pageLoadTimeout);
        ExpectedCondition<Boolean> jsLoad = d -> (Boolean) this.js.executeScript("return document.readyState === 'complete'");
        this.w.withTimeout(timeoutDuration).until(jsLoad);
    }

    protected void waitForAlert() {
        Duration timeoutDuration = Duration.ofSeconds(pageLoadTimeout);
        w.withTimeout(timeoutDuration).until(ExpectedConditions.alertIsPresent());
    }

    // SCROLL

    public void scrollToElement(By locator) {
        this.scrollToElement(this.findByVisibility(locator));
    }

    public void scrollToElement(WebElement element) {
        this.js.executeScript(
            "window.scrollTo(arguments[0], arguments[1])",
            element.getLocation().x,
            element.getLocation().y
        );
    }

    public void scrollToTopOfPage() {
        this.js.executeScript("window.scrollTo(0, 0)");
    }

    public void scrollToBottomOfPage() {
        this.js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    // Otras funciones que pueden servir

    private void controlClickElement(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = this.findElement(locator);
        actions.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).build().perform();
    }

    protected void inputDataAndSelectFirstOptionFromBrowserTypeahead(By locator, String data) {
        int delayTime = 1;
        WebElement element = this.findByVisibility(locator);
        this.clearData(element);
        this.delay(delayTime);
        this.js.executeScript("arguments[0].click();", element);
        element.sendKeys(data);
        this.delay(delayTime);
        element.sendKeys(Keys.DOWN);
        element.sendKeys(Keys.ENTER);
    }

    protected void clickCheckBox(By locator, boolean checked) {
        this.clickCheckBox(this.findByPresence(locator), checked);
    }

    protected void clickCheckBox(WebElement checkbox, boolean checked) {
        boolean isSelected = checkbox.isSelected();
        boolean clickable = isSelected && !checked || !isSelected && checked;
        if (clickable) checkbox.click();
    }

    private void selectRandomFromCombobox(By locator) {
        this.selectRandomFromCombobox(this.findByVisibility(locator));
    }

    private void selectRandomFromCombobox(WebElement element) {
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        int count = options.size();
        Random r = new Random();
        int i = r.nextInt(count);
        select.selectByIndex(i);
    }

    private void clickRandomElementFromList(List<WebElement> list) {
        int count = list.size();
        Random r = new Random();
        int index = r.nextInt(count);
        this.clickElementFromListByIndex(list, index);
    }

    private void clickElementFromListByIndex(List<WebElement> list, int index) {
        list.get(index).click();
    }

    private String getNormalizedText(By locator) {
        return this.findElements(locator)
            .stream()
            .map(WebElement::getText)
            .map(this::normalize)
            .collect(Collectors.joining(" "));
    }

    /**
     * Normalizes a string using the same algorithm as a browser.
     * @param s the string to normalize
     * @return a normalized string
     */
    private String normalize(String s) {
        // return null == s ? null : Arrays.stream(s.split("\\s")).collect(Collectors.joining(" "));
        return null == s ? null : String.join(" ", s.split("\\s"));
    }

    /**
     * if element is becoming stale due to quick DOM change
     * Method will try to find element and click it for 5 times
     * @param locator desc
     * @return boolean
     */
    private boolean retryingFindClickIfStale(By locator) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 5) {
            try {
                this.clickElement(locator);
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
                e.getLocalizedMessage();
            }
            attempts++;
        }
        return result;
    }

    // Funciones totalmente privadas solo para esta clase

    private String getTextListScript() {
        String filePath = "src/main/resources/getTextList.js";
        try {
            return Arrays.toString(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer el archivo " + filePath);
        }
    }
}
