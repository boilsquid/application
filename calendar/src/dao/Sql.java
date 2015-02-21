package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

@Component("sql")
public class Sql {
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	/*
	 * Select a users own feed of user events for his personal table
	 */
	public List<Events> getUserEvents(String userName) {

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userName", userName);

		return jdbc.query("select * from UserEvents where userName =:userName",
				param, new RowMapper<Events>() {

					public Events mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Events events = new Events();

						events.setId(rs.getInt("id"));
						events.setUserName(rs.getString("userName"));
						events.setStart(rs.getTimestamp("start"));
						events.setEnd(rs.getTimestamp("end"));
						events.setRecurring(rs.getInt("recurring"));
						events.setEventType(rs.getString("eventType"));
						events.setTypeId(rs.getInt("typeId"));
						events.setTitle(rs.getString("title"));

						return events;
					}

				});
	}

	/*
	 * Delete, from, where, generic type sql call Delete all events from a users
	 * events table according to event type
	 */
	public boolean deleteFromWhere(Object table, Object username,
			Object eventtype) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("username", username)
				.addValue("eventtype", eventtype).addValue("table", table);

		return jdbc.update("delete from " + table
				+ " where username=:username and " + "eventtype=:eventtype",
				params) == 1;
	}

	/*
	 * Delete, from, groupMembers generic type sql call Delete all events from a
	 * users events table according to event type
	 */
	public boolean deleteFromGroupMembers(Object table, Object username,
			Object createdBy) {
		SqlParameterSource params = new MapSqlParameterSource().addValue(
				"username", username).addValue("createdBy", createdBy);

		return jdbc
				.update("delete from groupMembers where username =:username "
						+ " and groupId IN(Select groupId from groups where createdby =:createdBy)",
						params) == 1;
	}

	/*
	 * Get a group object by finding its name
	 */
	public Group getGroupByName(Object groupName) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("groupName", groupName);

		return jdbc.queryForObject(
				"select * from Groups where groupName=:groupName", params,
				new RowMapper<Group>() {

					public Group mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Group group = new Group();

						group.setGroupId(rs.getInt("groupId"));
						group.setGroupName(rs.getString("groupName"));
						group.setUserName(rs.getString("userName"));
						group.setDateCreated(rs.getTimestamp("dateCreated"));

						return group;

					}

				});
	}

	public List<Events> getFreeWeeks(String username, Timestamp startTime,
			int week, int min, int groupId, String event) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("date", startTime);
		params.addValue("week", week);
		params.addValue("username", username);
		params.addValue("min", min);
		params.addValue("groupId", groupId);
		
		/* run this for personal events for one user */
		String sql1 = "Select * from userevents where start >=( SELECT TIMESTAMPADD(WEEK,:week,:date)) "
				+ "and end<=(SELECT timestampadd(MINUTE,:min,( SELECT TIMESTAMPADD(WEEK,:week,:date))))"
				+ " and username=:username";

		/* run this sql to find free times for tutorials group */
		String sql2 = "Select * from userevents where start >=( SELECT TIMESTAMPADD(WEEK,:week,:date)) "
				+ "and end<=(SELECT timestampadd(MINUTE,:min,( SELECT TIMESTAMPADD(WEEK,:week,:date))))"
				+ " and (eventType='lecture' OR eventType='tutorial')"
		+ " and username IN(SELECT username from groupMembers where groupId=:groupId)";
		
		/* run this for free time for meeting events for a group */
		String sql3 = "Select * from userevents where start >=( SELECT TIMESTAMPADD(WEEK,:week,:date)) "
				+ "and end<=(SELECT timestampadd(MINUTE,:min,( SELECT TIMESTAMPADD(WEEK,:week,:date))))"
				+ " and username IN(SELECT username from groupMembers where groupId=:groupId)";

		String sql = sql1;

		if (groupId != 0) {// if its a group event choose 

			if (event.equals("tutorial")) {
				sql = sql2;
			} else if (event.equals("meeting")) {
				sql = sql3;
			}
		}

		return jdbc.query(sql, params, new RowMapper<Events>() {

			public Events mapRow(ResultSet rs, int rowNum) throws SQLException {
				Events events = new Events();

				events.setId(rs.getInt("id"));
				events.setUserName(rs.getString("userName"));
				events.setStart(rs.getTimestamp("start"));
				events.setEnd(rs.getTimestamp("end"));
				events.setRecurring(rs.getInt("recurring"));
				events.setEventType(rs.getString("eventType"));
				events.setTypeId(rs.getInt("typeId"));
				events.setTitle(rs.getString("title"));

				return events;
			}

		});
	}

	public boolean updateMinutes(Object id, int min, Timestamp start) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		params.addValue("min", min);
		params.addValue("start", start);
		
		return jdbc.update("update userevents "
					+"set end =(SELECT TIMESTAMPADD(MINUTE,:min,:start)) "
					+"where id =:id;", params) == 1;
	}
	
	
	/* get a list of users in a group*/
	public List<User> getUsersInGroup(int groupId) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("groupId", groupId);
		
		String sql ="Select * from users where username IN("
				+"Select userName from groupmembers where groupId =:groupId)";

		return jdbc.query(sql,params, new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
			
				user.setUserName(rs.getString("userName"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setRoleId(rs.getString("roleId"));
				user.setStreamId(rs.getInt("streamId"));
				user.setPassword(rs.getString("password"));
				user.setPasswordConfirmation(rs.getString("passwordConfirmation"));
				user.setCreatedAt(rs.getTimestamp("createdAt"));
				user.setUpdatedAt(rs.getTimestamp("updatedAt"));
				user.setSignInCount(rs.getInt("signInCount"));
				user.setEnabled(rs.getBoolean("enabled"));
				user.setActivationToken(rs.getString("activationToken"));
				user.setActivationTokenUpdatedAt(rs.getTimestamp("activationTokenUpdatedAt"));
				user.setPasswordResetToken(rs.getString("passwordResetToken"));
				user.setPasswordTokentUpdatedAt(rs.getTimestamp("passwordTokentUpdatedAt"));
				return user;
			}

		});
	}
	
	
	
}//end class



