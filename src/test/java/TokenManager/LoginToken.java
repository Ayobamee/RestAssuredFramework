package TokenManager;
import Payloads.LoginPayload;
import Route.LoginPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;


public class LoginToken {

	// Open the environment properties file
	public Properties prop = new Properties();

	@BeforeTest
	public void getBaseUrl() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//Environment//enviro.properties");
		prop.load(fis);
		RestAssured.baseURI = prop.getProperty("BaseURL");

	}

	// This test is to ensure that a token can be generated for an admin.

	@Test
	public String adminToken() {
		// Open the baseURL


		Response res = given().
				//Input the header
						header("Content-Type", "application/json").

				//Input the Login Payload
						body(LoginPayload.LoginDetails()).
						when().
				//Input the resource
						get(LoginPath.Login()).

				//Run an assertion
						then().assertThat().statusCode(200).and().contentType(ContentType.JSON).

				//extract response of body
						extract().response();


		//Convert response to string
		String responseString = res.asString();


		//Extract bearer token
		JsonPath js = new JsonPath(responseString);
		String extractedToken = js.get("accessToken");
		System.out.println(extractedToken);
		return extractedToken;
	}



}
