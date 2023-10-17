package com.neotech.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.neotech.pages.DashboardPageElements;
	import com.neotech.pages.LoginPageElements;
	import com.neotech.utils.CommonMethods;
	import com.neotech.utils.ConfigsReader;

	public class LoginTest extends CommonMethods {

		@Test(groups= {"smoke" ,"regression"})
		public void validLogin() {

			LoginPageElements login = new LoginPageElements();
			DashboardPageElements dashboard = new DashboardPageElements();

			// send username
			sendText(login.username, ConfigsReader.getProperty("username"));
			wait(1);

			// send password
			sendText(login.password, ConfigsReader.getProperty("password"));
			wait(1);

			// click on login
			click(login.loginButton);
			// Or use jsClick or Action.click
			wait(1);

			// Verify account name
			String expected = "Jacqueline White";
			String actual = dashboard.accountName.getText();

			// Assertion
			Assert.assertEquals(actual, expected, "The account does NOT match!!!");
		}

		
		@Test(groups="regression")
		public void emptyPasswordLogin() {

			LoginPageElements login = new LoginPageElements();
		//	DashboardPageElements dashboard = new DashboardPageElements();

			// send username
			sendText(login.username, ConfigsReader.getProperty("username"));
			wait(1);

			// click on login
			click(login.loginButton);
			// Or use jsClick or Action.click
			wait(1);

			// Verify account name
			String expected = "Password cannot be empty";
			String actual = login.blankPasswordMessage.getText();

			// Assertion
			Assert.assertEquals(actual, expected, "Error does NOT match!!!");
		}
	
		@Test(groups= "regression")
		public void invalidPassword() {

			LoginPageElements login = new LoginPageElements();

			// send username
			sendText(login.username, ConfigsReader.getProperty("username"));
			wait(1);

			// send password
			sendText(login.password, "pasword123");

			// click on login
			click(login.loginButton);
			// Or use jsClick or Action.click
			wait(1);

			// Verify account name
			String expected = "Invalid Credentials";
			String actual =login.invalidCredentials.getText();

			// Assertion
			Assert.assertEquals(actual, expected, "The message does NOT match!!!");
		}
		
}
