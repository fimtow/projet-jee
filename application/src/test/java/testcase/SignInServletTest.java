package testcase;


import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.junit.Test;



public class SignInServletTest {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	
	private final String URL = "http://localhost:8080/application/signin";
	
	private static final String POST_PARAMS1 = "username=Test&password=TestPassword";
	
	private static final String POST_PARAMS2 = "username=othmane&password=1234";
	

    @Test
    public void GetLoginPage()
    {
    	
    	try {
			URL obj = new URL(URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			assertEquals(responseCode,HttpURLConnection.HTTP_OK);
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    @Test
    public void LogUserExist()
    {
		try {
    		 
			URL obj = new URL(URL);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(POST_PARAMS1.getBytes());
			os.flush();
			os.close();
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null)
				{
					response.append(inputLine);
				}
				in.close();
				JSONObject responseJSON = new JSONObject(response.toString());
				assertEquals(responseJSON.get("success"),true);
			} else {
				System.out.println("POST request not worked");
			}
			//JSONObject myResponse = new JSONObject(response.toString());
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    
    @Test
    public void LogUserNotExist() {
    
		try {
    		URL obj;
			obj = new URL(URL);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(POST_PARAMS2.getBytes());
			os.flush();
			os.close();
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { //success
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));
				String inputLine;
				
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				JSONObject responseJSON = new JSONObject(response.toString());
				assertEquals(responseJSON.get("success"),false);
				
			} else {
				System.out.println("POST request not worked");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}