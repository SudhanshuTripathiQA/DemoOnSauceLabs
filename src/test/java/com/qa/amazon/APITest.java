package com.qa.amazon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APITest {

	private final static String GET_Request = "https://reqres.in/api/users?page=2";
	private final static String POST_Request = "https://reqres.in/api/users";

	/*
	 * Inside this test case I am going to verify the get request by
	 * HttpURLConnection from the java library and I have assert with assertTrue
	 * function it returns the json resopnse and I have validate with same data as I
	 * have sent the get URL
	 */
	@Test
	void verifyGETRequestTest() throws IOException {
		JSONObject responseMessage = sendGETRequest(GET_Request);
		Assert.assertTrue(responseMessage.get("page").equals(2) && responseMessage.get("total").equals(12),
				"Response message is not correct");
	}

	/*
	 * Inside this test case I am going to verify the get request by using okhttp3
	 * library and I have assert with assertTrue function it returns the json
	 * resopnse and I have validate with entered data as I have provided at the time
	 * of send the post request.
	 */

	@Test
	void verifyPostRequestTest() throws IOException {
		JSONObject responseMessage = sendPostRequest(POST_Request);
		Assert.assertTrue(responseMessage.get("name").equals("Sudhanshu") && responseMessage.get("job").equals("QA"),
				"Resource not created suceessfully");
	}

	private JSONObject sendGETRequest(String sendGetRequest) {
		HttpURLConnection connection = getHTTPConnection(sendGetRequest);
		try {
			connection.setRequestMethod("GET");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		int responseCode = 0;
		try {
			responseCode = connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject myJsonResponse = null;
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			String inputLine;
			StringBuffer response = new StringBuffer();

			try {
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			myJsonResponse = new JSONObject(response.toString());
			// Printing the response result
			System.out.println(myJsonResponse);
		} else {
			System.out.println("GET request not worked");
		}
		return myJsonResponse;
	}

	private JSONObject sendPostRequest(String url) {
		String responseMessage = "";
		OkHttpClient client = new OkHttpClient();

		RequestBody requestBody = new FormBody.Builder().add("name", "Sudhanshu").add("job", "QA").build();
		Request request = new Request.Builder().url(url).post(requestBody).build();
		Response response = null;

		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (response.code() == 201) {
			if (response.isSuccessful()) {
				try {
					responseMessage = response.body().string();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("POST request not worked");
		}
		JSONObject myJsonResponse = new JSONObject(responseMessage);
		System.out.println(myJsonResponse);
		return myJsonResponse;
	}

	private HttpURLConnection getHTTPConnection(String connectionUrl) {
		URL baseURL = null;
		try {
			baseURL = new URL(connectionUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) baseURL.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
