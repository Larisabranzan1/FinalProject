package ObjectModels;

public class LoginModelRegister {

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel account) {
        this.account = account;
    }

    public String getPassErr() {
        return passErr;
    }

    public void setPassErr(String passErr) {
        this.passErr = passErr;
    }

    public String getUsernameErr() {
        return usernameErr;
    }

    public void setUsernameErr(String usernameErr) {
        this.usernameErr = usernameErr;
    }



    private AccountModel account;
    private String passErr;
    private String usernameErr;

    public LoginModelRegister(String username, String password, String usernameErr, String passErr) {
        AccountModel accountM = new AccountModel();
        accountM.setPassword(password);
        accountM.setUsername(username);

        this.account = accountM;
        this.usernameErr = usernameErr;
        this.passErr = passErr;
    }

    @Override
    public String toString() {
        return "LoginModelRegister{" +
                "account={username:" + account.getUsername() + ",password:" + account.getPassword() +
                ", passErr='" + passErr + '\'' +
                ", usernameErr='" + usernameErr + '\'' +
                '}';
    }


}
