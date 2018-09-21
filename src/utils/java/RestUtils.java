import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestUtils {
	private static final String USER_AGENT = "Mozilla/5.0";
	public static RestResponse sendPost(String p_url, String p_urlParameters) throws Exception {
		URL obj = new URL(p_url);
		HttpURLConnection con = (HttpURLConnection)obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(p_urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + p_url);
		System.out.println("Post parameters : " + p_urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		return new RestResponse(responseCode, response.toString());
	}
	public static RestResponse sendPut(String p_url) throws Exception {
	
			URL obj = new URL(p_url);
			HttpURLConnection con = (HttpURLConnection)obj.openConnection();
	
			// optional default is GET
			con.setRequestMethod("PUT");
	
			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
	
			int responseCode = con.getResponseCode();
			
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			return new RestResponse(responseCode, response.toString());
	
		}
	public static RestResponse sendGet(String p_url) throws Exception {

		URL obj = new URL(p_url);
		HttpURLConnection con = (HttpURLConnection)obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
	
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return new RestResponse(responseCode, response.toString());

	}
}
