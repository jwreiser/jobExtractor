package com.goodforallcode.jobExtractor.driver;

import com.goodforallcode.jobExtractor.util.WebdriverUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;

public class Scroller {
    public void scrollResultsIntoView(String url, WebDriver driver) {
        WebdriverUtil webdriverUtil=new WebdriverUtil(driver);
        WebElement ulElement = webdriverUtil.getUiContainingLiPerJob();
        ulElement.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].style = ''; arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible';",
                ulElement);

  /*
    we are hardcoding this to deal with the weirdness of the number of results changing over time.
    I don't want to miss loading some jobs, especially since we scroll quick. I would rather waste a few seconds
    */
        int scrolls =5;
        if(ulElement!=null) {
            //we were not loading all of the elements fully but scrolling them into view fixes it
            for (int i = 1; i < scrolls * 25; i++) {
                try {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop=" + i * 75,ulElement);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollBy(0," + i * 75+")",ulElement);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(arguments[0])",ulElement);

                } catch (Exception ex) {//may get runtimes we can't catch specifically
                    try {
                        Thread.sleep(5_0000);
                    } catch (InterruptedException e) {
                        System.err.println(e.getLocalizedMessage());
                    }
                }
            }
        }
        int lastElement=0;
        List<WebElement> liElements=ulElement.findElements(By.tagName("li")).stream().filter(li->li.getAttribute("class").contains("scaffold-layout__list-item")).collect(Collectors.toList());
        WebElement lastLi=null;
        while(lastElement<liElements.size()){
            lastLi=liElements.getLast();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(arguments[1])",ulElement,lastLi);
            lastElement=liElements.size();
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
        Actions actions = new Actions(driver);
        actions = actions.clickAndHold(ulElement);
        actions = actions.keyDown(ulElement,Keys.PAGE_DOWN);
        actions = actions.keyUp(ulElement,Keys.PAGE_DOWN);
         Action chain=actions.release(ulElement).build();
        chain.perform();

        int lastSize=0;
        while(lastSize<liElements.size()) {
            for (int i = 0; i < liElements.size(); i++) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", liElements.get(i));
                actions.moveToElement(liElements.get(i)).perform();
            }

            lastSize = liElements.size();
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                System.err.println(e.getLocalizedMessage());
            }
            liElements = ulElement.findElements(By.tagName("li")).stream().filter(li -> li.getAttribute("class").contains("scaffold-layout__list-item")).collect(Collectors.toList());

        }
//         ulElement.sendKeys(Keys.PAGE_DOWN);
    }
}
