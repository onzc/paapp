package Team;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TeamService {

	private final Map<Integer, Team> m_teams = new HashMap<>();
	private static final AtomicInteger m_count = new AtomicInteger(0);

	public Team findById(Integer p_id) {
		return m_teams.get(p_id);
	}

	public Team add(String p_name) {
		int currentId = m_count.incrementAndGet();
		Team team = new Team(currentId, p_name);
		m_teams.put(currentId, team);
		return team;
	}

	public Team update(Integer p_id, String p_name) {

		Team team = m_teams.get(p_id);
		if (p_name != null) {
			team.setName(p_name);
		}

		m_teams.put(p_id, team);

		return team;

	}

	public void delete(Integer p_id) {
		m_teams.remove(p_id);
	}

	public List findAll() {
		return new ArrayList<>(m_teams.values());
	}
}
