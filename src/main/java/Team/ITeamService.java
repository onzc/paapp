package Team;

import java.util.List;

public interface ITeamService {
	public Team findById(Integer p_id);
	public Team addTeam(String p_name);
	public Team updateTeam(Integer p_id, String p_name);
	public void deleteTeam(Integer p_id);
	public List findAllTeams();
}
