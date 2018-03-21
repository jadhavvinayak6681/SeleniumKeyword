package Groupid.JsonProject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * JSON processor
 *
 */
public class App 
{
	private static final String filePath = "C:\\Users\\rohit.labhe\\git\\Cucumber_Automation\\target\\cucumber.json";
	public static void main( String[] args )
    {
		try {
			// read the json file
			JSONParser jsonParser = new JSONParser();
			 Object obj = jsonParser.parse(new FileReader(filePath));
			 JSONObject jsonObject = (JSONObject) obj;
			 
			// get a String from the JSON object
			String firstName = (String) jsonObject.get("description");
			System.out.println("description: " + firstName);

			// get a number from the JSON object
			 
			System.out.println("line: " + jsonObject.get("line").toString());

			// get an array from the JSON object
			JSONArray jarray= (JSONArray) jsonObject.get("elements");
			
			// take the elements of the json array
			for(int i=0; i<jarray.size(); i++){
				System.out.println("elements: "+jarray.get(i));
			}
			/*
			Iterator i = lang.iterator();

			// take each value from the json array separately
			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();
				System.out.println("language "+ innerObj.get("lang") + " with level " + innerObj.get("knowledge"));
			}
			// handle a structure into the json object
			JSONObject structure = (JSONObject) jsonObject.get("job");
			System.out.println("Into job structure, name: " + structure.get("name")); */

		} 
		catch (FileNotFoundException ex) { ex.printStackTrace();}
		catch (IOException ex) {ex.printStackTrace();}
		catch (ParseException ex) {ex.printStackTrace();}
		catch (NullPointerException ex) {ex.printStackTrace();}
	}
 }

