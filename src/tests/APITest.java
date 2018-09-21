import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class APITest {

	@BeforeEach
	void setUp() {
		RestSparkStarter restSparkStarter = new RestSparkStarter("localhost",4567 , "/");
		restSparkStarter.startSparkAppIfNotRunning(4567);
	}
	@Test
	void getHeartBeat() throws Exception {
		RestResponse response = RestUtils.sendGet("http://localhost:4567/")	;
		assertTrue( response.getResponseCode() == 200);
	}
	@Test
		void postTeam () throws Exception{
		RestResponse response = addTestTeam("Wasp");
		assertTrue( response.getResponseCode() == 201);
	}
	@Test
	void getTeams () throws  Exception{
		RestResponse response = addTestTeam("Wasps");
		assertTrue( response.getResponseCode() == 201);
		
		RestResponse responseTeams = RestUtils.sendGet("http://localhost:4567/teams")	;
		assertTrue(response.getResponseCode() == 201); 
	}
	@Test 
	void putTeam () throws  Exception {
		RestResponse response = addTestTeam("Wasps");
		assertTrue( response.getResponseCode() == 201);
		RestResponse responsePut= RestUtils.sendPut("http://localhost:4567/team/1?name=Nottingham");
		assertTrue(responsePut.getResponseCode()== 201);
		System.out.println(responsePut.getMessage());
	}
	private RestResponse addTestTeam (String p_name) throws Exception{
		String urlParameters = "name=" + p_name;
		return  RestUtils.sendPost("http://localhost:4567/team/add", urlParameters);
	}
}
