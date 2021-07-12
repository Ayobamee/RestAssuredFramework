package TestCases;

import Route.JobPath;
import TokenManager.LoginToken;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

// This test is to ensure that jobs can be listed.
public class getJobs {


	public Properties prop = new Properties();
	@Test

	    public void main() throws IOException {


		//Load Base URL
		LoginToken load = new LoginToken();
		load.getBaseUrl();


		//Set the extracted token.
		String authToken = load.adminToken();


		Response res1 =	given()
				//pass the auth token
				.header("Authorization",authToken).
				contentType("application/json").

				//Insert get pricing payload
						when().


				//Input the get jobs path
						get(JobPath.jobs()).


				//Run the assertion
						then()
				        .assertThat()
				        .statusCode(200)
				        .and()
				        .contentType(ContentType.JSON)

						 .extract().response();


		//Display the response body
		String pricingResponse = res1.asString();
		System.out.println(pricingResponse);


	}

}
