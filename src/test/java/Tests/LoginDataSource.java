package Tests;

import ObjectModels.LoginModel;
import ObjectModels.LoginModelRegister;
import ObjectModels.LoginModelSuccesful;
import PageObjects.AccountPage;
import PageObjects.LoginPage;
import PageObjects.RegistrationPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import Utils.Tools;


public class LoginDataSource extends LoginTest{

    String browser = "chrome";
    LoginPage loginPage;
    RegistrationPage registrationPage;

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
        if (username.isEmpty() && password.isEmpty()) {
            Assert.assertTrue(loginPage.geUsernameErr().contains(usernameErr));
        }
        if (!usernameErr.isEmpty())
            Assert.assertTrue(loginPage.geUsernameErr().contains(usernameErr));
        if (!passErr.isEmpty())
            Assert.assertTrue(loginPage.getPassErr().contains(passErr));

    }

    //#####################################PositiveLoginJson#######################

    @DataProvider(name = "positiveJsonLogin")
    public Iterator<Object[]> positiveJsonLogin() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
//      here we start json deserialization of json into LoginModel obj
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src\\test\\resources\\Data\\testdatasuccessful.json");

        LoginModelSuccesful[] lms = objectMapper.readValue(file, LoginModelSuccesful[].class);

        for (LoginModelSuccesful lm : lms)
                dp.add(new Object[]{lm});

        return dp.iterator();
    }

    @Test(dataProvider = "positiveJsonLogin")
    public void positiveJsonLogin(LoginModelSuccesful lm) {
        positiveLogin(lm);
    }


    private void positiveLogin(LoginModelSuccesful lm) {
        System.out.println(lm);
        positiveLogin(lm.getAccount().getUsername(), lm.getAccount().getPassword(), lm.getLoginMessage());
    }

    private void positiveLogin(String username, String password, String loginMessage) {
        System.out.println("Login with username:" + username + "/password:" + password + "/loginMessage" + loginMessage);
        driver.get(baseUrl);

        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login(username, password);
        accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.getNamePersAccount().contains(username));
        System.out.println("Logout user");

    }


    //#####################################MYSQL#######################
    @DataProvider(name = "mysql")
    public Iterator<Object[]> mysqlDpCollection() throws Exception {
//        show DB connection details
        System.out.println("Use dbHostname:"+dbHostname);
        System.out.println("Use dbUser:"+dbUser);
        System.out.println("Use dbPort:"+dbPort);
        System.out.println("Use dbSchema:"+dbSchema);
        Collection<Object[]> dp = new ArrayList<>();
//        db connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+dbHostname+":"+dbPort+
                "/"+dbSchema, dbUser,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM login_negative");
        while (resultSet.next()){
            LoginModel lm = new LoginModel(getEscapedElement(resultSet,"username"),
                    getEscapedElement(resultSet,"password"),
                    getEscapedElement(resultSet,"usernameErr"),
                    getEscapedElement(resultSet,"passwordErr"));
            dp.add(new Object[]{lm});
        }
        return dp.iterator();
   }


    @Test(dataProvider = "mysql")
    public void loginWithSQLAsDataSource(LoginModel lm) {
        loginLm(lm);
    }
    //   login with loginModel


    //#####################################MYSQLNegativeRegistration#######################
    @DataProvider(name = "mysqlWithNegative")
    public Iterator<Object[]> mysqlWithNegative() throws Exception {
//        show DB connection details
        System.out.println("Use dbHostname:"+dbHostname);
        System.out.println("Use dbUser:"+dbUser);
        System.out.println("Use dbPort:"+dbPort);
        System.out.println("Use dbSchema:"+dbSchema);
        Collection<Object[]> dp = new ArrayList<>();
//        db connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+dbHostname+":"+dbPort+
                "/"+dbSchema, dbUser,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM register_negative");
        while (resultSet.next()){
            LoginModelRegister lm = new LoginModelRegister(getEscapedElement(resultSet,"username"),
                    getEscapedElement(resultSet,"password"),
                    getEscapedElement(resultSet,"usernameErr"),
                    getEscapedElement(resultSet,"passErr"));
            dp.add(new Object[]{lm});
        }
        return dp.iterator();
    }


    @Test(dataProvider = "mysqlWithNegative")
    public void mysqlWithNegative(LoginModelRegister lm) {
        registrationLm(lm);
    }
    //   login with loginModel

    private void registrationLm(LoginModelRegister lm) {
        System.out.println(lm);
        registerNegativeUserPassConfirm(lm.getAccount().getUsername(), lm.getAccount().getPassword(), lm.getPassErr(), lm.getUsernameErr());
    }

    public void registerNegativeUserPassConfirm(
                                                String username,
                                                String password,
                                                String usernameErr,
                                                String passErr
    ) {
        System.out.println("Login with username:" + username +
                " /password:" + password +
                " /confirmationPassword:" +
                " => on browser:" );
        //setUpDriver(browser);
        //driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        System.out.println("Opened login page.");

        loginPage.goToRegistrationPage();
        System.out.println("Opened registration page.");

        registrationPage = new RegistrationPage(driver);
        registrationPage.register(username, password);
        System.out.println("Registration  finished, verify error message");


        if (!usernameErr.isEmpty())
            Assert.assertTrue(registrationPage.getRegisterErr().contains(usernameErr));
        if (!passErr.isEmpty())
            Assert.assertTrue(registrationPage.getRegisterErr().contains(passErr));

    }

    //#####################################MYSQLNegativeRegistration#######################


    private String getEscapedElement(ResultSet resultSet, String element) throws SQLException {
        return Tools.replaceElements(resultSet.getString(element), "''", "");
    }


}
