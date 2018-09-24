import Team.DbTeamService;
import Team.TeamService;

public class RestSparkStarter extends SparkStarter {

	private static RestSparkStarter starter;
	private final String host;
	private final String heartBeatPath;
	private final Integer m_port;
	public RestSparkStarter(String host, Integer p_port, String heartBeatPath) {
		this.host = host;
		this.heartBeatPath = heartBeatPath;
		this.m_port = p_port;

	}

	public static RestSparkStarter get(String host) {

		if (RestSparkStarter.starter == null) {
			RestSparkStarter.starter = new RestSparkStarter(host, 4567, "/");
		}
		return RestSparkStarter.starter;
	}

	static Server server;

	public boolean isRunning() {

		try {
			RestResponse restResponse = RestUtils.sendGet("http://" + host + ":" + m_port + heartBeatPath);
			return restResponse.getMessage() == "";
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void startServer() {
		TeamService teamService = new DbTeamService();
		server = new Server(m_port, teamService);

	}

}

