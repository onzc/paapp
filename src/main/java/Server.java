import Team.Team;
import Team.TeamService;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

import static spark.Spark.*;

class Server {

	protected Server(Integer p_port, TeamService p_teamService) {

		try {
			port(p_port);
		} catch (IllegalStateException e) {
			//suppress this
			//TODO work out how we can see if spark is already running
		}

		// Main Page, welcome
		get("/", (request, response) -> "Welcome To PA Team Crud");

		// POST - Add team
		post("/team/add", (request, response) -> addTeamHandler(new RouteHelper(request, response), p_teamService));
		// Get - list of teams
		get("/teams", (request, response) -> listTeamsHandler(new JsonHelper(), p_teamService));
		//put - update team
		put("/team/:id", (request, response) -> updateTeamHandler(new RouteHelper(request, response), p_teamService));
		//delete
		delete("/team/:id", (request, response) -> deleteTeamHandler(new RouteHelper(request, response), p_teamService));

	}
	private static String addTeamHandler(AbstractRouteHelper p_routeHelper, TeamService p_teamService) throws
			JsonProcessingException {
		String name = p_routeHelper.getParam("name");
		Team team = p_teamService.addTeam(name);
		p_routeHelper.setStatus(201); // 201 Created
		return p_routeHelper.writeValue(team);
	}
	protected static String listTeamsHandler(AbstractJsonHelper p_jsonHelper, TeamService p_teamService)
			throws JsonProcessingException {
		List result = p_teamService.findAllTeams();
		if (result.isEmpty()) {
			return p_jsonHelper.writeValue("team not found");
		} else {
			return p_jsonHelper.writeValue(p_teamService.findAllTeams());
		}
	}
	protected static String updateTeamHandler(AbstractRouteHelper p_routeHelper, TeamService p_teamService)
			throws JsonProcessingException {
		Integer id = Integer.parseInt(p_routeHelper.getRouteParams(":id"));
		Team team = p_teamService.findById(id);
		if (team != null) {
			String name = p_routeHelper.getParam("name");

			p_teamService.updateTeam(id, name);
			Team updated = p_teamService.findById(id);
			return p_routeHelper.writeValue(updated);
		} else {
			p_routeHelper.setStatus(404);
			return p_routeHelper.writeValue("team not found");
		}
	}
	protected static String deleteTeamHandler(AbstractRouteHelper p_routeHelper, TeamService p_teamService)
			throws JsonProcessingException {
		Integer id = Integer.parseInt(p_routeHelper.getRouteParams(":id"));
		Team team = p_teamService.findById(id);
		if (team != null) {
			p_teamService.deleteTeam(id);
			return p_routeHelper.writeValue("team " + id + " deleted");
		} else {
			p_routeHelper.setStatus(404);
			return p_routeHelper.writeValue("team not found");
		}
	}
}
