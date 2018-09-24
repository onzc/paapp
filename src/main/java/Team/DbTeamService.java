package Team;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbTeamService extends TeamService {
	private Connection m_connection;
	public DbTeamService() {
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL("jdbc:h2:mem:˜/team.db");
		ds.setUser("sa");
		ds.setPassword("sa");
		try {
			m_connection = ds.getConnection();

			createTable();
		} catch (SQLException e) {
			System.out.println(e);
		} //end try
	}
	private void createTable() throws SQLException {

		Statement statement = m_connection.createStatement();

		String sql = "CREATE TABLE Teams " +
				"(id INTEGER not NULL AUTO_INCREMENT, " +
				" name VARCHAR(255), " +
				" PRIMARY KEY ( id ))";

		statement.execute(sql);

	}

	@Override
	public Team findById(Integer p_id) {
		Team team = null;
		try {
			Statement statement = m_connection.createStatement();
			String sql = "SELECT id, name FROM Teams WHERE id=" + p_id;
			statement.execute(sql);

			ResultSet resultSet = statement.getResultSet();
			if (resultSet.next()) {
				Integer id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				team = new Team(id, name);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		return team;
	}
	@Override
	public Team addTeam(String p_name) {
		Team team = null;
		try {
			Statement statement = m_connection.createStatement();
			String sql = "INSERT INTO Teams (name) VALUES ('" + p_name + "' )";
			statement.execute(sql);

			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				Integer id = resultSet.getInt(1);
				team = new Team(id, p_name);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		return team;
	}
	@Override
	public Team updateTeam(Integer p_id, String p_name) {
		Team team = null;
		try {
			Statement statement = m_connection.createStatement();
			String sql = "UPDATE Teams SET name = '" + p_name + "' WHERE id = " + p_id;
			statement.execute(sql);

			team = new Team(p_id, p_name);

		} catch (SQLException e) {
			System.out.println(e);
		}

		return team;
	}
	@Override
	public void deleteTeam(Integer p_id) {

		try {
			Statement statement = m_connection.createStatement();
			String sql = "DELETE Teams WHERE id = " + p_id;
			statement.execute(sql);

		} catch (SQLException e) {
			System.out.println(e);
		}

	}
	@Override
	public List findAllTeams() {
		ArrayList<Team> list = new ArrayList<Team>();
		try {
			Statement statement = m_connection.createStatement();
			String sql = "SELECT id, name FROM Teams ";
			statement.execute(sql);

			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				Integer id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Team team = new Team(id, name);
				list.add(team);

			}

		} catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	public void deleteAllTeams() {
		try {
			Statement statement = m_connection.createStatement();
			String sql = "DELETE Teams ";
			statement.execute(sql);

		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
