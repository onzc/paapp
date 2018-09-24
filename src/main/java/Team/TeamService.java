package Team;

import java.util.List;

public abstract class TeamService implements ITeamService {
	public TeamService() {
		super();
	}
	public abstract Team findById(Integer p_id);
	public abstract Team addTeam(String p_name);
	public abstract Team updateTeam(Integer p_id, String p_name);
	public abstract void deleteTeam(Integer p_id);
	public abstract List findAllTeams();
}
