package com.utility.com;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

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

		Document d1 = new Document();
		d1.append("methodName", methodName);
		d1.append("className", className);
		d1.append("status", "PASS");

		List<Document> docList = new ArrayList<Document>();
		docList.add(d1);

		webCollection.insertMany(docList);

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String className = result.getMethod().getRealClass().getName();

		Document d1 = new Document();
		d1.append("methodName", methodName);
		d1.append("className", className);
		d1.append("status", "FAIL");

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
		
	}

	@Override
	public void onFinish(ITestContext context) {

		mongoClient.close();
	}

}
