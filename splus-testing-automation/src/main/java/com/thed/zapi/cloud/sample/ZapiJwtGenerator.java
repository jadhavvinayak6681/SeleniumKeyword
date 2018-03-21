package com.thed.zapi.cloud.sample;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.ToLongFunction;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;

import org.json.JSONArray;
import org.json.JSONException;
//import org.json.JSONObject;
import org.json.simple.JSONObject;

import com.google.gson.JsonObject;
import com.splus.service.connector.JiraConnector;
import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;

@SuppressWarnings("unused")
public class ZapiJwtGenerator {

	/*
	 * Zephyr Metadata
	 */

	// Replace Zephyr BaseUrl with the <ZAPI_CLOUD_URL> shared with ZAPI Cloud
	// Installation
	private static String zephyrBaseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
	// zephyr accessKey , we can get from Addons >> zapi section
	private static String accessKey = "ZTExYzUwNDItNjViMi0zYTcyLWJlYjgtMWUxMmViOTI5NGVlIGFkbWluIFVTRVJfREVGQVVMVF9OQU1F";
	// zephyr secretKey , we can get from Addons >> zapi section
	private static String secretKey = "xPa4cSNeA13I826n0Gh9PaPfzrN5wsAIcD5FuROHYUc";

	/*
	 * JIRA metadata
	 */
	// Replace Zephyr BaseUrl with the <ZAPI_CLOUD_URL> shared with ZAPI Cloud
	// Installation
	/*private static String jiraBaseUrl = "https://systemspluspune2.atlassian.net";
		private static String jiraUser = "systemspluspune2@gmail.com";
		private static String jiraPassword = "samartha9*";*/
	static Object[] jsondata = new Object[10];	

	public static void main(String[] args) throws URISyntaxException, IllegalStateException, IOException 
	{
		Object[] getjsondata = new Object[3];
		//String jwtToken = getToken();

		//getTesSteps("PROJ" , "PROJ-1");
		//getExecution("PROJ" , "PROJ-1", "-1", "systemspluspune3@gmail.com","samartha9*");
		
		//putTestResult("PROJ" , "PROJ-1");
		//getjsondata = getExecution("PROJ2" , "PROJ2-1");		
	}

	private static String getToken(String api, Long jiraProjectId , String stepResultId) throws URISyntaxException {

		String userName = "admin";
		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName).build();
		JwtGenerator jwtGenerator = client.getJwtGenerator();

		// API to which the JWT token has to be generated
		String createCycleUri = zephyrBaseUrl + api + stepResultId; //jiraIssueId+"?projectId="+jiraProjectId

		URI uri = new URI(createCycleUri);
		int expirationInSec = 360;
		String jwt = jwtGenerator.generateJWT("PUT", uri, expirationInSec);

		// Print the URL and JWT token to be used for making the REST call
		System.out.println("FINAL API : " +uri.toString());
		System.out.println("JWT Token : " +jwt);

		return jwt;
	}

	// PUT JWT
	private static String getToken(String api, String executionId) throws URISyntaxException {

		String userName = "admin";
		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName).build();
		JwtGenerator jwtGenerator = client.getJwtGenerator();

		// API to which the JWT token has to be generated
		String createCycleUri = zephyrBaseUrl + api + executionId; //jiraIssueId+"?projectId="+jiraProjectId

		URI uri = new URI(createCycleUri);
		int expirationInSec = 360;
		String jwt = jwtGenerator.generateJWT("PUT", uri, expirationInSec);

		// Print the URL and JWT token to be used for making the REST call
		System.out.println("FINAL API : " +uri.toString());
		System.out.println("JWT Token : " +jwt);

		return jwt;
	}

	// GET JWD
	private static String getToken(String api, Long jiraProjectId) throws URISyntaxException {

		String userName = "admin";
		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName).build();
		JwtGenerator jwtGenerator = client.getJwtGenerator();

		// API to which the JWT token has to be generated
		String createCycleUri = zephyrBaseUrl + api; 

		URI uri = new URI(createCycleUri);
		int expirationInSec = 360;
		String jwt = jwtGenerator.generateJWT("GET", uri, expirationInSec);

		// Print the URL and JWT token to be used for making the REST call
		System.out.println("FINAL API : " +uri.toString());
		System.out.println("JWT Token : " +jwt);

		return jwt;
	}

	// PUT data in Jira
	public static void putTestResult(String executionId) throws URISyntaxException
	{
		//long jiraProjectId  = getJiraProjectId(projectKey);
		//long jiraIssueId = getJiraIssueId(issueKey) ;

		String zephyrTesStepApi = "/public/rest/api/1.0/execution/";  
		String jiraExecutionID=executionId;

		Client client = ClientBuilder.newClient();
		Entity<JSONObject> payload = Entity.json(readingJSON.readingJson1());
		Response response = client.target(zephyrBaseUrl+zephyrTesStepApi+jiraExecutionID)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", getToken(zephyrTesStepApi , jiraExecutionID ))
				.header("zapiAccessKey", "ZTExYzUwNDItNjViMi0zYTcyLWJlYjgtMWUxMmViOTI5NGVlIGFkbWluIFVTRVJfREVGQVVMVF9OQU1F")
				.put(payload);

		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		System.out.println("body:" + response.readEntity(String.class));
	}

	// get all test steps of Test case 
	/*public static void getTesSteps(String projectKey , String issueKey) throws URISyntaxException {

		//long jiraProjectId  = getJiraProjectId(projectKey);
		//long jiraIssueId = getJiraIssueId(issueKey) ;
		String executionid = "0001510319248977-242ac112-0001";

		long jiraProjectId  = getJiraProjectId(projectKey,"systemspluspune3@gmail.com","samartha9*");
		long jiraIssueId = getJiraIssueId(issueKey,"systemspluspune3@gmail.com","samartha9*"); 


		String zephyrTesStepApi = "/public/rest/api/1.0/executions?issueId="+jiraIssueId+"&projectId="+jiraProjectId; 


		Client client = ClientBuilder.newClient();
		Response response = client.target(zephyrBaseUrl+zephyrTesStepApi)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", getToken(zephyrTesStepApi , executionid ))
				.header("zapiAccessKey", "ZTExYzUwNDItNjViMi0zYTcyLWJlYjgtMWUxMmViOTI5NGVlIGFkbWluIFVTRVJfREVGQVVMVF9OQU1F")
				.get();

		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		System.out.println("body:" + response.readEntity(String.class));
	}*/


	// Get Method
	public static Object[] getExecution(String projectKey,String issueKey,String cycleID, String jiraUrl,
			String jiraUser, String jiraPass) throws URISyntaxException, IOException {

		long jiraProjectId  = getJiraProjectId(projectKey,jiraUrl,jiraUser,jiraPass);
		long jiraIssueId = getJiraIssueId(issueKey,jiraUrl,jiraUser,jiraPass); 
		//long excid = getExecutionId(jiraProjectId,jiraIssueId,jiraUser,jiraPass);

		String zephyrTesStepApi = "/public/rest/api/1.0/executions?issueId="+jiraIssueId+"&projectId="+jiraProjectId;  
		Client client = ClientBuilder.newClient();

		Response response = client.target(zephyrBaseUrl+zephyrTesStepApi)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", getToken(zephyrTesStepApi , jiraProjectId ))
				.header("zapiAccessKey", "ZTExYzUwNDItNjViMi0zYTcyLWJlYjgtMWUxMmViOTI5NGVlIGFkbWluIFVTRVJfREVGQVVMVF9OQU1F")
				.get();
		String repos = response.readEntity(String.class);
		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		System.out.println("body:" + repos);

		org.json.JSONObject Object1 = new org.json.JSONObject(repos);

		jsondata[0] = jiraProjectId;
		jsondata[1] = jiraIssueId;
		jsondata[2] = cycleID;

		JSONArray arrayofobject1 = Object1.getJSONArray("executions");
		org.json.JSONObject object2 = null;
		String executionID = null;
		int versionID;

		for (int i = 0; i < arrayofobject1.length(); ++i) 
		{
			object2 = arrayofobject1.getJSONObject(i);
			System.out.println("Object2" + object2 );
		}
		org.json.JSONObject object3 = object2.getJSONObject("execution");
		System.out.println("Object3" + object3 );

		versionID = object3.getInt("versionId");
		System.out.println("versionId" + versionID );
		jsondata[3] = versionID;

		executionID = object3.getString("id");
		System.out.println("executions" + executionID );
		jsondata[4] = executionID;

		return jsondata;
	}

	public static long getJiraIssueId(String issueKey, String jiraUrl, String jiraUser, String jiraPass) {
		long issueId = 0;

		JiraConnector jiraConnector = new JiraConnector((jiraUrl + "/rest/api/latest/issue/"+issueKey+"	" ),jiraUser,jiraPass);	

		try {
			String json = jiraConnector.callGet();

			// Parse JSON response using org.json
			org.json.JSONObject results = new org.json.JSONObject(json);
			issueId = results.getLong("id");				

		} catch (Exception e) {
			e.printStackTrace();
		}

		return issueId;
	}

	public static long getJiraProjectId(String projectKey, String jiraUrl, String jiraUser, String jiraPass) {
		long projectId = 0;

		JiraConnector jiraConnector = new JiraConnector((jiraUrl + "/rest/api/latest/project/"+projectKey+"?expand=projectKeys" ),jiraUser,jiraPass);	

		try {
			String json = jiraConnector.callGet();

			// Parse JSON response using org.json
			org.json.JSONObject results = new org.json.JSONObject(json);

			projectId = results.getLong("id");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return projectId;
	}

	/*public static long getExecutionId(Long jiraprojectKey, Long jiraissueid, String jiraUser, String jiraPass) {
		long projectId = 0;

		JiraConnector jiraConnector = new JiraConnector((zephyrBaseUrl + "/public/rest/api/1.0/executions?issueId="+jiraissueid+"&projectId="+jiraprojectKey ),jiraUser,jiraPass);	
		try {
			String json = jiraConnector.callGet();

			// Parse JSON response using org.json
			org.json.JSONObject results = new org.json.JSONObject(json);
			System.out.println(results);
			org.json.JSONObject object1 = results.getJSONObject("body");
			JSONArray array1 = object1.getJSONArray("executions");
						
			System.out.println(array1);
			
			for (int i = 0; i < array1.length(); ++i) 
			{
			    org.json.JSONObject rec = array1.getJSONObject(i);
			    String loc = rec.getString("executions");
			    System.out.println(loc);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return projectId;
	}*/
	
	
	
	public static class readingJSON
	{
		public static JSONObject readingJson1()
		{
			JSONObject jsonObject = null;
			JSONParser parser = new JSONParser();
			try{
				Object obj = parser.parse(new FileReader("processedJSON.json"));
				jsonObject =  (JSONObject) obj;
			}
			catch (FileNotFoundException e1) 
			{
				e1.printStackTrace();
			}
			catch (IOException e2) 
			{
				e2.printStackTrace();
			}
			catch (ParseException e3) 
			{
				e3.printStackTrace();
			}
			return jsonObject;
		}
	}

}
