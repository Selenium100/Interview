package com.utility.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	Properties pro;

	public ConfigReader() {

		try {

			pro = new Properties();
			FileInputStream fis = new FileInputStream(new File("./config.properties"));
			pro.load(fis);

		} catch (Exception e) {

			e.getMessage();
		}

	}

	public String getAppUrl() {

		return pro.getProperty("appurl");
	}

	public String getFirstName() {

		return pro.getProperty("firstname");
	}
	
	public String getLastName() {

		return pro.getProperty("lastname");
	}
	
	public String getemail() {

		return pro.getProperty("email");
	}
	
	public String getphone() {

		return pro.getProperty("teleophone");
	}
	
	public String getpassword() {

		return pro.getProperty("password");
	}
	
	public String getConfirmpassword() {

		return pro.getProperty("confirmpassword");
	}

}
