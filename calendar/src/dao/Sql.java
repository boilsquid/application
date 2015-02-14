package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

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
	
		return jdbc.query("select * from UserEvents where userName =:userName", param, new RowMapper<Events>() {

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
	
	
	/*
	 * Delete, from, where,  generic type sql call
	 * Delete all events from a users events table according to event type
	 */
	public boolean deleteFromWhere(Object table, Object username, Object eventtype) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("username", username)
    			.addValue("eventtype",eventtype).addValue("table", table);
		
		return jdbc.update("delete from "+table+" where username=:username and "
				+ "eventtype=:eventtype", params) == 1;
	}

}
