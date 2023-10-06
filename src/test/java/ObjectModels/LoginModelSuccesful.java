package ObjectModels;

public class LoginModelSuccesful {


    private AccountModel account;
    private String loginMessage ;



    public AccountModel getAccount() {
        return account;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "account={username:" + account.getUsername() + ",password:" + account.getPassword() +
                ", loginMessage='" + loginMessage + '\'' +
                '}';
    }
}
