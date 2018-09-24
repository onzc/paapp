import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class APITest {
	private static final String TEAM_1 = "Wasps";
	private static final String TEAM_2 = "Sale";
	@BeforeEach
	void setUp() {
		RestSparkStarter restSparkStarter = new RestSparkStarter("localhost", 4567, "/");
		restSparkStarter.startSparkAppIfNotRunning(4567);
	}
	@Test
	void getHeartBeat() throws Exception {
		RestResponse response = RestUtils.sendGet("http://localhost:4567/");
		assertTrue(response.getResponseCode() == 200);
	}
	@Test
	void postTeam() throws Exception {
		RestResponse response = addTestTeam(TEAM_1);
		assertTrue(response.getResponseCode() == 201);
	}
	@Test
	void getTeams() throws Exception {
		RestResponse response = addTestTeam(TEAM_1);
		assertTrue(response.getResponseCode() == 201);

		RestResponse responseTeams = RestUtils.sendGet("http://localhost:4567/teams");
		assertTrue(response.getResponseCode() == 201);
	}
	@Test
	void putTeam() throws Exception {
		RestResponse response = addTestTeam(TEAM_1);
		assertTrue(response.getResponseCode() == 201);
		RestResponse responsePut = RestUtils.sendPut("http://localhost:4567/team/1?name=Nottingham");
		assertTrue(responsePut.getResponseCode() == 200);
		System.out.println(responsePut.getMessage());
	}
	@Test
	void deleteTeam() throws Exception {
		RestResponse response1 = addTestTeam(TEAM_1);
		assertTrue(response1.getResponseCode() == 201);
		RestResponse response2 = addTestTeam(TEAM_2);
		assertTrue(response2.getResponseCode() == 201);
		RestResponse responseDelete = RestUtils.sendDelete("http://localhost:4567/team/1");
		assertTrue(responseDelete.getResponseCode() == 201);

	}
	private RestResponse addTestTeam(String p_name) throws Exception {
		String urlParameters = "name=" + p_name;
		return RestUtils.sendPost("http://localhost:4567/team/add", urlParameters);
	}
}
