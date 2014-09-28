import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description """A new user account can be created 
              if a proper unused username 
              and a proper password are given"""

scenario "creation succesfull with correct username and password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }
 
    when 'a valid username and password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("Jorma");
        element = driver.findElement(By.name("password"));
        element.sendKeys("12345678");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("12345678");
        element = driver.findElement(By.name("add"));
        element.submit();
    }

    then 'new user is registered to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe true
    }
}

scenario "can login with succesfully generated account", {
    given 'command new user is selected', {

    }
 
    when 'a valid username and password are entered', {

    }

    then  'new credentials allow logging in to system', {

    }
}

scenario "creation fails with correct username and too short password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }
    when 'a valid username and too short password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("Jormala");
        element = driver.findElement(By.name("password"));
        element.sendKeys("1234");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("1234");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("length greater").shouldBe true
    }
}

scenario "creation fails with correct username and pasword consisting of letters", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }
    when 'a valid username and password consisting of letters are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("PeterJ");
        element = driver.findElement(By.name("password"));
        element.sendKeys("abcdefgh");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("abcdefgh");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("character").shouldBe true
    }
}

scenario "creation fails with too short username and valid pasword", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }
    when 'a too sort username and valid password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("Jo");
        element = driver.findElement(By.name("password"));
        element.sendKeys("12345678");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("12345678");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("length 5-10").shouldBe true
    }
}

scenario "creation fails with already taken username and valid pasword", {
    given 'command new user is selected'
    when 'a already taken username and valid password are entered'
    then 'new user is not be registered to system'
}

scenario "can not login with account that is not succesfully created", {
    given 'command new user is selected'
    when 'a invalid username/password are entered'
    then  'new credentials do not allow logging in to system'
}