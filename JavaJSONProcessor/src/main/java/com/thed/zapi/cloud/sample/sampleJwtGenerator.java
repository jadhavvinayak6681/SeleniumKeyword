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

import com.splus.service.connector.JiraConnector;
import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;

@SuppressWarnings("unused")
public class sampleJwtGenerator {

	/*
	 * Zephyr Metadata
	*/
	
	// Replace Zephyr BaseUrl with the <ZAPI_CLOUD_URL> shared with ZAPI Cloud
	// Installation
	private static String zephyrBaseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
	// zephyr accessKey , we can get from Addons >> zapi section
	private static String accessKey = "MGZlZDlmODQtNzUzOS0zNDEzLWE2MDMtYWNiNTk1YjA1MjcyIGFkbWluIFVTRVJfREVGQVVMVF9OQU1F";
	// zephyr secretKey , we can get from Addons >> zapi section
	private static String secretKey = "sXm_b4tNFvxi0KrsrS5eL2UIW-gnuihSrZ64MKYBJrE";

	/*
	 * JIRA metadata
	*/
	// Replace Zephyr BaseUrl with the <ZAPI_CLOUD_URL> shared with ZAPI Cloud
		// Installation
		private static String jiraBaseUrl = "https://systemspluspune2.atlassian.net";
		private static String jiraUser = "systemspluspune2@gmail.com";
		private static String jiraPassword = "samartha9*";
		static Object[] jsondata = new Object[5];	
		
	public static void main(String[] args) throws URISyntaxException, IllegalStateException, IOException 
	{
		Object[] getjsondata = new Object[3];
		//String jwtToken = getToken();
		
		//getTesSteps("PA" , "PA-149");
		putTestResult("PROJ2" , "PROJ2-1");
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
	public static void putTestResult(String projectKey , String issueKey) throws URISyntaxException
	{
		//long jiraProjectId  = getJiraProjectId(projectKey);
		//long jiraIssueId = getJiraIssueId(issueKey) ;
		
		String zephyrTesStepApi = "/public/rest/api/1.0/execution/";  
		String executionId="0001511769584449-242ac112-0001";
	
		Client client = ClientBuilder.newClient();
		Entity<JSONObject> payload = Entity.json(readingJSON.readingJson1());
		Response response = client.target(zephyrBaseUrl+zephyrTesStepApi+executionId)
		  .request(MediaType.APPLICATION_JSON_TYPE)
		  .header("Authorization", getToken(zephyrTesStepApi , executionId ))
		  .header("zapiAccessKey", "MGZlZDlmODQtNzUzOS0zNDEzLWE2MDMtYWNiNTk1YjA1MjcyIGFkbWluIFVTRVJfREVGQVVMVF9OQU1F")
		  .put(payload);

		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		System.out.println("body:" + response.readEntity(String.class));
	}
	
	// get all test steps of Test case 
	public static void getTesSteps(String projectKey , String issueKey) throws URISyntaxException {
		
		//long jiraProjectId  = getJiraProjectId(projectKey);
		//long jiraIssueId = getJiraIssueId(issueKey) ;
		String stepResultId = "0001510319249006-242ac112-0001";
		String executionid = "0001510319248977-242ac112-0001";
		
		String zephyrTesStepApi = "/public/rest/api/1.0/stepresult/";  
		
		
		Client client = ClientBuilder.newClient();
		Response response = client.target(zephyrBaseUrl+zephyrTesStepApi+stepResultId+"?executionId="+executionid)
		  .request(MediaType.APPLICATION_JSON_TYPE)
		  //.header("Authorization", getToken(zephyrTesStepApi , stepResultId , executionid ))
		  .header("zapiAccessKey", "OTRlNzM3MTQtYjc1Yy0zM2UyLWI3MGUtNjcyOTA1MTUzMWJkIHJvaGl0LmxhYmhlIFN5c3RlbUtleQ")
		  .get();

		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		System.out.println("body:" + response.readEntity(String.class));
	}
	
	
	// Get Method
public static Object[] getExecution(String projectKey , String issueKey) throws URISyntaxException, IOException {
		
		long jiraProjectId  = getJiraProjectId(projectKey);
		long jiraIssueId = getJiraIssueId(issueKey) ; 
		
		String zephyrTesStepApi = "/public/rest/api/1.0/executions?issueId="+jiraIssueId+"&projectId="+jiraProjectId;  
		Client client = ClientBuilder.newClient();
		Response response = client.target(zephyrBaseUrl+zephyrTesStepApi)
		  .request(MediaType.APPLICATION_JSON_TYPE)
		  .header("Authorization", getToken(zephyrTesStepApi , jiraProjectId ))
		  .header("zapiAccessKey", "MGZlZDlmODQtNzUzOS0zNDEzLWE2MDMtYWNiNTk1YjA1MjcyIGFkbWluIFVTRVJfREVGQVVMVF9OQU1F")
		  .get();
		String repos = response.readEntity(String.class);
		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		System.out.println("body:" + repos);
		
		jsondata[0] = jiraProjectId;
		jsondata[1] = jiraIssueId;
		jsondata[2] = repos;
		jsondata[3]="0001511769584449-242ac112-0001";
		return jsondata;
	}
	
	public static long getJiraIssueId(String issueKey) {
		long issueId = 0;
		
		JiraConnector jiraConnector = new JiraConnector((jiraBaseUrl + "/rest/api/latest/issue/"+issueKey+"	" ),jiraUser,jiraPassword);	
		
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
	
	public static long getJiraProjectId(String projectKey) {
		long projectId = 0;
		
		JiraConnector jiraConnector = new JiraConnector((jiraBaseUrl + "/rest/api/latest/project/"+projectKey+"?expand=projectKeys" ),jiraUser,jiraPassword);	
		
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

	public static class readingJSON
	{
		public static JSONObject readingJson1()
		{
			JSONObject jsonObject = null;
			JSONParser parser = new JSONParser();
		try{
	            Object obj = parser.parse(new FileReader("D:\\processedJSON.json"));
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
