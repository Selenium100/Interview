package com.utility.com;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.bson.Document;
import org.openqa.selenium.Capabilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbTestListner implements ITestListener {

	MongoCollection<Document> webCollection;
	MongoClient mongoClient;

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String className = result.getMethod().getRealClass().getName();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String date=dtf.format(now);
		
		

		Document d1 = new Document();
		d1.append("methodName", methodName);
		d1.append("className", className);
		d1.append("status", "PASS");
		d1.append("date", date);

		List<Document> docList = new ArrayList<Document>();
		docList.add(d1);

		webCollection.insertMany(docList);

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String className = result.getMethod().getRealClass().getName();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String date=dtf.format(now);

		Document d1 = new Document();
		d1.append("methodName", methodName);
		d1.append("className", className);
		d1.append("status", "FAIL");
		d1.append("date", date);

		List<Document> docList = new ArrayList<Document>();
		docList.add(d1);

		webCollection.insertMany(docList);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) { // Logger

		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = mongoClient.getDatabase("autoDB");
		// database.getCollection("web").drop();
		webCollection = database.getCollection("web");

	}

	@Override
	public void onFinish(ITestContext context) {

		mongoClient.close();
	}

}
