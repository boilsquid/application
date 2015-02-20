package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("groupEventDao")
public class GroupEventDao implements DaoInterface<GroupEvent>{
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	

	
	public List<GroupEvent> getList() {

		return jdbc.query("select * from Groupevents", new RowMapper<GroupEvent>() {

			public GroupEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
				GroupEvent events = new GroupEvent();
				
				events.setGroupEventId(rs.getInt("groupEventId"));
				events.setGroupId(rs.getInt("groupId"));
				events.setUserName(rs.getString("userName"));
				events.setStart(rs.getTimestamp("start"));
				events.setEnd(rs.getTimestamp("end"));
				events.setRecurring(rs.getInt("recurring"));
				events.setEventType(rs.getString("eventType"));
				events.setTitle(rs.getString("title"));
				
				
			
				return events;
			}

		});
	}
	
	
	public boolean update(GroupEvent event) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(event);
		
		return jdbc.update("update GroupEvents set groupId=:groupId, userName=:userName, start=:start, end=:end, recurring=:recurring,"
				+ " eventType=:eventType, title=:title "
				+ " where id=:id", params) == 1;
	}
	

	public boolean create(GroupEvent event) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(event);
		
		return jdbc.update("insert into GroupEvent (groupId, userName, start, end, recurring,"
				+ " eventType, title) "
				+ "values (:groupId, :userName, :start, :end, :recurring,"
				+ " :eventType, :title)", params) == 1;
	
	}
	

	
	public boolean delete(Object groupEventId) {
		MapSqlParameterSource params = new MapSqlParameterSource("groupEventId", groupEventId);
		
		return jdbc.update("delete from UserEvents where groupEventId=:groupEventId", params) == 1;
	}

	public GroupEvent getItem(Object groupEventId) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("groupEventId", groupEventId);

		return jdbc.queryForObject("select * from GroupEvents where groupEventId=:groupEventId", params,
				new RowMapper<GroupEvent>() {

					public GroupEvent mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						GroupEvent events = new GroupEvent();
						
						events.setGroupEventId(rs.getInt("groupEventId"));
						events.setGroupId(rs.getInt("groupId"));
						events.setUserName(rs.getString("userName"));
						events.setStart(rs.getTimestamp("start"));
						events.setEnd(rs.getTimestamp("end"));
						events.setRecurring(rs.getInt("recurring"));
						events.setEventType(rs.getString("eventType"));
						events.setTitle(rs.getString("title"));
					
						return events;
						
					}

				});
	}
}
