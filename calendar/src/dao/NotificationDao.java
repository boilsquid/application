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


@Component("notificationDao")
public class NotificationDao implements DaoInterface<Notification> {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Notification> getList() {

		return jdbc.query("select * from notifications", new RowMapper<Notification>() {

			public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notification note= new Notification();
				
				note.setId(rs.getInt("id"));
				note.setCreatedBy(rs.getString("createdBy"));
				note.setCreatedAt(rs.getTimestamp("createdAt"));
				note.setUserName(rs.getString("userName"));
				note.setEventName(rs.getString("eventName"));
				note.setTime(rs.getTimestamp("time"));

				return note;
			}

		});
	}

	public boolean update(Notification note) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				note);

		return jdbc
				.update("update groups set groupName=:groupName, createdBy=:createdBy, UserName=:UserName, dateCreated=:dateCreated "
						+ "where groupId=:groupId", params) == 1;
	}

	public boolean create(Notification note) {

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
				note);
		
		return jdbc.update(
				"insert into notifications (createdBy, userName, eventName, time, createdAt) "
						+ "values ( :createdBy, :userName, :eventName, :time, now())",
				params) == 1;

	}



	public boolean delete(Object id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id",
				id);

		return jdbc.update("delete from notifications where id=:id", params) == 1;
	}

	public Notification getItem(Object groupId) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("groupId", groupId);

		return jdbc.queryForObject(
				"select * from groups where groupId=:groupId", params,
				new RowMapper<Notification>() {

					public  Notification mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Notification note= new Notification();
						
						note.setId(rs.getInt("id"));
						note.setCreatedBy(rs.getString("createdBy"));
						note.setCreatedAt(rs.getTimestamp("createdAt"));
						note.setUserName(rs.getString("userName"));
						note.setEventName(rs.getString("eventName"));
						note.setTime(rs.getTimestamp("time"));

						return note;

					}

				});
	}
	
	/* get a list of personal notifications*/
	public List<Notification> getPersonalNotes(String username) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);
		String sql = "select * from notifications where username =:username";
		
		return jdbc.query(sql,params , new RowMapper<Notification>() {

			public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notification note= new Notification();
				
				note.setId(rs.getInt("id"));
				note.setCreatedBy(rs.getString("createdBy"));
				note.setCreatedAt(rs.getTimestamp("createdAt"));
				note.setUserName(rs.getString("userName"));
				note.setEventName(rs.getString("eventName"));
				note.setTime(rs.getTimestamp("time"));

				return note;
			}

		});
	}

}
