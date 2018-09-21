package Team;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamTest {
	private static final String TEAM_NAME = "Exeter Chiefs";
	private static final String UPDATED_NAME = "Harlequins";
	private  static  final  Integer TEAM_ID =1;
	private Team m_team;
	@BeforeEach
	void setUp() {
		m_team = new Team(TEAM_ID, TEAM_NAME);
	}
	@AfterEach
	void tearDown() {
		m_team = null;
	}
	@Test
	void getId() {
		assertEquals (TEAM_ID, m_team.getId());
	}
	@Test
	void getName() {
		assertEquals(TEAM_NAME, m_team.getName());
	}
	@Test
	void setName() {
		m_team.setName(UPDATED_NAME);
		assertEquals(m_team.getName(), UPDATED_NAME);
	}

}