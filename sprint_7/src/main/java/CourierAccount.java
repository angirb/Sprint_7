public class CourierAccount {
    private String login;
    private String password;

    public CourierAccount(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public static CourierAccount from(Courier courier) {
        return new CourierAccount(courier.getLogin(), courier.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "CourierCredentials{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
