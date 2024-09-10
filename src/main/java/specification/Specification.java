package specification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * Класс спецификаций
 * @author Sharapov Yuri
 */
public class Specification {

    /**
     * Метод настройки вида запроса
     * @return спецификация ответа
     */
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/")
                .setContentType("application/json")
                .build();
    }

    /**
     * Метод настройки вида ответа
     * @param code ожидаемый статус-код
     * @return спецификация ответа
     */
    public static ResponseSpecification responseSpec(int code) {
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .build();
    }

    /**
     * Инициализация спецификации (перегруженный метод)
     * @param requestSpec спецификация запроса
     */

    public static void installSpec(RequestSpecification requestSpec) {
        RestAssured.requestSpecification = requestSpec;
    }

    /**
     * Инициализация спецификации (перегруженный метод)
     * @param requestSpec спецификация запроса
     * @param responseSpec спецификация ответа
     */
    public static void installSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    /**
     * Удаление спецификаций
     */
    public static void deleteSpec() {
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }

}
