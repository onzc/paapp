package Team;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamServiceTest {
	private static final String TEAM_1 = "Leicester Tigers";
	private static final String TEAM_2 = "Newcastle Falcons";

	private SimpleTeamService m_teamService;
	@org.junit.jupiter.api.BeforeEach
	void setUp() {
		m_teamService = new SimpleTeamService();
	}
	@org.junit.jupiter.api.AfterEach
	void tearDown() {
		m_teamService = null;
	}
	@org.junit.jupiter.api.Test
	void isSetUpCorrectly() {
		assertEquals(0, m_teamService.findAllTeams().size());
	}
	@org.junit.jupiter.api.Test
	void findById() {
		Team team1 = addTeam(m_teamService, TEAM_1);
		Team team2 = addTeam(m_teamService, TEAM_2);

		Team foundTeam2 = m_teamService.findById(team2.getId());
		assertEquals(team2.getId(), foundTeam2.getId());

		Team foundTeam1 = m_teamService.findById(team1.getId());
		assertEquals(team1.getId(), foundTeam1.getId());
	}
	@org.junit.jupiter.api.Test
	void add() {
		Team added = addTeam(m_teamService, TEAM_1);
		assertEquals(TEAM_1, added.getName());

	}
	@org.junit.jupiter.api.Test
	void update() {
		Team added = addTeam(m_teamService, TEAM_1);
		assertEquals(TEAM_1, added.getName());
		Team updated = m_teamService.updateTeam(added.getId(), TEAM_2);
		assertEquals(TEAM_2, updated.getName());
	}
	@org.junit.jupiter.api.Test
	void delete() {
		Team team1 = addTeam(m_teamService, TEAM_1);
		addTeam(m_teamService, TEAM_2);
		assertEquals(2, m_teamService.findAllTeams().size());

		m_teamService.deleteTeam(team1.getId());
		assertEquals(1, m_teamService.findAllTeams().size());
	}
	@org.junit.jupiter.api.Test
	void findAll() {
		addTeam(m_teamService, TEAM_1);
		addTeam(m_teamService, TEAM_2);
		assertEquals(2, m_teamService.findAllTeams().size());
	}

	private Team addTeam(SimpleTeamService p_teamService, String p_name) {
		return p_teamService.addTeam(p_name);
	}
}