package cucumber.serenity.steps;

import com.studentapp.model.StudentClass;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class StudentSerenitySteps {
    @Step("Create student with firstName:{0}, LastName:{1}, Email:{2}, Programme:{3}, Courses:{4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courses)
    {
        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setProgramme(programme);
        student.setEmail(email);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .when()
                .body(student)
                .post()
                .then();
    }

    @Step("Getting the student information with firstName: {0}")
    public HashMap<String,Object> getStudentInfoByFirstName(String firstName)
    {
        return SerenityRest.rest().given()
                        .when()
                        .get("/list")
                        .then()
                        .log()
                        .all()
                        .statusCode(200)
                        .extract().path("findAll{it.firstName=='"+firstName+"'}.get(0)");

    }
    @Step("Getting the student information with Id: {0}")
    public ValidatableResponse getStudentInfoById(int studentId)
    {
        return SerenityRest.rest()
                .given()
                .when()
                .get("/"+studentId).then();

    }

    @Step("update student info using ID:{0} with firstName:{1}, LastName:{2}, Email:{3}, Programme:{4}, Courses:{5}")
    public ValidatableResponse updateStudent(int studentId,String firstName, String lastName, String email, String programme, List<String> courses)
    {
        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setProgramme(programme);
        student.setEmail(email);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON).log().all()
                .when()
                .body(student)
                .put("/"+studentId)
                .then();
    }

    @Step("Delete student using ID:{0}")
    public void deleteStudent(int studentId)
    {
        SerenityRest.rest()
                .given()
                .when()
                .delete("/"+studentId);
    }
}
