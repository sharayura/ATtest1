package data;

/**
 * Дата-класс, используемый для проверки регистрации
 * @author Sharapov Yuri
 */
public class Registration extends RegistrationFail {
    public static final String EMAIL_VALID = "eve.holt@reqres.in";
    public static final String PASSWORD_VALID = "pistol";

    private String password;

    public Registration(String email, String password) {
        super(email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
