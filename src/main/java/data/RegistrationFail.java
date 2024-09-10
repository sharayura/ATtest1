package data;

/**
 * Дата-класс, используемый для проверки регистрации с ошибкой
 * @author Sharapov Yuri
 */
public class RegistrationFail {
    private String email;

    public RegistrationFail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
