package org.cafetownsend.atf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample {
    public static void main(String[] args) {
//        System.setProperty("","D:\\Projects\\cafetownsend-atf\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("http://cafetownsend-angular-rails.herokuapp.com/");
        driver.findElement(By.xpath("//input[@ng-model='user.name']")).sendKeys("Luke");
        driver.findElement(By.xpath("//input[@ng-model='user.password']")).sendKeys("Skywalker");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

    }
}
