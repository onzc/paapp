import Team.Team;
import Team.TeamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import spark.Spark;

import java.util.List;

import static spark.Spark.*;

class Server {
	private static TeamService s_teamService = new TeamService();

	protected Server(Integer p_port) {
		try {
			port(p_port);
		}
		catch (IllegalStateException e){
			//suppress this
			//TODO work out how we can see if spark is already running
		}
		
		// Main Page, welcome
		get("/", (request, response) -> "Welcome To PA Team Crud");

		// POST - Add team
		post("/team/add", (request, response) -> addTeamHandler(new RouteHelper(request, response)));
		// Get - list of teams
		get("/teams", (request, response) -> listTeamsHandler(new JsonHelper()));
		//put - update team
		put("/team/:id", (request, response) -> updateTeamHandler(new RouteHelper(request, response)));
		//delete
		delete("/team/:id", (request, response) -> deleteTeamHandler(new RouteHelper(request, response)));

	}
	private static String addTeamHandler(AbstractRouteHelper p_routeHelper) throws
			JsonProcessingException {
		String name = p_routeHelper.getParam("name");
		Team team = s_teamService.add(name);
		p_routeHelper.setStatus(201); // 201 Created
		return p_routeHelper.writeValue(team);
	}
	protected static String listTeamsHandler(AbstractJsonHelper p_jsonHelper) throws JsonProcessingException {
		List result = s_teamService.findAll();
		if (result.isEmpty()) {
			return p_jsonHelper.writeValue("team not found");
		} else {
			return p_jsonHelper.writeValue(s_teamService.findAll());
		}
	}
	protected static String updateTeamHandler(AbstractRouteHelper p_routeHelper) throws JsonProcessingException {
		Integer id = Integer.parseInt(p_routeHelper.getRouteParams(":id"));
		Team team = s_teamService.findById(id);
		if (team != null) {
			String name = p_routeHelper.getParam("name");

			s_teamService.update(id, name);
			Team updated = s_teamService.findById(id);
			return p_routeHelper.writeValue(updated);
		} else {
			p_routeHelper.setStatus(404);
			return p_routeHelper.writeValue("team not found");
		}
	}
	protected static String deleteTeamHandler(AbstractRouteHelper p_routeHelper) throws JsonProcessingException {
		Integer id = Integer.parseInt(p_routeHelper.getRouteParams(":id"));
		Team team = s_teamService.findById(id);
		if (team != null) {
			s_teamService.delete(id);
			return p_routeHelper.writeValue("team " + id + " deleted");
		} else {
			p_routeHelper.setStatus(404);
			return p_routeHelper.writeValue("team not found");
		}
	}
}
