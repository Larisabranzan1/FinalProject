package Tests;

import ObjectModels.LoginModel;
import PageObjects.LoginPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;



public class LoginDataSource extends LoginTest{

    String browser = "chrome";
    LoginPage loginPage;

    @DataProvider(name = "jsonDp")
    public Iterator<Object[]> jsonDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
//      here we start json deserialization of json into LoginModel obj
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src\\test\\resources\\Data\\testdata.json");

        LoginModel[] lms = objectMapper.readValue(file, LoginModel[].class);

        for (LoginModel lm : lms)
            dp.add(new Object[]{lm});

        return dp.iterator();
    }

    @Test(dataProvider = "jsonDp")
    public void loginWithJsonAsDataSource(LoginModel lm) {
        loginLm(lm);
    }


    private void loginLm(LoginModel lm) {
        System.out.println(lm);
        login(lm.getAccount().getUsername(), lm.getAccount().getPassword(), lm.getUserError(), lm.getPasswordError());
    }

    private void login(String username, String password, String usernameErr, String passErr) {
        System.out.println("Login with username:" + username + "/password:" + password + "=> on browser:" + browser);
        driver.get(baseUrl);

        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login(username, password);

        System.out.println("Login Finished, verify error message");
        Assert.assertEquals(loginPage.geUsernameErr(), usernameErr);
        Assert.assertEquals(loginPage.getPassErr(), passErr);
    }


}
