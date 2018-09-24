import Team.DbTeamService;

class Main {
	protected static final Integer PORT = 4567;

	public static void main(String[] p_args) {
//		SimpleTeamService simpleTeamService = new SimpleTeamService();
		DbTeamService dbTeamService = new DbTeamService();
		new Server(PORT, dbTeamService);
	}

}