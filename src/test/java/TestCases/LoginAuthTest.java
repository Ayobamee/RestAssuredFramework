package TestCases;

import TokenManager.LoginToken;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginAuthTest {
	// This test is to ensure that a token can be generated for a driver.

	@Test

	public void Login() throws IOException {

		//Open baseurl
		LoginToken load = new LoginToken();
		load.getBaseUrl();

		//Display token generated
		 load.adminToken();

	}
}