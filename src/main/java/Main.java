import Team.TeamService;

class Main {
	protected static final Integer PORT = 4567;
	private static TeamService s_teamService = new TeamService();
	public static void main(String[] p_args) {
		new Server(PORT);
	}

}