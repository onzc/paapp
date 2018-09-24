import Team.SimpleTeamService;

class Main {
	protected static final Integer PORT = 4567;

	public static void main(String[] p_args) {
		SimpleTeamService simpleTeamService = new SimpleTeamService();
		new Server(PORT, simpleTeamService);
	}

}