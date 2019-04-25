package junit;

import com.studentapp.model.StudentClass;
import cucumber.serenity.steps.StudentSerenitySteps;
import io.appium.java_client.pagefactory.AndroidFindByChainSet;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import testbase.TestBase;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTests extends TestBase {
    static String firstName = "Kholoud"+TestUtils.getRandomString();
    static String lastName = "Mohsen";
    static String programme = "Computer Science";
    static String email = "email"+ TestUtils.getRandomString() +"@hotmail.com";
    static int studentId;

    @Steps
    StudentSerenitySteps steps;



    @Title("This test will create a new student")
    @Test
    public void T001_createStudent(){
        ArrayList<String> courses = new ArrayList<>();
        courses.add("JAVA");
        courses.add("C#");

        steps.createStudent(firstName,lastName,email,programme,courses)
                .statusCode(201);
    }

    @Title("verify if student was added to the application")
    @Test
    public void T002_getStudent(){
        HashMap<String,Object> value =steps.getStudentInfoByFirstName(firstName);

        assertThat(value,hasValue(firstName));
        studentId=(int)value.get("id");
    }

    @Title("update student information and verify the updates")
    @Test
    public void T003_updateStudent(){
        ArrayList<String> courses = new ArrayList<>();
        courses.add("JAVA");
        courses.add("C#");

        firstName = firstName+"_Updated";
        steps.updateStudent(studentId,firstName,lastName,email,programme,courses);

        HashMap<String,Object> value =steps.getStudentInfoByFirstName(firstName);
        assertThat(value,hasValue(firstName));
    }
    @Title("delete student and verify that it was deleted")
    @Test
    public void T004_deleteStudent(){
        steps.deleteStudent(studentId);

        steps.getStudentInfoById(studentId)
                .statusCode(404);
    }
}
