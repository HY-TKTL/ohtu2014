import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can log in with valid username/password-combination'

scenario "user can login with correct password", {
    given 'login selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("login"));       
        element.click();       
    }

    when 'a valid username and password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();
    }
 
    then 'user will be logged in to system', {
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe true
    }
}

scenario "user can not login with incorrect password", {
    given 'command login selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("login"));       
        element.click(); 
    }
    when 'a valid username and incorrect password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("juuh");
        element = driver.findElement(By.name("login"));
        element.submit()
    }
    then 'user will not be logged in to system', {
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe false
    }
}

scenario "nonexistent user can not login to system", {
    given 'command login selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("login"));       
        element.click(); 
    }
    when 'a valid username and incorrect password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("kekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("juuh");
        element = driver.findElement(By.name("login"));
        element.submit()
    }
    then 'user will not be logged in to system', {
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe false
    }
}

scenario "new user creation works", {
    given 'command new user', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click(); 
    }
    when 'valid user pw', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("nicememe");
        element = driver.findElement(By.name("password"));
        element.sendKeys("nicememe1");
        element = driver.findElement(By.name("confirm password"));
        element.sendKeys("nicememe1");
        element = driver.findElement(By.name("add"));
        element.submit()
    }
    then 'logged in', {
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe true
    }
}


scenario "new user creation doesnt work", {
    given 'command new user', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click(); 
    }
    when 'not matching user pw', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("nicememe");
        element = driver.findElement(By.name("password"));
        element.sendKeys("nicememe1");
        element = driver.findElement(By.name("confirm password"));
        element.sendKeys("nicememe2");
        element = driver.findElement(By.name("add"));
        element.submit()
    }
    then 'not logged in', {
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe false
    }
}