package testcase;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.junit.Test;


public class UserServletTest {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	
	private final String URL = "http://localhost:8080/application/user";
	
	private static final String POST_PARAMS1 = "id=1";
	
	private static final String POST_PARAMS2 = "id=10";
	
    @Test
    public void searchForUserExist()
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
				JSONObject user = responseJSON.getJSONObject("user");
				assertEquals(responseJSON.get("succes"),true);
				assertEquals(user.get("id"),1);
				assertEquals(user.get("username"),"Test");
				assertEquals(user.get("email"),"test@gmail.com");
			} else {
				System.out.println("POST request not worked");
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @Test
    public void searchForUserDoesntExist() {
    
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
				assertEquals(responseJSON.get("succes"),false);
			} else {
				System.out.println("POST request not worked");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    }
 

}