package com.splus.service.connector;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;

public class JiraConnector {

	private final String USER_AGENT = "Mozilla/5.0";

	String jiraUrl;
	String username;
	String password;

	public JiraConnector() {
		// TODO Auto-generated constructor stub
	}

	public JiraConnector(String jiraUrl, String username, String password) {
		this.jiraUrl = jiraUrl;
		this.username = username;
		this.password = password;

	}

	// HTTP POST request
	public String callGet() throws Exception {

		StringBuilder response = new StringBuilder();
		try {

			URL url = new URL(getjiraUrl());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			String encoding = Base64.getEncoder()
					.encodeToString((getUsername() + ":" + getPassword()).getBytes("UTF-8"));
			conn.setRequestProperty("Authorization", "Basic " + encoding);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			
			while ((output = br.readLine()) != null) {
				response.append(output);
			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			throw e; 

		} catch (IOException e) {
			throw e;
		}
		return response.toString();

	}

	/**
	 * @return the jiraUrl
	 */
	public String getjiraUrl() {
		return jiraUrl;
	}

	/**
	 * @param jiraUrl
	 *            the jiraUrl to set
	 */
	public void setjiraUrl(String jiraUrl) {
		this.jiraUrl = jiraUrl;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
