package com.splus.testing.automation.json;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import com.thed.zapi.cloud.sample.ZapiJwtGenerator;

public class JSONHandler {
	//private static final String filePath = "C:\\Users\\Snehal.Mahajan\\Desktop\\Projects\\cucumberCallingSmoke\\target\\cucumberNew.json";
	static int ii = 0;
	public static JSONObject createjsonObject = new JSONObject();
	public static JSONObject statusJsonObj = new JSONObject();
	
	public static void main(String[] arg) throws FileNotFoundException, IOException, ParseException, URISyntaxException
	{
		//jsonProcessormethod();
	}
	//Commented this method to remove dependancy of Cucumber(json file)
	/*public static void jsonProcessormethod() throws FileNotFoundException, IOException, ParseException, URISyntaxException
	{
		Boolean status = true;
		// read the json file
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(new FileReader(filePath));
		org.json.JSONObject req = (org.json.JSONObject) obj;
		
		 JSONArray lineItems = jsonObject.getJSONArray("elements");
		    for (Object o : lineItems) {
		        JSONObject jsonLineItem = (JSONObject) o;
		        String key = jsonLineItem.getString("key");
		        String value = jsonLineItem.getString("value");
		    }
		//JSONObject req = new JSONObject(join(loadStrings(data.json),""));
		JSONObject locs = req.getJSONObject("locations");
		JSONArray recs = locs.getJSONArray("record");
		
		for (int i = 0; i < recs.length(); ++i) 
		{
		    JSONObject rec = recs.getJSONObject(i);
		    int id = rec.getInt("id");
		    String loc = rec.getString("loc");
		}
		
		File file = new File(filePath);
		String content = FileUtils.readFileToString(file, "utf-8");
		 // Convert JSON string to JSONObject
		JSONArray jarrayone = new JSONArray(content);
		
	    JSONObject jsonObject = new JSONObject();
	    
	    for (int i = 0; i < jarrayone.length(); i++) 
	    {
	        System.out.println(jarrayone.get(i));
	        System.out.println(jarrayone.get(i));
	        jsonObject = jarrayone.getJSONObject(i);
	        //System.out.println(jsonObject.getString("name"));
	    }
	    
	    JSONObject jsonObject2 = new JSONObject();
	    
	    String name = jsonObject.getString("name");
		System.out.println(name); 
	    
	    JSONArray jarray1 = jsonObject.getJSONArray("elements");
	    
	    for (int i = 0; i < jarray1.length(); i++) 
	    {
	        //System.out.println(jarray1.get(i));
	        jsonObject2 = jarray1.getJSONObject(i);
	    }
	    
	    JSONArray jarray2 = jsonObject2.getJSONArray("steps"); 
	    for (int i = 0; i < jarray2.length(); i++) 
	    {
	    	int j=i+1;
	    	//Boolean tcstatus = true;
	        //System.out.println(jarray2.get(i));
	        JSONObject jsonObject3 = jarray2.getJSONObject(i);
	        System.out.print(j+")"+jsonObject3.getString("name"));
	        //System.out.println(jsonObject3.get("result"));
	        JSONObject jsonObject4 = (JSONObject) jsonObject3.get("result");
	        System.out.println(":"+jsonObject4.get("status"));
	        //status = createnewJSON(jsonObject4.get("status").toString());
			if (jsonObject4.get("status").toString().equals("passed"))
			{
				ii=1;
			}
			else if(jsonObject4.get("status").toString().equals("failed"))
			{
				ii=2;
			}
			if (ii == 2)
			{
				status = false;
			}
	    } 
	    statusJsonObj.put("id",(status)? 1:2);
	    createnewJSONfile();
	   // JSONObject elements = jsonObject.getJSONObject("elements");
	    //System.out.println(elements.getString("steps")); 
	}*/
	
	//Updated this method to take direct output from selenium and pass it to jira
	public static void createnewJSONfile(Boolean status,String projectId,String issueID,
			Boolean flag,String cycleID,String jiraUrl,String jiraUser,String jiraPass ) throws IOException, URISyntaxException
	{
		Object[] getjsondata = new Object[3];
		System.out.println("Status of Test Case: " +status);

		if(flag.equals(true)){
			statusJsonObj.put("id",(status)? 1:2);
			createjsonObject.accumulate("status", statusJsonObj);
			getjsondata = ZapiJwtGenerator.getExecution(projectId,issueID,cycleID,jiraUrl,jiraUser,jiraPass);

			createjsonObject.put("projectId",getjsondata[0]);
			System.out.println("projectId"+getjsondata[0]);

			createjsonObject.put("issueId", getjsondata[1]);
			System.out.println("issueId"+getjsondata[1]);

			//getjsonfields((String) getjsondata[2],(String) getjsondata[3]);
			createjsonObject.put("cycleId",getjsondata[2]);
			System.out.println("cycleId"+getjsondata[2]);

			createjsonObject.put("versionId",getjsondata[3]);
			System.out.println("versionId"+getjsondata[3].toString());
			/*statusJsonObj.put("comment","Comment for execution");
		statusJsonObj.put("assigneeType","currentUser/assignee");
		statusJsonObj.put("assignee","userKey");*/
			// Create a new FileWriter object
			FileWriter fileWriter = new FileWriter("processedJSON.json");
			// Writting the jsonObject into sample.json
			fileWriter.write(createjsonObject.toString());
			fileWriter.close();
			System.out.println("Updating test cases execution json result in to Jira");
			ZapiJwtGenerator.putTestResult(getjsondata[4].toString());
			System.out.println("Execution completed"); 
		}
	}

	/*private static void getjsonfields(String getJsonDatainString, String executionid)
	{		
		JSONObject object1 = new JSONObject();
		JSONObject object2 = new JSONObject();
		JSONArray getjsondata = new JSONArray("["+getJsonDatainString+"]");
		
		for (int i=0; i< getjsondata.length(); i++ )
		{
			object1 = getjsondata.getJSONObject(i);
		}
		
		JSONArray array2 = object1.getJSONArray("executions");
		for (int i=0; i<array2.length(); i++ )
		{
			object2 = array2.getJSONObject(i);
			JSONObject object3 = object2.getJSONObject("execution");
			
			if (executionid.equals(object3.getString("id")))
			{
				System.out.println("executionid : " + object3.getString("id"));
				arrayJson[0] = object3.get("versionId");
				System.out.println("versionId"+arrayJson[0]);
				arrayJson[1] = object3.get("cycleId");
				System.out.println("cycleId"+arrayJson[1]);
			}
		}
		
	}*/}
