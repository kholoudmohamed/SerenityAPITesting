package junit;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@RunWith(SerenityRunner.class)
public class SampleSerenityTest {
    @BeforeClass
    public static void init(){
        RestAssured.baseURI="http://localhost:8080/student";
    }
    @Test
    public void getAllStudents(){
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .statusCode(200);

    }

    @Test
    public void thisIsAFailingTest(){
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .statusCode(500);

    }
    @Pending
    @Test
    public void thisIsAPendingTest(){

    }
    @Ignore
    @Test
    public void thisIsAnIgnoredTest(){

    }

    @Test
    public void thisIsATestWithError(){
        System.out.println("This is an error "+(5/0));

    }
    @Test
    public void thisIsAComprimisedTest() throws FileNotFoundException {
        File file = new File("user/file.txt");
        FileReader fr = new FileReader(file);

    }
    @Manual
    @Test
    public void thisIsAManualTest(){

    }
}
