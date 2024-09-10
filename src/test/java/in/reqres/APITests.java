package in.reqres;

import data.Registration;
import data.RegistrationFail;
import data.User;
import data.UserUpdate;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static specification.Specification.*;

/**
 * @author Sharapov Yuri
 */
public class APITests {

    @AfterMethod
    public void afterEach() {
        deleteSpec();
    }

    @Test
    public void case1RegistrationSuccessTest() {
        installSpec(requestSpec(), responseSpec(200));

        Registration registration = new Registration(Registration.EMAIL_VALID, Registration.PASSWORD_VALID);
        given()
                .body(registration)
                .when()
                .post("/api/register")
                .then()
                .log().all()
                .body("token", notNullValue());
    }

    @Test
    public void case1RegistrationFailTest() {
        installSpec(requestSpec());

        RegistrationFail registration = new RegistrationFail(Registration.EMAIL_VALID);
        int statusCode = given()
                .body(registration)
                .when()
                .post("/api/register")
                .then()
                .log().all()
                .extract().statusCode();
        Assert.assertEquals(statusCode, 400, "Wrong status code " + statusCode);
    }

    @Test
    public void case2EmailEndsTest() {
        installSpec(requestSpec(), responseSpec(200));

        ExtractableResponse<Response> response = given()
                .when()
                .get("/api/users?page=2")
                .then()
                .log().all()
                .extract();

        List<User> userList = response.jsonPath()
                .getList("data", User.class);
        List<String> wrongEmails = userList.stream()
                .map(User::getEmail)
                .filter(s -> !s.endsWith("@reqres.in")).toList();

        Assert.assertTrue(wrongEmails.isEmpty(), "Wrong emails: " + wrongEmails);
    }

    @Test
    public void case3DeleteUserTest() {
        installSpec(requestSpec());
        int statusCode = given()
                .when()
                .delete("/api/users/2")
                .then()
                .log().all()
                .extract().statusCode();
        Assert.assertEquals(statusCode, 204, "Wrong status code (" + statusCode + ")");
    }


    @Test
    public void case4UpdateUserTest() {
        installSpec(requestSpec(), responseSpec(200));
        UserUpdate userUpdate = new UserUpdate(UserUpdate.NAME_UPDATE, UserUpdate.JOB_UPDATE);
        ExtractableResponse<Response> response = given()
                .body(userUpdate)
                .when()
                .patch("/api/users/2")
                .then()
                .log().all()
                .extract();

        String updateDate = response.jsonPath().getString("updatedAt").split("T")[0];
        String currentDate = java.time.LocalDate.now().toString();
        Assert.assertEquals(updateDate, currentDate, "Update date is not equal to current date");
    }
}
