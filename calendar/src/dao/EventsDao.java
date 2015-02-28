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
@Component("eventsDao")
public class EventsDao implements DaoInterface<Events>{
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	

	
	public List<Events> getList() {

		return jdbc.query("select * from userevents", new RowMapper<Events>() {

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
				events.setColor(rs.getString("color"));
				
			
				return events;
			}

		});
	}
	
	public boolean update(Events event) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(event);
		
		return jdbc.update("update userevents set userName=:userName, start=:start, end=:end, recurring=:recurring,"
				+ " eventType=:eventType, typeId=:typeId, title=:title, color=:color "
				+ " where id=:id", params) == 1;
	}
	

	public boolean create(Events event) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(event);
		
		return jdbc.update("insert into userevents (userName, start, end, recurring,"
				+ " eventType, typeId, title, color) "
				+ "values (:userName, :start, :end, :recurring,"
				+ " :eventType, :typeId, :title, :color)", params) == 1;
	
	}
	

	
	public boolean delete(Object id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from userevents where id=:id", params) == 1;
	}

	public Events getItem(Object id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from userevents where id=:id", params,
				new RowMapper<Events>() {

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
						events.setColor(rs.getString("color"));
					
						return events;
						
					}

				});
	}
}
